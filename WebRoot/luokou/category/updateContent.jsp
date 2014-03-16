<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加字典</title>
    <link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
  </head> 
  <body>
    <form name="messageForm" id="messageForm" action="<%=path%>/csms/message/update.v" class="form" method="post">
      <s:hidden name="category.id"/>
      <div class="form-group">基本信息</div>
      <table class="form-table" cellspacing="0" cellpadding="0">
         <tr>
          <td class="form-left"><span class="form-required">*</span>类别名称</td>
          <td class="form-right"><s:textfield  name="category.name"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>英文名称</td>
          <td class="form-right"><s:textfield  name="category.enName"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>封面图片地址</td>
          <td class="form-right"><s:textfield  name="category.imageUrl"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>父类别</td>
          <td class="form-right">
          	<s:select list="contentList" listKey="id" listValue="name" name="category.superId"></s:select>
           </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>类型</td>
          <td class="form-right"><select name="category.type">
          	<option value="0"  <s:if test="category.type==0">selected=selected</s:if> >类别</option>
          	<option value="1" <s:if test="category.type==1">selected=selected</s:if> >专辑</option>
          </select> </td>
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
      addValidate('deptName', [{type:'canNull', value:'F', message:'必须填写部门名称'}]);
      
      addValidate('deptName', [{type:'maxlength', value: 50, message:'【部门名称】最大长度为50'}]);
      addValidate('deptRemark', [{type:'maxlength', value: 1000, message:'【说明】最大长度为1000'}]);
    });
    </script>
  </body>
</html>