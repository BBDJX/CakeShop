<%--
  Created by IntelliJ IDEA.
  User: LR
  Date: 2024/12/30
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>节目管理</title>
</head>
<body>
<h1>节目管理</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>节目名称</th>
        <th>操作</th>
    </tr>
    <%
        List<ListHolidayServlet> programs = (List<HolidayProgram>) request.getAttribute("programs");
        if (programs!= null) {
            for (HolidayProgram program : programs) {
    %>
    <tr>
        <td><%= program.getId() %></td>
        <td><%= program.getProgramName() %></td>
        <td>
            <a href="editHolidayProgram?id=<%= program.getId() %>">编辑</a>
            <a href="deleteHolidayProgram?id=<%= program.getId() %>">删除</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<a href="addHolidayProgram">添加节目</a>
</body>
</html>
