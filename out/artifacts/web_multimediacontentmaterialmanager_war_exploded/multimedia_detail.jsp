<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\12\08 0023
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="referrer" content="no-referrer">
    <title>素材详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/multimedia_detail.css" rel="stylesheet" type="text/css"/>
    <link href="css/video-js.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="topbar.jsp" %>
<div class="container">
    <div class="center-content">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row clearfix">
                    <div id="project_detail_content" class="col-md-8 column">
                         
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<%@include file="bottombar.jsp" %>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/multimedia_detail.js"></script>
<script type="text/javascript" src="js/video.min.js"></script>
<script type="text/javascript">
    window.onload = function () {
        $("#search_project").css('display', 'none'); //隐藏
        var number = GetQueryString("number");
        getProjectByNumber(number);
    }
</script>
</body>
</html>
