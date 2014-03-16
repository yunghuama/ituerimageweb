var eventFunctions = {
  openSaveContentWindow : function(){
    new WindowPanel({
      id : 'saveEvent',
      title : '新增活动',
      width : 500,
      height : 355,
      closeConfirm : true,
      html : '<iframe name="eventSaveFrame" id="eventSaveFrame" src="'+projectName+'/csms/event/toSave.v?'+getFlushParam('saveEvent')+'" frameborder="0" scrolling="auto"></iframe>',
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
            if(getFrame('eventSaveFrame').validate())
              submitFrameForm('eventSaveFrame', 'eventForm');
          }
        }]
      })
    });
  },
  openViewContentWindow : function(id){
	    new WindowPanel({
	      id : 'viewEvent',
	      title : '查看活动',
	      width : 500,
	      height : 355,
	      closeConfirm : true,
	      html : '<iframe name="eventViewFrame" id="eventViewFrame" src="'+projectName+'/csms/event/toView.v?'+getFlushParam('viewEvent')+'&event.id='+id+'" frameborder="0" scrolling="auto"></iframe>'
	    });
	  }
};
