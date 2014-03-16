package com.csms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.csms.constants.CSMSSQLConstant;
import com.csms.domain.Category;
import com.platform.dao.GenericDAO;
import com.platform.domain.Users;
import com.platform.util.Meta;
import com.platform.util.PageHelper;
import com.platform.util.UUIDGenerator;
import com.platform.vo.Page;

/**
 * <p>程序名称：       ContentDAO.java</p>
 * <p>程序说明：       通知管理DAO</p>
 * <p>时间：          2013-1-13 16:53 雨夹雪</p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.1
 */
public class CategoryDAO extends GenericDAO{

	private static CategoryDAO instance;
	private JdbcTemplate jdbcTemplate;
	
	public static CategoryDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new CategoryDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public CategoryDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 分页获取内容
	 */
	public Page<Category> pagination(final Page<Category> page,String sql,Object[] args){
		Object[] preparedArgs = Meta.addPageToParameters(args, page);
		page.setList(jdbcTemplate.query(sql + CSMSSQLConstant.LIMIT, preparedArgs, new RowMapper<Category>(){
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category content = new Category();
				content.setName(rs.getString("name"));
				content.setId(rs.getString("id"));
				content.setType(rs.getString("type"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				content.setEnName(rs.getString("enname"));
				content.setImageUrl(rs.getString("imageurl"));
				content.setRemark(rs.getString("remark"));
				content.setSuperId(rs.getString("superid"));
				return content;
			}}));
		int rowCount = queryForInt(Meta.getRowCountSQL(CSMSSQLConstant.CATEGORY_ROWCOUNT_SQL , sql),args);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		return page;
	}
	
	
	/**
	 * 分页获取内容
	 */
	public Page<Category> paginationA(final Page<Category> page,String sql,Object[] args){
		Object[] preparedArgs = Meta.addPageToParameters(args, page);
		page.setList(jdbcTemplate.query(sql + CSMSSQLConstant.LIMIT, preparedArgs, new RowMapper<Category>(){
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category content = new Category();
				content.setName(rs.getString("name"));
				content.setId(rs.getString("id"));
				content.setType(rs.getString("type"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				content.setEnName(rs.getString("enname"));
				content.setImageUrl(rs.getString("imageurl"));
				content.setRemark(rs.getString("remark"));
				content.setSuperId(rs.getString("superid"));
				return content;
			}}));
		int rowCount = queryForInt(Meta.getRowCountSQL(CSMSSQLConstant.CATEGORY_ROWCOUNT_SQL , sql),args);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		return page;
	}
	
	/**
	 * 根据sql 和 args 查询
	 * 
	 */
	public List<Category> findAll(String sql,Object[] args){
		return jdbcTemplate.query(sql, args, new RowMapper<Category>(){
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category content = new Category();
				content.setName(rs.getString("name"));
				content.setId(rs.getString("id"));
				content.setType(rs.getString("type"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				content.setEnName(rs.getString("enname"));
				content.setImageUrl(rs.getString("imageurl"));
				content.setRemark(rs.getString("remark"));
				content.setSuperId(rs.getString("superid"));
				content.setUpdateTime(rs.getDate("updatetime")+ " "+ rs.getTime("updatetime"));
				return content;
			}
		});
	}
	
	/**
	 * 根据sql 查询
	 * 
	 */
	public List<Category> findAll(String sql){
		return findAll(sql,null);
	}
	
	/**
	 * 保存部门数据
	 * @param department
	 * @return
	 */
	public int save(Category content){
     return jdbcTemplate.update(CSMSSQLConstant.CATEGORY_SAVE_SQL, new Object[]{
			UUIDGenerator.generate(),
			content.getName(),
			content.getEnName(),
			content.getImageUrl(),
			content.getSuperId(),
			content.getRemark(),
			content.getType(),
			new Date(),
			new Date()
		});
	}
	
	
	public int update(Category content){
	     return jdbcTemplate.update(CSMSSQLConstant.CATEGORY_UPDATE_BY_ID_SQL, new Object[]{
	    		 content.getName(),
	 			content.getEnName(),
	 			content.getImageUrl(),
	 			content.getSuperId(),
	 			content.getRemark(),
	 			content.getType(),
	 			new Date(),
				content.getId()
			});
		}
	
	/**
	 * 根据ID 查询部门
	 * @param id
	 */
	public Category find(String sql,Object[] args){
		List<Category> list = findAll(sql,args);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}