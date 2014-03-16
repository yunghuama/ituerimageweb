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
    <form name="numberForm" id="numberForm" action="<%=path%>/csms/number/save.v" class="form" method="post">
      <div class="form-group">基本信息</div>
      <s:hidden name="depId"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
      <tr>
          <td class="form-left"><span class="form-required">*</span>姓名</td>
          <td class="form-right"><input type="text" class="text" name="number.name" id="name"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>部门</td>
          <td class="form-right"><s:select list="groupList" listKey="id" listValue="name" name="number.group" id="group"></s:select> </td>
        </tr>
        <tr>
          <td class="form-left">职务</td>
          <td class="form-right"><input type="text" class="text" name="number.remark" id="remark"/></td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>号码</td>
          <td class="form-right"><input type="text" class="text" name="number.number" id="number"/> </td>
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
      addValidate('number', [{type:'canNull', value:'F', message:'请填写正确的手机号'}]);
      addValidate('number',[{type:'minlength', value:'11', message:'请填写正确的手机号'}]);
      addValidate('number',[{type:'maxlength', value:'11', message:'请填写正确的手机号'}]);
      addValidate('number',[{type:'isInteger', valueType:'+',canNull:'F', message:'请填写正确的手机号'}]);
      addValidate('group', [{type:'canNull', value:'F', message:'请选择组群'}]);
      addValidate('remark', [{type:'maxlength', value: 1000, message:'【备注说明】最大长度为50'}]);
    });
    </script> 
  </body>
</html>