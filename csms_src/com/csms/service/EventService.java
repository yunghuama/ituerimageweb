package com.csms.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csms.constants.CSMSSQLConstant;
import com.csms.dao.EventDAO;
import com.csms.domain.Event;
import com.platform.exception.CRUDException;
import com.platform.service.IService;
import com.platform.util.LoginBean;
import com.platform.util.UUIDGenerator;
import com.platform.vo.Page;

@Service
public class EventService implements IService {

    private EventDAO contentDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	Log log = LogFactory.getLog(EventService.class);
    	log.debug(jdbcTemplate);
        contentDAO = EventDAO.getInstance(jdbcTemplate);
    }

    /**
     * 分页
     * 
     * @param page
     * @param superId
     * @return
     * @throws CRUDException
     */
    public Page<Event> listPagination(Page<Event> page,String type) throws CRUDException {
    		return contentDAO.pagination(page, CSMSSQLConstant.EVENT_SELECT_BY_PAGE_SQL ,new Object[]{});
    }
    
    /**
     * 根据id查询内容
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Event findById(String id) throws Exception {
        return contentDAO.find(CSMSSQLConstant.EVENT_SELECT_BY_ID,new String[]{id});
    }
    

    /**
     * 新建内容
     * 
     * @param department
     * @throws CRUDException
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void saveContent(Event content) throws CRUDException {
    	content.setCreator(LoginBean.getLoginBean().getUser());
    	content.setId(UUIDGenerator.generate());
        contentDAO.save(content);
    }

    
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void updateContent(Event content) throws CRUDException {
        contentDAO.update(content);
    }
    
    /**
     * 删除
     * @param idList
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void delete(List<String> idList){
    	contentDAO.deleteByIdList(CSMSSQLConstant.EVENT_DELETE_BY_IDS,idList);
    }
}