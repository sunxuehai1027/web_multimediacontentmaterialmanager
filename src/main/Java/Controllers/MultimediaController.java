package Controllers;

import Constant.Constants;
import Constant.ErrorCode;
import Constant.TypeAndFolder;
import Dto.Args;
import Entity.Multimedia;
import Service.IMultimediaService;
import Util.DateUtil;
import Util.TypeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class MultimediaController {


    @Autowired
    private IMultimediaService iMultimediaService;

    @RequestMapping(value = "/api/DeleteMultiMediaByNumber", produces = "text/plain;charset=utf-8")
    public String deleteMultiMediaByNumber(Integer number) {

        int result = iMultimediaService.delete(number);
        System.out.println("删除结果:" + result);
        return result + "";
    }

    @RequestMapping(value = "/api/GetMultiMediaByName", produces = "text/plain;charset=utf-8")
    public String getMultiMediaByName(String name) {

        ArrayList<Multimedia> multimedias = iMultimediaService.selectByPrimaryByName(name);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(multimedias);
        System.out.println("模糊查询结果为:" + json.toString());
        return json;
    }

    @RequestMapping(value = "/api/GetMultiMediaByNumber", produces = "text/plain;charset=utf-8")
    public String getMultiMediaByNumber(Integer number) {

        Multimedia multimedia = iMultimediaService.getMultiMediaByNumber(number);
        String path = multimedia.getPath() + "/" + multimedia.getFilename();
        multimedia.setPath(path);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(multimedia);
        System.out.println("multimedia:" + json.toString());
        return json;
    }

    @RequestMapping(value = "/api/GetMultiMediaByOffsetAndLength", produces = "text/plain;charset=utf-8")
    public String getMultiMediaByOffsetAndLength(int offset, int length) {
        Args args = new Args();
        args.arg1 = offset;
        args.arg2 = length;
        ArrayList<Multimedia> multimedias = iMultimediaService.selectByPrimaryKeyOffset(args);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(multimedias);
        System.out.println("multimedias:" + json.toString());
        return json;
    }

    @RequestMapping(value = "/api/GetAllMutilMediaCount", produces = "text/plain;charset=utf-8")
    public String getAllMutilMediaCount() {
        int count = iMultimediaService.getAllMutilMediaCount();
        System.out.println("getAllMutilMediaCount:" + count);
        return count + "";
    }

    @RequestMapping(value = "/api/InsertMultiMedia", produces = "text/plain;charset=utf-8")
    public String insertMultiMedia(Multimedia multimedia) {

        //multimedia.setUploaduserid(session.getAttribute("username").toString());
        System.out.println("插入的数据：" + multimedia.toString());
        multimedia.setUploaddate(DateUtil.getCurrentDate());
        if (multimedia.getFilename() == null) {
            return ErrorCode.ERROR_EMPTY_FILE_UPLOAD.getCode() + "";
        }
        // 获得文件后缀名称
        String suffixName = multimedia.getFilename().substring(multimedia.getFilename().lastIndexOf(".") + 1).toUpperCase();
        int type = TypeUtil.getFileTypeBySuffix(suffixName);
        multimedia.setType(type);
        String path = Constants.FILE_STORAGE_PATH_HEAD + "/" + TypeAndFolder.getMsgByCode(type);
        multimedia.setPath(path);
        int result = iMultimediaService.insert(multimedia);
        System.out.println("插入数据的返回值:" + result);
        return result + "";
    }


    @RequestMapping(value = "/api/FileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam(value = "fileName", required = false) MultipartFile file) throws IOException {

       /* if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            return ErrorCode.ERROR_EMPTY_NO_SIGN.getCode() + "";
        } else if (session.getAttribute("username") != "admin") {
            return ErrorCode.ERROR_EMPTY_NOT_ADMIN.getCode() + "";
        }*/
        System.out.println("开始接收文件...");
        String path = Constants.SERVER_NATIVE_HEAD + Constants.FILE_STORAGE_PATH_HEAD;//文件的上传路径
        System.out.println("path" + path);
        String fileName = file.getOriginalFilename();
        //获取文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得文件后缀名称
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 生成最新的uuid文件名称
        String newFileName = uuid + "." + suffixName;
        int type = TypeUtil.getFileTypeBySuffix(suffixName);
        path = path + "/" + TypeAndFolder.getMsgByCode(type);
        path = path.replaceAll("/", "\\\\");
        System.out.println("文件名" + fileName + ",文件路径" + path);
        //String path1[] = path.split("\\\\");//对于特殊字符的分隔|  ^   $  *   .    (    )   \   /等都是正则表达式的一部分，只能通过前面加上\\进行转义。注意\要用三个\\\，也就是split（“\\\\”）
        //String path2 = path1[path1.length - 1];//截取图片所在的文件夹名称
        File dir = new File(path, newFileName);//将指定文件上传到指定的目录下
        /**
         * 如果文件夹不存在，自动创建该文件夹
         */
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        if (!file.isEmpty()) {
            file.transferTo(dir);
            return newFileName;
        } else {
            System.out.println("空文件");
            return ErrorCode.ERROR_EMPTY_FILE_UPLOAD.getCode() + "";
        }

    }

    @RequestMapping("/api/Download")
    public void down(Integer number, HttpServletResponse response) throws Exception {
        Multimedia multimedia = iMultimediaService.getMultiMediaByNumber(number);
        System.out.println("开始下载文件..." + multimedia.toString());
        String path = Constants.SERVER_NATIVE_HEAD + multimedia.getPath() + "/" + multimedia.getFilename();
        path = path.replaceAll("/", "\\\\");
        System.out.println("path:" + path);
        //转码，免得文件名中文乱码
        String filename = URLEncoder.encode(multimedia.getFilename(), "UTF-8");
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("下载文件不存在");
            return;
        }
        response.setHeader("Content-type", "application-download");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(file);
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte buffer[] = new byte[1024]; // 缓冲区的大小设置是个迷  我也没搞明白
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        // 关闭输出流
        out.close();
        System.out.println("下载完成");
    }


}
