package com.csms.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.csms.domain.Category;
import com.csms.domain.Image;
import com.csms.service.CategoryService;
import com.csms.service.ImageService;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Metadata;
import com.platform.domain.AttachedFile;
import com.platform.util.UploadHelper;
import com.platform.web.action.GenericAction;

@Controller
@Scope("prototype")
public class ImageAction extends GenericAction<Image> {

    private static final long serialVersionUID = -4867867482994497216L;

    @Autowired
    private ImageService imageService;
    @Autowired
    private CategoryService categoryService;
    private List<Image> contentList;
    private List<Category> categoryList;
    private Image image;
    private String imageId;
    private String categoryId;
    
    
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
    	categoryList = categoryService.findAll();
        return SUCCESS;
    }

    public String listPagination() throws Exception {
    	try{
    		Category cc =  categoryService.findById(categoryId);
    		if(cc!=null){
    			 page = imageService.listPagination(page,cc.getEnName());
    		}
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
    	categoryList = categoryService.findAll();
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
    		InputStream is = this.getClass().getClassLoader().getResourceAsStream("ituer.properties");
    		Properties pp = new Properties();
    		pp.load(is);
    		String path = pp.getProperty("basepath");
    		Category cc =  categoryService.findById(image.getCategoryId());
    		if(cc != null){
    			//获得类别组成的目录地址
    			String categorypath = getCategoryPath(image.getCategoryId(),"");
    			UploadHelper helper = new UploadHelper(upload, uploadFileName, uploadTitle, uploadContentType, path + categorypath,UploadHelper.NAME);
        		List<AttachedFile> list = helper.getAttachedFiles();
        		if(list!=null && list.size()>0){
        			for(AttachedFile file : list){
        				//获得图片的大小
        				File jpegFile = new File(file.getFilePath());
        				BufferedImage img = ImageIO.read(jpegFile);
        				image.setImageHeight(img.getHeight()+"");
        				image.setImageWidth(img.getWidth()+"");
        				image.setPath(categorypath+file.getFileName());
        				image.setTag(cc.getEnName() + " "+image.getTag());
        				imageService.saveContent(image);
        			}
        		}
        		
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }

    
    public String getCategoryPath(String id,String path) throws Exception{
    	if(id!=null && !"-1".equals(id)){
    		Category cc =  categoryService.findById(id);
    		path = cc.getName() + "/"+ path;
    		return getCategoryPath(cc.getSuperId(),path);
    	}else {
    		return path;
    	}
    }
    
    /**
     * 预修改
     * 
     * @return
     * @throws Exception
     */
    public String toUpdate() throws Exception {
    	try{
    		categoryList = categoryService.findAll();
    		image = imageService.findById(image.getId());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String toUpdateA() throws Exception {
    	try{
    		image = imageService.findById(image.getId());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public void exportJSON() throws Exception{
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("ituer.properties");
		Properties pp = new Properties();
		pp.load(is);
		String path = pp.getProperty("basepath");
    	
		
    }

    /**
     * 修改
     * 
     * @return
     * @throws Exception
     */
    public String update() throws Exception {
    	try{
//    		imageService.updateDepartment(image);
    		imageService.updateContent(image);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    public String updateA() throws Exception {
    	try{
    		imageService.updateContent(image);
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
    		 imageService.delete(idList);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }

	public ImageService getImageService() {
		return imageService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public List<Image> getContentList() {
		return contentList;
	}

	public void setContentList(List<Image> contentList) {
		this.contentList = contentList;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}