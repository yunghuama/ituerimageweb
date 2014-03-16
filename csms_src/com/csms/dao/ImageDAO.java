package com.csms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.csms.constants.CSMSSQLConstant;
import com.csms.domain.Image;
import com.platform.dao.GenericDAO;
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
public class ImageDAO extends GenericDAO{

	private static ImageDAO instance;
	private JdbcTemplate jdbcTemplate;
	
	public static ImageDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new ImageDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public ImageDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 分页获取内容
	 */
	public Page<Image> pagination(final Page<Image> page,String sql,Object[] args){
		Object[] preparedArgs = Meta.addPageToParameters(args, page);
		page.setList(jdbcTemplate.query(sql + CSMSSQLConstant.LIMIT, preparedArgs, new RowMapper<Image>(){
			@Override
			public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
				Image content = new Image();
				content.setName(rs.getString("name"));
				content.setId(rs.getString("id"));
				content.setPath(rs.getString("path"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				content.setTag(rs.getString("tag"));
				content.setTitle(rs.getString("title"));
				content.setImageHeight(rs.getString("imageheight"));
				content.setImageWidth(rs.getString("imagewidth"));
				content.setRemark(rs.getString("remark"));
				content.setCategoryId(rs.getString("categoryid"));
				content.setUpdateTime(rs.getDate("updatetime") + " "+ rs.getTime("updatetime"));
				return content;
			}}));
		int rowCount = queryForInt(Meta.getRowCountSQL(CSMSSQLConstant.IMAGE_ROWCOUNT_SQL , sql),args);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		return page;
	}
	
	
	
	/**
	 * 根据sql 和 args 查询
	 * 
	 */
	public List<Image> findAll(String sql,Object[] args){
		return jdbcTemplate.query(sql, args, new RowMapper<Image>(){
			@Override
			public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
				Image content = new Image();
				content.setName(rs.getString("name"));
				content.setId(rs.getString("id"));
				content.setPath(rs.getString("path"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				content.setTag(rs.getString("tag"));
				content.setTitle(rs.getString("title"));
				content.setRemark(rs.getString("remark"));
				content.setCategoryId(rs.getString("categoryid"));
				content.setUpdateTime(rs.getDate("updatetime") + " "+ rs.getTime("updatetime"));
				content.setImageHeight(rs.getString("imageheight"));
				content.setImageWidth(rs.getString("imagewidth"));
				return content;
			}
		});
	}
	
	/**
	 * 根据sql 查询
	 * 
	 */
	public List<Image> findAll(String sql){
		return findAll(sql,null);
	}
	
	/**
	 * 保存部门数据
	 * @param department
	 * @return
	 */
	public int save(Image content){
     return jdbcTemplate.update(CSMSSQLConstant.IMAGE_SAVE_SQL, new Object[]{
			UUIDGenerator.generate(),
			content.getName(),
			content.getTitle(),
			content.getRemark(),
			content.getPath(),
			content.getTag(),
			content.getCategoryId(),
			new Date(),
			new Date(),
			content.getImageWidth(),
			content.getImageHeight()
		});
	}
	
	
	public int update(Image content){
	     return jdbcTemplate.update(CSMSSQLConstant.IMAGE_UPDATE_BY_ID_SQL, new Object[]{
	    		content.getName(),
	 			content.getTitle(),
	 			content.getRemark(),
	 			content.getPath(),
	 			content.getTag(),
	 			content.getCategoryId(),
	 			new Date(),
				content.getId()
			});
		}
	
	/**
	 * 根据ID 查询部门
	 * @param id
	 */
	public Image find(String sql,Object[] args){
		List<Image> list = findAll(sql,args);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}