function getProjectByNumber(number) {
    $.ajax({
            url: address_head + "/api/GetMultiMediaByNumber",
            async: true,
            type: "POST",
            data: {
                number: number,
            },
            // 成功后开启模态框
            success: function (data) {
                console.log(data);
                var content = "";
                if (data.path == null || data.path == undefined || data.path === "") {
                    content = "<div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>素材浏览</strong></h3><hr><p>服务器文件丢失，暂无内容演示</p></div></div>";
                } else {
                    if (data.type == 1) {
                        content = " <div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>文本演示</strong></h3><hr></div></div>";
                    } else if (data.type == 2) {
                        content = " <div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>图片演示</strong></h3><hr><img class='img_show' src='" + address_head + "/api/Download?number="+data.number + "'/></div></div>";
                    } else if (data.type == 3) {
                        content = " <div class=\"row clearfix\"><div class=\"col-md-12 column\"><h3><strong>视频演示</strong></h3><hr><video id=\"myvideo\"  class=\"video-js vjs-big-play-centered\" align=\"middle\" controls=\"true\"  " +
                            "preload=\"auto\" poster=\"\" data-setup=\"{}\" > <source src='" + address_head + "/api/Download?number="+data.number + "' type=\"video/mp4\" ></source><p class=\"vjs-no-js\">不支持本浏览器 " +
                            "<a href=\"" + data.path + "\" target=\"_blank\"></a></p>请将浏览器设置为极速模式，使用html5播放</a></p></video></div>";
                    }
                }
                $html = $("<ul class=\"breadcrumb\">\n" +
                    "                            <li>\n" +
                    "                                <a href=\"index.jsp\">首页</a>\n" +
                    "                            </li>\n" +
                    "                            <li>\n" +
                    "                                <a href=\"index.jsp\">素材</a>\n" +
                    "                            </li>\n" +
                    "                            <li class=\"active\">\n" + data.name + "\n" +
                    "                            </li>\n" +
                    "                        </ul>\n" +
                    "                        <h2 class='mytitle'>\n【" + data.number + "】" + data.name + "\n" +
                    "                        </h2>\n" +
                    "                        <div id='tag_and_time1' class=\"row clearfix\">\n" +
                    "                            <div class=\"col-md-8 column\">\n" +
                    "                                " + "" + "\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-md-4 column\">\n" +
                    "                                <p>\n" +
                    "                                    " + data.uploaddate + " </p>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"row clearfix\">\n" +
                    "                            <div class=\"col-md-12 column\">\n" +
                    "                                <h3>\n<strong>" +
                    "                                    素材描述</strong>\n" +
                    "                                </h3>\n" +
                    "                                <hr>\n" +
                    "                                <p class='introduction'>\n" +
                    "                                   \n" +
                    "                                        " + data.description + "\n" +
                    "                                </p>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" + content +
                    "<div class=\"row clearfix\">\n" +
                    "                            <div class=\"col-md-12 column\">\n" +
                    "                                <h3>\n<strong>" +
                    "                                    下载地址</strong>\n" +
                    "                                </h3>\n" +
                    "                                <hr>\n" +
                    "                                <p class='introduction'>\n" +
                    "                                   \n<span onclick=\"downloadFile(" + data.number + ")\">点此下载</span><br><br><br>\n" +
                    "                                </p>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <blockquote>\n" +
                    "                            <p class='myremind'>\n转载请注明：转载自毕业项目 本文链接地址 " +
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

function downloadFile(number) {
    console.log('开始下载：' + number)
    window.location.href = address_head + "/api/Download?number=" + number;
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
