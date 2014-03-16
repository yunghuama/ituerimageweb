package com.csms.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csms.constants.CSMSSQLConstant;
import com.csms.dao.ImageDAO;
import com.csms.domain.Image;
import com.platform.exception.CRUDException;
import com.platform.service.IService;
import com.platform.vo.Page;

@Service
public class ImageService implements IService {

    private ImageDAO contentDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	Log log = LogFactory.getLog(ImageService.class);
    	log.debug(jdbcTemplate);
        contentDAO = ImageDAO.getInstance(jdbcTemplate);
    }

    /**
     * 分页
     * 
     * @param page
     * @param superId
     * @return
     * @throws CRUDException
     */
    public Page<Image> listPagination(Page<Image> page,String tag) throws CRUDException {
		return contentDAO.pagination(page, CSMSSQLConstant.IMAGE_SELECT_BY_PAGE_TAG_SQL ,new Object[]{"%"+tag+"%"});
    }
    
    /**
     * 根据id查询内容
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Image findById(String id) throws Exception {
        return contentDAO.find(CSMSSQLConstant.IMAGE_SELECT_BY_ID,new String[]{id});
    }
    

    public List<Image> findAll(){
    	return contentDAO.findAll(CSMSSQLConstant.IMAGE_SELECT_ALL_SQL);
    }
    
    /**
     * 新建内容
     * 
     * @param department
     * @throws CRUDException
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void saveContent(Image content) throws CRUDException {
        contentDAO.save(content);
    }

    
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void updateContent(Image content) throws CRUDException {
        contentDAO.update(content);
    }
    
    /**
     * 删除
     * @param idList
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void delete(List<String> idList){
    	contentDAO.deleteByIdList(CSMSSQLConstant.IMAGE_DELETE_BY_IDS,idList);
    }
}