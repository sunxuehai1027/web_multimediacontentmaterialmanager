function getProjectById(id) {
    $.ajax({
            url: address_head + "/api/GetProjectById?id=" + id,
            async: true,
            type: "POST",
            // 成功后开启模态框
            success: function (data) {
                console.log(data);
                var pic = "";
                if (data.demonstration == null || data.demonstration == undefined || data.demonstration == "") {
                    pic =  "<div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>图片演示</strong></h3><hr><p>暂无图片演示</p></div></div>";
                } else {
                    pic = "<div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>图片演示</strong></h3><hr>"+getTypeHtml1(data.demonstration)+"</div>";
                }
                var video="";
                if(data.videopath==null || data.videopath==undefined || data.videopath===""){
                    video="<div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>视频演示</strong></h3><hr><p>暂无视频演示</p></div></div>";
                }else{
                    video=" <div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>视频演示</strong></h3><hr><video id=\"myvideo\"  class=\"video-js vjs-big-play-centered\" align=\"middle\" controls=\"true\"  " +
                        "preload=\"auto\" poster=\"\" data-setup=\"{}\" > <source src=\""+data.videopath+"\" type=\"video/mp4\" ></source><p class=\"vjs-no-js\">不支持本浏览器 " +
                        "<a href=\"http://videojs.com/html5-video-support/\" target=\"_blank\"></a></p>请将浏览器设置为极速模式，使用html5播放</a></p></video></div>";
                }
                $html = $("<ul class=\"breadcrumb\">\n" +
                    "                            <li>\n" +
                    "                                <a href=\"index.jsp\">首页</a>\n" +
                    "                            </li>\n" +
                    "                            <li>\n" +
                    "                                <a href=\"index.jsp\">项目</a>\n" +
                    "                            </li>\n" +
                    "                            <li class=\"active\">\n" +
                    "                                " + data.title + "\n" +
                    "                            </li>\n" +
                    "                        </ul>\n" +
                    "                        <h2 class='mytitle'>\n" +
                    "                            " + data.title + "\n" +
                    "                        </h2>\n" +
                    "                        <div id='tag_and_time1' class=\"row clearfix\">\n" +
                    "                            <div class=\"col-md-8 column\">\n" +
                    "                                " + getTypeHtml(data.typename) + "\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-md-4 column\">\n" +
                    "                                <p>\n" +
                    "                                    " + data.releasetime + " </p>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"row clearfix\">\n" +
                    "                            <div class=\"col-md-12 column\">\n" +
                    "                                <h3>\n<strong>" +
                    "                                    需求</strong>\n" +
                    "                                </h3>\n" +
                    "                                <hr>\n" +
                    "                                <p class='introduction'>\n" +
                    "                                   \n" +
                    "                                        " + data.introduction + "\n" +
                    "                                </p>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +video+ pic+
                    "                        <blockquote>\n" +
                    "                            <p class='myremind'>\n转载请注明：转载自毕业项目 本文链接地址 http://www.liuyuesheji.com" +
                    "                            </p> " +
                    "                        </blockquote>");
                $("#project_detail_content").append($html);
            },
            error:
                function () {
                    alert("请求失败");
                },
            dataType: "json"
        }
    )
    ;
}


function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 *将类型的字符串根据逗号拆分显示
 * @param types
 */
function getTypeHtml(types) {
    var typename, types_html = "";
    if (types != null && types != undefined) {
        typename = types.split("，");
        for (var i in  typename) {
            types_html = types_html + "<span class=\"label label-default\">" + typename[i] + "</span>&nbsp";
        }
    } else {
        types_html = "";
    }
    console.log("types_html:" + types_html);
    return types_html;
}

/**
 *将类型的字符串根据逗号拆分显示
 * @param types
 */
function getTypeHtml1(types) {
    var typename, types_html = "";
    if (types != null && types != undefined) {
        typename = types.split("，");
        for (var i in  typename) {
            types_html = types_html + "<img class='demon_picture' src='" +typename[i] + "'/> \n\n";
        }
    } else {
        types_html = "";
    }
    console.log("types_html:" + types_html);
    return types_html;
}
