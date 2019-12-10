<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\5\23 0023
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员操作界面</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin_operation.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="topbar.jsp" %>
<div class="container">
    <div class="center-content">
        <div class="row clearfix">
            <div class="col-md-3 column">
                <div class="panel panel-default">
                    <div id="btn_project_list" onclick="getInfromation(1)" class="panel-body">
                        <strong>素材列表</strong>
                    </div>
                    <div id="btn_add_project" onclick="getInfromation(2)" class="panel-footer">
                        <strong>新增素材</strong>
                    </div>
                    <div id="btn_contact_us" onclick="getInfromation(3)" class="panel-footer">
                        <strong> 联系我们</strong>
                    </div>
                </div>
            </div>
            <div id="main_content" class="col-md-9 column">
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/admin_operation.js"></script>
<script type="text/javascript">
    window.onload = function () {
        $("#search_project").css('display', 'none'); //显示
        getInfromation(1);
    }
</script>
</body>
</html>
