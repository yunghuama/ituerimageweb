<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title></title>
    <link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/pagination.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/list.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/xToolbar.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/jquery-impromptu.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/xWindowPanel.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <s:include value="/share/btMask.jsp" />
  	<div id="toolbar"></div>
  	<form id="listForm" action="<%=path%>/csms/image/listPagination.v" method="post">
  	<s:hidden name="categoryId" id="categoryId"></s:hidden>
      <table id="titleTable" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td><input type="checkbox" id="allBox"/></td>
          <td>图片</td>
          <td>名称</td>
          <td>标题</td>
          <td>目录地址</td>
          <td>tag</td>
          <td>宽-高</td>
          <td>创建时间</td>
          <td>修改时间</td>
        </tr>
      </table>
      <table id="dataTable" cellpadding="0" cellspacing="0">
        <s:iterator id="content" value="page.list" status="i">
          <tr class="row">
            <td class="num"><s:property value="#i.index+1"/></td>
            <td class="box">
                <input type="checkbox" name="idList" value="<s:property value="#content.id"/>"/>
            </td>
            <td><span><s:property value="#content.name"/>&nbsp;</span></td>
            <td><span><s:property value="#content.name"/>&nbsp;</span></td>
            <td><span><s:property value="#content.title"/>&nbsp;</span></td>
            <td title='<s:property value="#content.path"/>'><span><s:property value="#content.path"/>&nbsp;</span></td>
            <td title='<s:property value="#content.tag"/>'><span><s:property value="#content.tag"/>&nbsp;</span></td>
             <td><span><s:property value="#content.imageWidth"/>X<s:property value="#content.imageHeight"/></span></td>
            <td><span><s:property value="#content.createTime"/>&nbsp;</span></td>
            <td><span><s:property value="#content.updateTime"/>&nbsp;</span></td>
          </tr>
        </s:iterator>
      </table>
      <s:hidden name="type"/>
      <s:include value="/share/pagebar.jsp"/>
  	</form>
    
    <script src="<%=path%>/js/core.js" type="text/javascript"></script>
    <script src="<%=path%>/js/share.js" type="text/javascript"></script>
    <script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
    <script src="<%=path%>/js/Grid.js" type="text/javascript"></script>
    <script src="<%=path%>/js/BoxSelect.js" type="text/javascript"></script>
    <script src="<%=path%>/js/xToolbar.js" type="text/javascript"></script>
    <script src="<%=path%>/js/xToolbar.items.js" type="text/javascript"></script>
    <script src="<%=path%>/js/jquery-impromptu.js" type="text/javascript"></script>
    <script src="<%=path%>/js/xWindowPanel.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function(){
       
      new Toolbar({
        id: '1',
        renderTo : 'toolbar',
        icon: '../../image/op.gif',
        items : [{
          type:'button',
          text:'新增',
          useable : 'T',
          position: {
            a: '0px 0px',
            b: '0px -120px'
          },
          handler:function(){
            top.imageFunctions.openSaveContentWindow($("#categoryId").val());
          }
        },'-',{
            type:'button',
            text:'修改',
            useable : 'T',
            position: {
              a: '0px 0px',
              b: '0px -120px'
            },
            handler:function(){
              if(getFirstID())
              top.imageFunctions.openUpdateContentWindow(getFirstID());
            }
          },'-',{
          type : 'button',
          text : '删除',
          position: {
            a: '-40px 0px',
            b: '-40px -120px'
          },
          useable : 'T',
          handler : function(){
            validateBeforeDelete({
              validateURL : '<%=path%>/system/ajax/deleteValidate.v',
              validateParams : $('input:checkbox[name="idList"]:checked').serialize(),
              validateTable : 'CSMSRule',
              deleteURL : '<%=path%>/csms/message/delete.v'
            });
          }
        }]
      });
      
      new Grid({
        titleTable:'titleTable',
        dataTable:'dataTable',
        widths : [30,24,100,100,150,150,150,100,150,150],
        height : function(){return getGridHeight({toolbarId:'toolbar',hasPage:true});}
      });
      
      new BoxSelect({
        allId : 'allBox',
        boxName : 'idList'
      });
      
      loadReady();
    });
    
      function openMoreOperateWindow(e, usersId,departmentId, id) {
      var morebar = new Toolbar({
        id:'2',
        icon: '../../image/op.gif',
        border: 'none',
        items : [{
          type : 'button',
          text : '删除',
          useable : findOtherOperate(usersId,departmentId, 'department_delete'),
          position: {
            a: '-40px 0px',
            b: '-40px -120px'
          },
          handler : function(){
            WPS['jWindowPanel-moreOperate'].close();
            var txt = '是否删除？';
            $.prompt(txt,{
              buttons:{删除:true,取消:false},
              callback: function(v,m) {
                if(v) {
                  window.location = '<%=path%>/csms/message/delete.v?idList='+id;
                }
              }
            });
          }
        }]
      });
      var wp = new WindowPanel({
        id : 'moreOperate',
        title : '可用操作',
        width : 280,
        height : 0,
        draggable : false,
        minimizable: false,
        maximizable: false,
        position: {
          x: e.clientX,
          y: e.clientY
        },
        tbar: morebar
      });
    }
    </script>
  </body>
</html>