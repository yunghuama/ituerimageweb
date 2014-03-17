package com.csms.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csms.constants.CSMSSQLConstant;
import com.csms.dao.CategoryDAO;
import com.csms.domain.Category;
import com.platform.exception.CRUDException;
import com.platform.service.IService;
import com.platform.util.LoginBean;
import com.platform.vo.Page;

@Service
public class CategoryService implements IService {

    private CategoryDAO contentDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	Log log = LogFactory.getLog(CategoryService.class);
    	log.debug(jdbcTemplate);
        contentDAO = CategoryDAO.getInstance(jdbcTemplate);
    }

    /**
     * 分页
     * 
     * @param page
     * @param superId
     * @return
     * @throws CRUDException
     */
    public Page<Category> listPagination(Page<Category> page,String type,String superId) throws CRUDException {
    	if(type== null || "".equals(type)){
    		return contentDAO.pagination(page, CSMSSQLConstant.CATEGORY_SELECT_BY_PAGE_SQL ,new Object[]{superId});
    	}else {
    		 return contentDAO.pagination(page, CSMSSQLConstant.CATEGORY_SELECT_BY_TYPE_PAGE_SQL ,new Object[]{type,superId});
    	}
       
    }
    
    /**
     * 根据id查询内容
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Category findById(String id) throws Exception {
        return contentDAO.find(CSMSSQLConstant.CATEGORY_SELECT_BY_ID,new String[]{id});
    }
    

    public List<Category> findAll(){
    	return contentDAO.findAll(CSMSSQLConstant.CATEGORY_SELECT_ALL_SQL);
    }
    
    public List<Category> findBySuperId(String superId){
    	return contentDAO.findAll(CSMSSQLConstant.CATEGORY_SELECT_BY_PAGE_SQL,new String[]{superId});
    }
    
    /**
     * 新建内容
     * 
     * @param department
     * @throws CRUDException
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void saveContent(Category content) throws CRUDException {
        contentDAO.save(content);
    }

    
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void updateContent(Category content) throws CRUDException {
        contentDAO.update(content);
    }
    
    /**
     * 删除
     * @param idList
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void delete(List<String> idList){
    	contentDAO.deleteByIdList(CSMSSQLConstant.CATEGORY_DELETE_BY_IDS,idList);
    }
}