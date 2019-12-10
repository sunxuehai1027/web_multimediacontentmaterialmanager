<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\12\11 0011
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="css/top_bar.css" rel="stylesheet" type="text/css"/>
    <style rel="stylesheet" type="text/css">

        body {
            background-color: #EDF1F2;
            font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu
        }

        .center-content {
            background-color: white;
            padding: 20px;
        }

        hr {
            height: 1px;
            border: none;
            border-top: 1px solid #EAEAEA;
        }

        .line_container {
            display: inline-block;
            white-space: nowrap;
        }

        * {
            box-sizing: border-box;
        }

        div.search_bar {
            padding: 10px 0;
        }

        .search_bar input {
            width: 100%;
            height: 42px;
            padding-left: 13px;
            border: 2px solid #999999;
            border-radius: 5px;
            background: transparent;
            top: 0;
            right: 0;
        }

        .search_bar button {
            border: none;
            outline: none;
            height: 42px;
            width: 42px;
            cursor: pointer;
            background: #999999;
            border-radius: 0 5px 5px 0;
            width: 60px;
            position: relative;
            top: -1px;
            right: 65px;
        }

        .search_bar button:before {
            content: "搜索";
            font-size: 13px;
            color: #F9F0DA;
        }

        #login_register {
            float: right;
            margin-top: 15px;
            margin-right: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span></button>
                    <a class="navbar-brand" href="index.jsp"><strong>素材管理系统</strong></a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <span class="line_container">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="index.jsp"> <img src="image/project.png"/>媒体</a>
                            </li>
                            <li>
                                <a href="project_classify.jsp"> <img src="image/divide.png"/> 分类</a>
                            </li>
                        </ul>
                        <div id="search_project" class="search_bar">
                             <input id="my_input_index" placeholder="" name="cname" type="text">
                             <button type="submit" onclick="searchProject()"></button>
                        </div>
                    </span>
                    <a href="login.html"><span id="login_register" onclick="">登录/注册</span></a>
                </div>
            </nav>
        </div>
    </div>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>

        $(function () {
            var username = '<%= session.getAttribute("username")%>';
            console.log("username:::" + username);
            if (username != null && username != undefined) {
                $('#login_register').innerHTML = username;
            }
        })
    </script>
</div>
</body>
</html>
