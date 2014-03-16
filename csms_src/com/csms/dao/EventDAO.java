package com.csms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.csms.constants.CSMSSQLConstant;
import com.csms.domain.Event;
import com.platform.dao.GenericDAO;
import com.platform.util.Meta;
import com.platform.util.PageHelper;
import com.platform.vo.Page;

/**
 * <p>程序名称：       EventDAO.java</p>
 * <p>程序说明：      	 活动DAO</p>
 * <p>时间：          2013-1-13 16:53 雨夹雪</p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.1
 */
public class EventDAO extends GenericDAO{

	private static final long serialVersionUID = 376038639038947152L;
	private static EventDAO instance;
	private JdbcTemplate jdbcTemplate;
	
	public static EventDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new EventDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public EventDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 分页获取内容
	 */
	public Page<Event> pagination(final Page<Event> page,String sql,Object[] args){
		Object[] preparedArgs = Meta.addPageToParameters(args, page);
		page.setList(jdbcTemplate.query(sql + CSMSSQLConstant.LIMIT, preparedArgs, new RowMapper<Event>(){
			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event content = new Event();
				content.setContent(rs.getString("content"));
				content.setId(rs.getString("id"));
				content.setCreateTime(rs.getDate("createtime") +" "+ rs.getTime("createtime"));
				return content;
			}}));
		int rowCount = queryForInt(Meta.getRowCountSQL(CSMSSQLConstant.EVENT_ROWCOUNT_SQL , sql),args);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		return page;
	}
	
	
	
	/**
	 * 根据sql 和 args 查询
	 * 
	 */
	public List<Event> findAll(String sql,Object[] args){
		return jdbcTemplate.query(sql, args, new RowMapper<Event>(){
			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event content = new Event();
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
	public List<Event> findAll(String sql){
		return findAll(sql,null);
	}
	
	/**
	 * 保存部门数据
	 * @param department
	 * @return
	 */
	public int save(Event content){
     return jdbcTemplate.update(CSMSSQLConstant.EVENT_SAVE_SQL, new Object[]{
    		content.getId(),
			content.getContent(),
			content.getCreator().getId(),
			new Date()
		});
	}
	
	
	public int update(Event content){
	     return jdbcTemplate.update(CSMSSQLConstant.EVENT_UPDATE_BY_ID_SQL, new Object[]{
				content.getContent(),
				content.getId()
			});
		}
	
	/**
	 * 根据ID 查询部门
	 * @param id
	 */
	public Event find(String sql,Object[] args){
		List<Event> list = findAll(sql,args);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}