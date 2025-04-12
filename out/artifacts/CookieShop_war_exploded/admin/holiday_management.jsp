<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>节日管理</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"></jsp:include>

    <br>

    <!-- 添加节日表单 -->
    <div>
        <form class="form-inline" method="post" action="/admin/holiday_add">
            <input type="text" class="form-control" id="input_holidayName" name="holidayName" placeholder="输入节日名称" required="required" style="width: 500px">
            <input type="date" class="form-control" id="input_startDate" name="startDate" placeholder="开始日期" required="required" style="width: 200px">
            <input type="date" class="form-control" id="input_endDate" name="endDate" placeholder="结束日期" required="required" style="width: 200px">
            <input type="number" class="form-control" id="input_discount" name="discount" placeholder="折扣（可选）" style="width: 100px">
            <input type="text" class="form-control" id="input_description" name="description" placeholder="节日描述（可选）" style="width: 500px">
            <input type="submit" class="btn btn-warning" value="添加节日"/>
        </form>
    </div>
    <br/>
    <!-- 根据后端传递的消息提示，显示相应的提示框 -->
    <c:if test="${!empty successMsg }">
        <div class="alert alert-success">${successMsg }</div>
    </c:if>
    <c:if test="${!empty errorMsg }">
        <div class="alert alert-danger">${errorMsg }</div>
    </c:if>
    <br>

    <table class="table table-bordered table-hover">

        <tr>
            <th width="5%">ID</th>
            <th width="20%">节日名称</th>
            <th width="15%">开始日期</th>
            <th width="15%">结束日期</th>
            <th width="10%">折扣</th>
            <th width="20%">描述</th>
            <th width="15%">操作</th>
        </tr>

        <c:forEach items="${holidayList }" var="h">
            <tr>
                <td><p>${h.id }</p></td>
                <td><p>${h.holidayName }</p></td>
                <td><p>${h.startDate }</p></td>
                <td><p>${h.endDate }</p></td>
                <td><p>${h.discount }</p></td>
                <td><p>${h.description }</p></td>
                <td>
                    <a class="btn btn-primary" href="/admin/holiday_edit.jsp?id=${h.id }&holidayName=${h.holidayName }&startDate=${h.startDate }&endDate=${h.endDate }&discount=${h.discount }&description=${h.description }">修改</a>
                    <a class="btn btn-danger" href="/admin/holiday_delete?id=${h.id }">删除</a>
                </td>
            </tr>
        </c:forEach>


    </table>

</div>
</body>
</html>