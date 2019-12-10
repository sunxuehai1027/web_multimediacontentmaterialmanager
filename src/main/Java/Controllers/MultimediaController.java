package Controllers;

import Constant.Constants;
import Dto.Args;
import Entity.Multimedia;
import Service.IMultimediaService;
import Util.DateUtil;
import Util.TypeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class MultimediaController {


    @Autowired
    private IMultimediaService iMultimediaService;

    @RequestMapping(value = "/api/GetMultiMediaByNumber", produces = "text/plain;charset=utf-8")
    public String getMultiMediaByNumber(Integer number) {

        Multimedia multimedia = iMultimediaService.getMultiMediaByNumber(number);
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

        System.out.println("插入的数据：" + multimedia.toString());
        multimedia.setUploaddate(DateUtil.getCurrentDate());
        // 获得文件后缀名称
        String suffixName = multimedia.getPath().substring(multimedia.getPath().lastIndexOf(".") + 1).toUpperCase();
        multimedia.setType(TypeUtil.getFileTypeBySuffix(suffixName));
        int result = iMultimediaService.insert(multimedia);
        System.out.println("插入数据的返回值:" + result);
        return result + "";
    }


    @RequestMapping(value = "/api/FileUpload")
    @ResponseBody
    public String update(@RequestParam(value = "fileName", required = false) MultipartFile file, HttpServletRequest request) throws IOException {

        System.out.println("开始接收文件...");
        String path = Constants.FILE_STORAGE_PATH_HEAD;//文件的上传路径

        System.out.println("path" + path);
        String fileName = file.getOriginalFilename();
        //获取文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得文件后缀名称
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 生成最新的uuid文件名称
        String newFileName = uuid + "." + suffixName;


        System.out.println("文件名" + fileName);
        String path1[] = path.split("\\\\");//对于特殊字符的分隔|  ^   $  *   .    (    )   \   /等都是正则表达式的一部分，只能通过前面加上\\进行转义。注意\要用三个\\\，也就是split（“\\\\”）
        String path2 = path1[path1.length - 1];//截取图片所在的文件夹名称
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
            return Constants.errorcode.ERROR_EMPTY_FILE_UPLOAD.getCode() + "";
        }

    }


}
