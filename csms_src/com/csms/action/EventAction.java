package com.csms.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.csms.domain.Event;
import com.csms.service.EventService;
import com.platform.domain.AttachedFile;
import com.platform.service.AttachedFileService;
import com.platform.util.UploadHelper;
import com.platform.web.action.GenericAction;

@Controller
@Scope("prototype")
public class EventAction extends GenericAction<Event> {

    private static final long serialVersionUID = -4867867482994497216L;

    @Autowired
    private EventService eventService;
    @Autowired
    private AttachedFileService attachedFileService;
    private List<Event> contentList;
    private Event event;
    private String contentId;
    private String type;
    
    
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
    	    page = eventService.listPagination(page,type);	
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
    		//保存内容
    		eventService.saveContent(event);
    		UploadHelper helper = new UploadHelper(upload, uploadFileName, uploadTitle, uploadContentType, "upload",UploadHelper.NAME_UUID);
    		List<AttachedFile> list = helper.getAttachedFiles();
    		//保存图片
    		attachedFileService.save(list, event.getId());
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
    		 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String toView() throws Exception {
    	try{
    		//查询活动
    		event = eventService.findById(event.getId());
    		//查询图片
    		if(event!=null){
    			List<AttachedFile> list =	attachedFileService.findAttachedFileBySuperId(event.getId());
    			List<String> imageList = new ArrayList<String>();
    			event.setImageList(imageList);
    			if(list!=null&&list.size()>0){
    				for(AttachedFile file : list){
    					imageList.add(file.getFilePath());
    				}
    			}
    		}
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
//    		eventService.updateDepartment(message);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String updateA() throws Exception {
    	try{
    		eventService.updateContent(event);
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
    		 eventService.delete(idList);
    		 //删除对应的附件
    		 if(idList!=null&&idList.size()>0){
    			 for(String id : idList){
    				 attachedFileService.deleteBySuperId(id);
    			 }
    		 }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public AttachedFileService getAttachedFileService() {
		return attachedFileService;
	}

	public void setAttachedFileService(AttachedFileService attachedFileService) {
		this.attachedFileService = attachedFileService;
	}

	public List<Event> getContentList() {
		return contentList;
	}

	public void setContentList(List<Event> contentList) {
		this.contentList = contentList;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
    
}