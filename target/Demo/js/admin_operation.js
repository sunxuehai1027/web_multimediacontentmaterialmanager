var index = 1;

function getInfromation(myindex) {
    index = myindex;
    if (myindex == 1) {
        $("#btn_project_list").attr("class", "panel-body");
        $("#btn_add_project").attr("class", "panel-footer");
        $("#btn_contact_us").attr("class", "panel-footer");
        $("#main_content").html("");
        $.ajax({
                url: address_head + "/api/GetMultiMediaByOffsetAndLength?offset=0&length=100",
                async: true,
                type: "POST",
                // 成功后开启模态框
                success: function (data) {
                    console.log(data);
                    $html = $("<ul id='project_list'></ul>");
                    $("#main_content").append($html);
                    for (i in data) {// data.data指的是数组，数组里是对象，i为数组的索引
                        $html = $("<li><div class=\"row clearfix\"><div class=\"col-md-9 column\"  onclick='javascript:window.location.href=\"multimedia_detail.jsp?number=" + data[i].number + "\"'>【" + data[i].number + "】 " + data[i].name +
                            "</div><div class=\"col-md-3 column\"><img onclick='modifyProject(" + data[i].number + ")' src='image/modify.png' title='修改'/>" +
                            "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img onclick='deleteProject(" + data[i].number + ")' src='image/delete.png' title='删除'/></div></div><hr></li>");
                        $("#project_list").append($html);
                    }
                },
                error:
                    function () {
                        alert("请求失败");
                    },
                dataType: "json"
            }
        )
        ;
    } else if (myindex == 2) {
        $("#btn_project_list").attr("class", "panel-footer");
        $("#btn_add_project").attr("class", "panel-body");
        $("#btn_contact_us").attr("class", "panel-footer");
        /*新增项目*/
        $("#main_content").html("");
        $html = $("<form id='uploadForm' method='post' action='" + address_head + "/api/InsertProject'><div class=\"form-group\"><label for=\"exampleInputEmail1\">素材名称</label>" +
            "<input type=\"text\" name='title' class=\"form-control\" id=\"exampleInputEmail1\" placeholder=\"请输入素材名称...\" /> </div> <div class=\"form-group\">" +
            "<label for=\"exampleInputPassword1\">素材说明</label><textarea type=\"text\" name='introduction' class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"请输入详细素材说明...\">" +
            "</textarea> </div><div><label for=\"exampleInputFile\">上传附件</label><input id='fileName' name='fileName' type=\"file\" id=\"exampleInputFile\" /><hr></div></form>" +
            "<button onclick='uploadMyFile()' type=\"submit\"  class=\"btn btn-default\">保存</button> ");
        $("#main_content").append($html);
        $('input[name="day"]').css("height", "300px");
        //showAllType();
    } else if (myindex == 3) {
        $("#btn_project_list").attr("class", "panel-footer");
        $("#btn_add_project").attr("class", "panel-footer");
        $("#btn_contact_us").attr("class", "panel-body");
        $("#main_content").html("");

    }
}

/**
 * 修改项目
 * @param id
 */
function modifyProject(number) {

}

/**
 * 删除项目
 * @param id
 */
function deleteProject(number) {
    if (number == null) {
        return;
    }
    var gnl = confirm("是否确认删除该素材?");
    if (gnl == true) {

    } else {
        return false;
    }
    $.ajax({
            url: address_head + "/api/DeleteMultiMediaByNumber?number=" + number,
            async: true,
            type: "POST",
            // 成功后开启模态框
            success: function (data) {
                console.log("deleteResult:" + data);
                if ("1" == data) {
                    getInfromation(1)
                } else {
                    alert("删除失败！")
                }
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

function showAllType() {
    $.ajax({
            url: address_head + "/api/GetAllType",
            async: true,
            type: "POST",
            // 成功后开启模态框
            success: function (data) {
                console.log(data);
                for (i in data) {// data.data指的是数组，数组里是对象，i为数组的索引
                    console.log(data[i]);
                    $html = $("<input type=\"checkbox\" value='" + data[i] + "' name='project_type_checked'/><span>" + data[i] + "&nbsp&nbsp&nbsp</span>");
                    $("#type_checkbox_list").append($html);
                }
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

//上传文件
function uploadMyFile() {

    var fileName = $("#fileName").val();
    var name = $("#exampleInputEmail1").val();
    var description = $("#exampleInputPassword1").val();

    if (name == null || name == undefined) {
        alert("请输入素材名称！")
        return false;
    }
    if (description == null || description == undefined) {
        alert("请填写素材描述！")
        return false;
    }
    if (fileName == null || fileName == undefined) {
        alert("请上传附件！")
        return false;
    }
    var formData = new FormData($('#uploadForm')[0]);//序列化表单，将上传类型设置为文件对象
    if (fileName == "") {
        alert("请选择文件进行上传");
        return false;
    } else {
        $.ajax({
            type: "POST",
            url: address_head + "/api/FileUpload",
            data: formData,
            cache: false,//文件不设置缓存
            processData: false,//数据不被转换为字符串
            contentType: false,//上传文件时使用，避免 JQuery 对其操作\
            dataType: 'text',
            success: function (data) {
                console.log("返回的文件名:" + data)
                uploadMyFileConfig(data, name, description);
            },
            error: function () {
                console.log("上传失败")
                alert("上传失败");
            }
        });

        /*$.ajaxFileUpload({
            fileElementId: 'fileName',    //需要上传的文件域的ID，即<input type="file">的ID。
            url: address_head + "/api/FileUpload", //后台方法的路径
            type: 'post',   //当要提交自定义参数时，这个参数要设置成post
            secureuri: false,   //是否启用安全提交，默认为false。
            async: true,   //是否是异步
            success: function (data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                console.log("返回的文件名:" + data)
                uploadMyFileConfig(data, name, description);
            },
            error: function (data, status, e) {  //提交失败自动执行的处理函数。
                console.log("上传失败")
                alert("上传失败");
            }
        });*/
    }
}

//上传文件配置信息，插入数据库
function uploadMyFileConfig(filename, name, description) {

    $.ajax({
            url: address_head + "/api/InsertMultiMedia",
            async: true,
            type: "POST",
            dataType: 'json',
            data: {
                name: name,
                filename: filename,
                description: description,
            },
            cache: false,
            success: function (data) {
                console.log("result" + data);
                if (data == "1") {
                    getInfromation(1)
                } else {
                    alert("上传失败");
                }
            },
            error:
                function () {
                    alert("请求失败");
                },
        }
    );
}
