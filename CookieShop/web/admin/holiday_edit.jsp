<%--
  Created by IntelliJ IDEA.
  User: LR
  Date: 2024/12/30
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>节日编辑</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"></jsp:include>

    <br><br>

    <form class="form-horizontal" action="/admin/holiday_edit" method="post">
        <input type="hidden" name="id" value="${param.id }">
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">节日名称</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="holidayName" value="${param.holidayName }" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_startDate" class="col-sm-1 control-label">开始日期</label>
            <div class="col-sm-6">
                <input type="date" class="form-control" id="input_startDate" name="startDate" value="${param.startDate }" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_endDate" class="col-sm-1 control-label">结束日期</label>
            <div class="col-sm-6">
                <input type="date" class="form-control" id="input_endDate" name="endDate" value="${param.endDate }" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_discount" class="col-sm-1 control-label">折扣</label>
            <div class="col-sm-6">
                <input type="number" class="form-control" id="input_discount" name="discount" value="${param.discount }">
            </div>
        </div>
        <div class="form-group">
            <label for="input_description" class="col-sm-1 control-label">描述</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_description" name="description" value="${param.description }">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交修改</button>
            </div>
        </div>
    </form>

    <span style="color:red;"></span>

</div>
</body>
</html>