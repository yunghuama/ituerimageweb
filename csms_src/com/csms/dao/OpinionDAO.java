package com.csms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.csms.constants.CSMSSQLConstant;
import com.csms.domain.Opinion;
import com.platform.dao.GenericDAO;
import com.platform.domain.Users;
import com.platform.util.Meta;
import com.platform.util.PageHelper;
import com.platform.util.UUIDGenerator;
import com.platform.vo.Page;

/**
 * <p>程序名称：       ContentDAO.java</p>
 * <p>程序说明：       考核成绩DAO</p>
 * <p>时间：          2013-1-13 16:53 雨夹雪</p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.1
 */
public class OpinionDAO extends GenericDAO{

	private static final long serialVersionUID = 376038639038947152L;
	private static OpinionDAO instance;
	private JdbcTemplate jdbcTemplate;
	
	public static OpinionDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new OpinionDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public OpinionDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 分页获取内容
	 */
	public Page<Opinion> pagination(final Page<Opinion> page,String sql,Object[] args){
		Object[] preparedArgs = Meta.addPageToParameters(args, page);
		page.setList(jdbcTemplate.query(sql + CSMSSQLConstant.LIMIT, preparedArgs, new RowMapper<Opinion>(){
			@Override
			public Opinion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Opinion content = new Opinion();
				content.setContent(rs.getString("content"));
				content.setId(rs.getString("id"));
				content.setNumber(rs.getString("number"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				return content;
			}}));
		int rowCount = queryForInt(Meta.getRowCountSQL(CSMSSQLConstant.OPINION_ROWCOUNT_SQL , sql),args);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		return page;
	}
	
	
	/**
	 * 根据sql 和 args 查询
	 * 
	 */
	public List<Opinion> findAll(String sql,Object[] args){
		return jdbcTemplate.query(sql, args, new RowMapper<Opinion>(){
			@Override
			public Opinion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Opinion content = new Opinion();
				content.setId(rs.getString("id"));
				content.setContent(rs.getString("content"));
				return content;
			}
		});
	}
	
	/**
	 * 根据sql 查询
	 * 
	 */
	public List<Opinion> findAll(String sql){
		return findAll(sql,null);
	}
	
	/**
	 * 保存部门数据
	 * @param department
	 * @return
	 */
	public int save(Opinion content){
     return jdbcTemplate.update(CSMSSQLConstant.OPINION_SAVE_SQL, new Object[]{
			UUIDGenerator.generate(),
			content.getContent(),
			content.getNumber(),
			content.getCreator().getId(),
			new Date()
		});
	}
	
	
	public int update(Opinion content){
	     return jdbcTemplate.update(CSMSSQLConstant.OPINION_UPDATE_BY_ID_SQL, new Object[]{
				content.getContent(),
				content.getId()
			});
		}
	
	/**
	 * 根据ID 查询部门
	 * @param id
	 */
	public Opinion find(String sql,Object[] args){
		List<Opinion> list = findAll(sql,args);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}