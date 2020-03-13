var current_page = 1;

var page_size = 8;

/**
 * 初始化
 */
function init() {
    $("#search_project").css('display', 'block'); //显示
    getAllProject(0, page_size);
    getProjectCount();
}

/**
 * 获取总的项目数，显示分页条
 */
function getProjectCount() {

    $.ajax({
            url: address_head + "/api/GetAllMutilMediaCount",
            async: true,
            type: "POST",
            // 成功后开启模态框
            success: function (data) {
                if (data == "0" || data == 0) {
                    return;
                }
                var pages = Math.ceil(data / page_size);
                //console.log("pages:" + pages + "    " + data / page_size);
                $("#project_order").html("")
                if (pages > 1) {
                    if (current_page <= 1) {
                        $html = $("<li><a>上一页</a></li>");
                    } else {
                        $html = $(" <li onclick='getAllProject(" + (current_page - 2) * page_size + "," + page_size + ")'><a>上一页</a></li>");
                    }
                    $("#project_order").append($html);
                }
                for (var i = 1; i <= pages; i++) {// data.data指的是数组，数组里是对象，i为数组的索引
                    //console.log(data[i].title);
                    //var url = address_head + "/api/GetAllProjectByOffset?start=" + current_page + "&offset=" + page_size;
                    $html = $("<li onclick='getAllProject(" + (i - 1) * page_size + "," + page_size + ")'><a>" + i + "</a></li>");
                    $("#project_order").append($html);
                }
                $("#project_order").append(" <li><a>  " + current_page + "/" + pages + "  </a></li>");
                if (pages > 1) {
                    if (current_page < pages) {
                        $html = $(" <li onclick='getAllProject(" + (current_page) * page_size + "," + page_size + ")'><a>下一页</a></li>");
                    } else {
                        $html = $("<li><a>下一页</a></li>");
                    }
                    $("#project_order").append($html);
                }
            },
            error:
                function () {
                    alert("请求失败111");
                },
            dataType: "json"
        }
    )
    ;
}

/**
 * 搜索框
 */
function searchProject() {
    var index = $("#my_input_index").val();
    if (index == null || "" == index || index == undefined) {
        return;
    }
    $.ajax({
            url: address_head + "/api/GetMultiMediaByName",
            async: true,
            type: "POST",
            data: {"name": name},
            // 成功后开启模态框
            success: function (data) {
                $("#project_list").html("")
                for (i in data) {// data.data指的是数组，数组里是对象，i为数组的索引
                    //console.log(data[i].title);
                    $html = $("<li onclick='javascript:window.location.href=\"multimedia_detail.jsp?number=" + data[i].number + "\"'><div class=\"row clearfix\"><div class=\"col-md-12 column\">" +
                        "<div class=\"col-md-8 column\"><h3>[" + data[i].number + "] " + data[i].name + "</h3> " +
                        "</div><div class=\"col-md-4 column\"><span onclick=\"downloadFile(" + data[i].number + ")\"><img src=\"image/download.png\"/></a></span></div>" +
                        "<div class=\"row clearfix\"><div class=\"col-md-8 column\"><p class=\"line-limit-length\">&nbsp&nbsp&nbsp&nbsp" + data[i].description + "</p></div><div class=\"col-md-4 column\"><p>" +
                        "<small>" + data[i].uploaddate + "</small></p></div></div></div></div><hr></li>");
                    $("#project_list").append($html);
                }
            },
            error:
                function () {
                    alert("请求失败111");
                },
            dataType: "json"
        }
    )
    ;
}

/**
 * 根据起始位置和偏移量获取所有媒体信息
 * @param offset
 * @param length
 */
function getAllProject(offset, length) {
    console.log("index url:" + address_head + "/api/GetMultiMediaByOffsetAndLength?offset=" + offset + "&length=" + length);
    $.ajax({
            url: address_head + "/api/GetMultiMediaByOffsetAndLength?offset=" + offset + "&length=" + length,
            async: true,
            type: "POST",
            // 成功后开启模态框
            success: function (data) {
                current_page = offset < page_size ? 1 : Math.ceil(offset / page_size) + 1;
                getProjectCount();
                console.log("current_page1111:" + current_page + "             " + offset / page_size);
                $("#project_list").html("")
                for (i in data) {// data.data指的是数组，数组里是对象，i为数组的索引
                    //console.log(data[i].title);
                    $html = $("<li onclick='javascript:window.location.href=\"multimedia_detail.jsp?number=" + data[i].number + "\"'><div class=\"row clearfix\"><div class=\"col-md-12 column\">" +
                        "<div class=\"col-md-8 column\"><h3>[" + data[i].number + "] " + data[i].name + "</h3> " +
                        "</div><div class=\"col-md-4 column\"><span onclick=\"downloadFile(" + data[i].number + ")\"><img src=\"image/download.png\"/></a></span></div>" +
                        "<div class=\"row clearfix\"><div class=\"col-md-8 column\"><p class=\"line-limit-length\">&nbsp&nbsp&nbsp&nbsp" + data[i].description + "</p></div><div class=\"col-md-4 column\"><p>" +
                        "<small>" + data[i].uploaddate + "</small></p></div></div></div></div><hr></li>");
                    $("#project_list").append($html);
                }
            },
            error:
                function () {
                    alert("请求失败111");
                },
            dataType: "json"
        }
    )
    ;
}


function downloadFile(number) {
    console.log('开始下载：' + number)
    window.location.href = address_head + "/api/Download?number=" + number;
    /*$.ajax({
        url: address_head + "/api/Download",
        asyn: true,
        type: 'POST',
        data: {
            number: number,
        },
        cache: false,
        success: function (data) {
            console.log('服务器返回值：' + data)
        },
        error: function () {
            alert("请求失败");
        },
    });*/
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
    //console.log("types_html:" + types_html);
    return types_html;
}