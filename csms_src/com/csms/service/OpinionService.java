package com.csms.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csms.constants.CSMSSQLConstant;
import com.csms.dao.OpinionDAO;
import com.csms.domain.Opinion;
import com.platform.exception.CRUDException;
import com.platform.service.IService;
import com.platform.util.LoginBean;
import com.platform.vo.Page;

@Service
public class OpinionService implements IService {

    private OpinionDAO opinionDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	Log log = LogFactory.getLog(OpinionService.class);
    	log.debug(jdbcTemplate);
        opinionDAO = OpinionDAO.getInstance(jdbcTemplate);
    }

    /**
     * 分页
     * 
     * @param page
     * @param superId
     * @return
     * @throws CRUDException
     */
    public Page<Opinion> listPagination(Page<Opinion> page) throws CRUDException {
    		return opinionDAO.pagination(page, CSMSSQLConstant.OPINION_SELECT_BY_PAGE_SQL ,new Object[]{});
       
    }
    
    /**
     * 根据id查询内容
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Opinion findById(String id) throws Exception {
        return opinionDAO.find(CSMSSQLConstant.OPINION_SELECT_BY_ID,new String[]{id});
    }
    

    /**
     * 新建内容
     * 
     * @param department
     * @throws CRUDException
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void saveContent(Opinion content) throws CRUDException {
    	content.setCreator(LoginBean.getLoginBean().getUser());
        opinionDAO.save(content);
    }

    
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void updateContent(Opinion content) throws CRUDException {
        opinionDAO.update(content);
    }
    
    /**
     * 删除
     * @param idList
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void delete(List<String> idList){
    	opinionDAO.deleteByIdList(CSMSSQLConstant.OPINION_DELETE_BY_IDS,idList);
    }
}