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
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <form name="eventForm" id="eventForm" action="<%=path%>/csms/event/save.v" class="form" method="post" enctype="multipart/form-data">
      <div class="form-group">基本信息</div>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left"><span class="form-required"></span>活动内容</td>
          <td class="form-right" style="height:200px;"><div style="height:200px;word-wrap: break-word;width:370px;overflow:auto"><s:property value="event.content"/> </div></td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required"></span>面图</td>
          <td class="form-right">
          	<s:iterator value="event.imageList" id="imagesrc">
          		<img src='<%=path %>/<s:property value="#imagesrc"/>' alt="" style="width:100px;height:100px"/>
          	</s:iterator>
          	
          </td>
        </tr>
      </table>
      <s:hidden name="tabId"/>
      <s:hidden name="windowPanelId"/>
    </form>
    <script src="<%=path%>/js/core.js" type="text/javascript"></script>
    <script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
    <script src="<%=path%>/js/Validate.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function(){
      addValidate('content', [{type:'canNull', value:'F', message:'必须填写通知内容'}]);
    });
    </script> 
  </body>
</html>