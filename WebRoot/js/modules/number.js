var numberFunctions = {
  openSaveNumberWindow : function(){
    new WindowPanel({
      id : 'saveNumber',
      title : '添加号码',
      width : 350,
      height : 205,
      closeConfirm : true,
      html : '<iframe name="numberSaveFrame" id="numberSaveFrame" src="'+projectName+'/csms/number/toSave.v?'+getFlushParam('saveNumber')+'" frameborder="0" scrolling="auto"></iframe>',
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
            if(getFrame('numberSaveFrame').validate())
              submitFrameForm('numberSaveFrame', 'numberForm');
          }
        }]
      })
    });
  },
  openUpdateNumberWindow : function(id){
	    new WindowPanel({
	      id : 'updateNumber',
	      title : '修改号码',
	      width : 350,
	      height : 205,
	      closeConfirm : true,
	      html : '<iframe name="numberUpdateFrame" id="numberUpdateFrame" src="'+projectName+'/csms/number/toUpdate.v?'+getFlushParam('updateNumber')+'&number.id='+id+'" frameborder="0" scrolling="auto"></iframe>',
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
	            if(getFrame('numberUpdateFrame').validate())
	              submitFrameForm('numberUpdateFrame', 'numberForm');
	          }
	        }]
	      })
	    });
	  }
  ,
  openUpdateNumberAWindow : function(id){
	    new WindowPanel({
	      id : 'updateNumberA',
	      title : '移动号码',
	      width : 350,
	      height : 80,
	      closeConfirm : true,
	      html : '<iframe name="numberAUpdateFrame" id="numberAUpdateFrame" src="'+projectName+'/csms/number/toUpdateA.v?'+getFlushParam('updateNumberA')+'&ids='+id+'" frameborder="0" scrolling="auto"></iframe>',
	      tbar : new Toolbar({
	        icon: 'image/op.gif',
	        items : [{
	          type : 'button',
	          text : '移动',
	          position: {
	            a: '-80px 0px',
	            b: '-80px -120px'
	          },
	          handler : function(){
	            if(getFrame('numberAUpdateFrame').validate())
	              submitFrameForm('numberAUpdateFrame', 'numberForm');
	          }
	        }]
	      })
	    });
	  }
  ,
  openImportNumberWindow : function(){
	    new WindowPanel({
	      id : 'importNumber',
	      title : '导入号码',
	      width : 350,
	      height : 80,
	      closeConfirm : true,
	      html : '<iframe name="numberImportFrame" id="numberImportFrame" src="'+projectName+'/csms/number/toImportA.v?'+getFlushParam('importNumber')+'" frameborder="0" scrolling="auto"></iframe>',
	      tbar : new Toolbar({
	        icon: 'image/op.gif',
	        items : [{
	          type : 'button',
	          text : '导入',
	          position: {
	            a: '-80px 0px',
	            b: '-80px -120px'
	          },
	          handler : function(){
	            if(getFrame('numberImportFrame').validate())
	              submitFrameForm('numberImportFrame', 'numberForm');
	          }
	        }]
	      })
	    });
  	}
};
