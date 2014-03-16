var messageFunctions = {
  openSaveContentWindow : function(superId){
    new WindowPanel({
      id : 'saveMessage',
      title : '新建类别',
      width : 500,
      height : 305,
      closeConfirm : true,
      html : '<iframe name="messageSaveFrame" id="messageSaveFrame" src="'+projectName+'/csms/message/toSave.v?'+getFlushParam('saveMessage')+'&category.superId='+superId+'" frameborder="0" scrolling="auto"></iframe>',
      tbar : new Toolbar({
        icon: 'image/op.gif',
        items : [{
          type : 'button',
          text : '保存',
          position: {
            a: '-80px 0px',
            b: '-80px -120px'
          },
          handler : function(){
            if(getFrame('messageSaveFrame').validate())
              submitFrameForm('messageSaveFrame', 'messageForm');
          }
        }]
      })
    });
  }
,
  openUpdateContentWindow : function(id){
	    new WindowPanel({
	      id : 'updateMessage',
	      title : '修改',
	      width : 500,
	      height : 305,
	      closeConfirm : true,
	      html : '<iframe name="messageUpdateFrame" id="messageUpdateFrame" src="'+projectName+'/csms/message/toUpdate.v?'+getFlushParam('updateMessage')+'&category.id='+id+'" frameborder="0" scrolling="auto"></iframe>',
	      tbar : new Toolbar({
	        icon: 'image/op.gif',
	        items : [{
	          type : 'button',
	          text : '修改',
	          position: {
	            a: '-80px 0px',
	            b: '-80px -120px'
	          },
	          handler : function(){
	            if(getFrame('messageUpdateFrame').validate())
	              submitFrameForm('messageUpdateFrame', 'messageForm');
	          }
	        }]
	      })
	    });
	  }
};


var imageFunctions = {
		  openSaveContentWindow : function(categoryId){
		    new WindowPanel({
		      id : 'saveImage',
		      title : '新建类别',
		      width : 500,
		      height : 305,
		      closeConfirm : true,
		      html : '<iframe name="imageSaveFrame" id="imageSaveFrame" src="'+projectName+'/csms/image/toSave.v?'+getFlushParam('saveImage')+'&image.categoryId='+categoryId+'" frameborder="0" scrolling="auto"></iframe>',
		      tbar : new Toolbar({
		        icon: 'image/op.gif',
		        items : [{
		          type : 'button',
		          text : '保存',
		          position: {
		            a: '-80px 0px',
		            b: '-80px -120px'
		          },
		          handler : function(){
		            if(getFrame('imageSaveFrame').validate())
		              submitFrameForm('imageSaveFrame', 'messageForm');
		          }
		        }]
		      })
		    });
		  },
		  openUpdateContentWindow : function(id){
			    new WindowPanel({
			      id : 'updateImage',
			      title : '修改',
			      width : 500,
			      height : 305,
			      closeConfirm : true,
			      html : '<iframe name="imageUpdateFrame" id="imageUpdateFrame" src="'+projectName+'/csms/image/toUpdate.v?'+getFlushParam('updateImage')+'&image.id='+id+'" frameborder="0" scrolling="auto"></iframe>',
			      tbar : new Toolbar({
			        icon: 'image/op.gif',
			        items : [{
			          type : 'button',
			          text : '修改',
			          position: {
			            a: '-80px 0px',
			            b: '-80px -120px'
			          },
			          handler : function(){
			            if(getFrame('imageUpdateFrame').validate())
			              submitFrameForm('imageUpdateFrame', 'messageForm');
			          }
			        }]
			      })
			    });
			  }
	};
