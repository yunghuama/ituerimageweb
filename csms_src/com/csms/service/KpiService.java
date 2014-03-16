package com.csms.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csms.constants.CSMSSQLConstant;
import com.csms.dao.KpiDAO;
import com.csms.domain.Kpi;
import com.platform.exception.CRUDException;
import com.platform.service.IService;
import com.platform.util.LoginBean;
import com.platform.vo.Page;

@Service
public class KpiService implements IService {

    private KpiDAO kpiDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	Log log = LogFactory.getLog(KpiService.class);
    	log.debug(jdbcTemplate);
        kpiDAO = KpiDAO.getInstance(jdbcTemplate);
    }

    /**
     * 分页
     * 
     * @param page
     * @param superId
     * @return
     * @throws CRUDException
     */
    public Page<Kpi> listPagination(Page<Kpi> page) throws CRUDException {
    		return kpiDAO.pagination(page, CSMSSQLConstant.KPI_SELECT_BY_PAGE_SQL ,new Object[]{});
       
    }
    
    /**
     * 根据id查询内容
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Kpi findById(String id) throws Exception {
        return kpiDAO.find(CSMSSQLConstant.KPI_SELECT_BY_ID,new String[]{id});
    }
    

    /**
     * 新建内容
     * 
     * @param department
     * @throws CRUDException
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void saveContent(Kpi content) throws CRUDException {
    	content.setCreator(LoginBean.getLoginBean().getUser());
        kpiDAO.save(content);
    }

    
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void updateContent(Kpi content) throws CRUDException {
        kpiDAO.update(content);
    }
    
    /**
     * 删除
     * @param idList
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public void delete(List<String> idList){
    	kpiDAO.deleteByIdList(CSMSSQLConstant.KPI_DELETE_BY_IDS,idList);
    }
}