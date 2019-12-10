<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\12\11 0011
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" %>

<html>
<head>

    <title>素材管理系统</title>
    <meta name="referrer" content="never">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>

</head>
<body>
<%@include file="topbar.jsp" %>
<div class="container">
    <div class="center-content">
        <div class="row clearfix">
            <div class="col-md-8 column">

                <ul class="breadcrumb">
                    <li>
                        <a href="#">首页</a>
                    </li>
                    <li class="active">
                        素材列表
                    </li>
                </ul>
                <ul id="project_list">
                </ul>
                <ul id="project_order" class="pagination">
                </ul>
            </div>
        </div>
    </div>
</div>
<%@include file="bottombar.jsp" %>
<script type="text/javascript">
    $(function () {
        init();
    });
</script>
</body>
</html>
