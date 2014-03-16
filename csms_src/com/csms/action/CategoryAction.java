package com.csms.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.csms.domain.Category;
import com.csms.service.CategoryService;
import com.platform.web.action.GenericAction;

@Controller
@Scope("prototype")
public class CategoryAction extends GenericAction<Category> {

    private static final long serialVersionUID = -4867867482994497216L;

    @Autowired
    private CategoryService categoryService;
    private List<Category> contentList;
    private Category category;
    private String contentId;
    private String type;
    private String superId;
    
    
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
    	contentList = categoryService.findAll();
        return SUCCESS;
    }

    public String listPagination() throws Exception {
    	try{
    	    page = categoryService.listPagination(page,type,superId);	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    /**
     * 预新建
     * 
     * @return
     * @throws Exception
     */
    public String toSave() throws Exception {
    	contentList = categoryService.findAll();
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
    		categoryService.saveContent(category);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }

    /**
     * 预修改
     * 
     * @return
     * @throws Exception
     */
    public String toUpdate() throws Exception {
    	try{
    		contentList = categoryService.findAll();
    		category = categoryService.findById(category.getId());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String toUpdateA() throws Exception {
    	try{
    		category = categoryService.findById(category.getId());
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
//    		categoryService.updateDepartment(category);
    		categoryService.updateContent(category);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String updateA() throws Exception {
    	try{
    		categoryService.updateContent(category);
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
    		 categoryService.delete(idList);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<Category> getContentList() {
		return contentList;
	}

	public void setContentList(List<Category> contentList) {
		this.contentList = contentList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}