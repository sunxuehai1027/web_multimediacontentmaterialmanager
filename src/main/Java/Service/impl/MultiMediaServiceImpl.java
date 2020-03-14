package Service.impl;

import Constant.Constants;
import Dao.MultimediaMapper;
import Dto.Args;
import Entity.Multimedia;
import Service.IMultimediaService;
import Util.DateUtil;
import Util.FileUtil;
import Util.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import response.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

@Service
public class MultiMediaServiceImpl implements IMultimediaService {


    @Autowired
    private MultimediaMapper multimediaMapper;


    @Override
    public Multimedia getMultiMediaByNumber(Integer number) {
        return multimediaMapper.selectByPrimaryKey(number);
    }

    @Override
    public ArrayList<Multimedia> selectByPrimaryKeyOffset(Args args) {
        return multimediaMapper.selectByPrimaryKeyOffset( args);
    }

    @Override
    public ArrayList<Multimedia> selectByPrimaryByName(String name) {
        return multimediaMapper.selectByPrimaryByName(name);
    }

    @Override
    public int getAllMutilMediaCount() {
        return multimediaMapper.getAllMutilMediaCount();
    }

    @Override
    public int insert(Multimedia record) {
        return multimediaMapper.insert(record);
    }

    @Override
    public int delete(Integer number) {
        return multimediaMapper.deleteByPrimaryKey(number);
    }

    @Override
    public Multimedia insertSelective(Multimedia record) {
        Multimedia multimedia = multimediaMapper.insertSelective(record);
        return multimedia;
    }
    @Override
    public JsonResult uploadFile(MultipartFile file, HttpServletRequest request, HttpSession session) {

        //创建文件目录
        String path = Constants.SERVER_NATIVE_HEAD + Constants.FILE_STORAGE_PATH_HEAD;//文件的上传路径
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
            //boolean mkdirs = new File(CommonConsts.UPLOADURL).mkdirs();
        }
        //new文件一个对象
        Multimedia files = new Multimedia();
        //判断上传文件的大小
        if (file.getSize() > 1024 * 1024 * 100) {
            return JsonResult.createByErrorMessage("上传文件的大小不能大于100M");
        }
        //获取文件名
        String subFileName = file.getOriginalFilename();
        //String subFileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        String size = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (file.getSize() < 1024) {
            size = df.format((double) file.getSize()) + "B";
        } else if (file.getSize() < 1048576) {
            size = df.format((double) file.getSize() / 1024) + "KB";
        } else if (file.getSize() < 1073741824) {
            size = df.format((double) file.getSize() / 1048576) + "MB";
        } else {
            size = df.format((double) file.getSize() / 1073741824) + "GB";
        }
        files.setSize(size);
        files.setFilename(subFileName);
        //截取url，获取文件路径
        String path1 = path.substring(path.lastIndexOf("/") + 1);
        files.setPath(path1);
        String suffixName = files.getFilename().substring(files.getFilename().lastIndexOf(".") + 1).toUpperCase();
        int type = TypeUtil.getFileTypeBySuffix(suffixName);
        files.setType(type);
        files.setUploaddate(DateUtil.getCurrentDate());
        files.setUploaduserid(session.getAttribute("username").toString());
        files.setDescription(files.getDescription());
        files.setNumber(files.getNumber());
        files.setClick(files.getClick());
        files.setDownload(FileUtil.getUUID());
        files.setName(files.getName());
        return JsonResult.createBySuccess("上传成功");
    }
}
