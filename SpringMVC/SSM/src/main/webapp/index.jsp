<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String basePath = request.getScheme() + "://" +
          request.getServerName() + ":" + request.getServerPort() +
          request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <base href="<%=basePath%>>">
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<h1><p>ssm crud</p></h1>
<table>
  <tr>
    <td><a href="addStudent.jsp">注册学生</a></td>
  </tr>
  <tr>
    <td><a href="listStudent.jsp">浏览学生</a> </td>
  </tr>
  <tr>
    <td>注册学生</td>
  </tr>

</table>

</body>
</html>