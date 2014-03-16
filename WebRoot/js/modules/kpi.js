var kpiFunctions = {
  openSaveContentWindow : function(){
    new WindowPanel({
      id : 'saveKpi',
      title : '新建考核结果',
      width : 500,
      height : 305,
      closeConfirm : true,
      html : '<iframe name="kpiSaveFrame" id="kpiSaveFrame" src="'+projectName+'/csms/kpi/toSave.v?'+getFlushParam('saveKpi')+'" frameborder="0" scrolling="auto"></iframe>',
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
            if(getFrame('kpiSaveFrame').validate())
              submitFrameForm('kpiSaveFrame', 'kpiForm');
          }
        }]
      })
    });
  }
};
