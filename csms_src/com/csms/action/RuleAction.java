package com.csms.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.csms.domain.Message;
import com.csms.domain.GloRule;
import com.csms.domain.PreRule;
import com.csms.domain.Rule;
import com.csms.service.CategoryService;
import com.csms.service.RuleService;
import com.platform.exception.CRUDException;
import com.platform.web.action.GenericAction;

@Controller
@Scope("prototype")
public class RuleAction extends GenericAction<Rule> {

    private static final long serialVersionUID = -4867867482994497216L;

    @Autowired
    private RuleService ruleService;
    @Autowired
    private CategoryService contentService;
    private List<Rule> ruleList;
    private List<Message> contentList;
    private Rule rule;
    private String type;
    private String ids;
    private String state;
    
    public String list() throws Exception {
    	
        return SUCCESS;
    }
    
    /**
     * 部门树
     * 
     * @return
     * @throws Exception
     */
    public String listTree() throws Exception {
    	
        return SUCCESS;
    }

    public String listPagination() throws Exception {
    	try{
    	    page = ruleService.listPaginationRule(page,type);	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    


    /**
     * 新建
     * 
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
    	try{
    		if(!"0".equals(rule.getState()))
    			rule.setState("1");
    		
    		if("0".equals(rule.getTimeType())){
    			rule.setRuleStartTime("8:00");
    			rule.setRuleEndTime("18:00");
    		}else if("1".equals(rule.getTimeType())){
    			
    		}else if("2".equals(rule.getTimeType())){
    			
    		}
    		
    		ruleService.saveRule(rule,ids);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }


    /**
     * 修改
     * 
     * @return
     * @throws Exception
     */
    public String update() throws Exception {
    	try{
    		if(!"0".equals(rule.getState()))
    			rule.setState("1");
    		ruleService.updateRule(rule);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }

    /**
     * 删除
     * 
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
    	try{
    		 ruleService.deleteRule(idList);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String findAllContent() throws CRUDException{
    	try{
    		ruleList = ruleService.findAllRule();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        
        return SUCCESS;
    }
    
    //更新状态
    public String updateState(){
    	ruleService.updateState(idList, state);
    	return SUCCESS;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	public List<Rule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Message> getContentList() {
		return contentList;
	}

	public void setContentList(List<Message> contentList) {
		this.contentList = contentList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}