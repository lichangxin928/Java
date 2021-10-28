<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>查询学生ajax</title>
    <base href=" <%=basePath%>">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script>
        $(function (){

            $("#btnLoader").click(function (){
                $.ajax({
                    url:"student/queryStudent.do",
                    type:"get",
                    dataType:"json",
                    success:function(data){
                        $.each(data,function(i,n){
                            $("#info").append("<tr>")
                                .append("<td> "+ n.id+"</td>")
                                .append("<td> "+ n.name+"</td>")
                                .append("<td> "+ n.age+"</td>").append("<tr/>")
                        })
                    }
                })
            })
            $("#btnLoader").click();
        })
    </script>
</head>
<body>
    <div align="center">
        <table>
            <thead>
            <tr>
                <td>学号</td>
                <td>姓名</td>
                <td>年龄</td>
            </tr>
            </thead>
            <tbody id="info">

            </tbody>
        </table>
        <input type="button" id="btnLoader" value="查询数据" style="display: none">
    </div>
</body>
</html>
