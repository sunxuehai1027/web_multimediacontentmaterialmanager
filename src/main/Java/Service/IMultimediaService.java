package Service;

import Dto.Args;
import Entity.Multimedia;
import org.springframework.web.multipart.MultipartFile;
import response.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public interface IMultimediaService {

    Multimedia getMultiMediaByNumber(Integer number);

    ArrayList<Multimedia> selectByPrimaryKeyOffset(Args args);

    ArrayList<Multimedia> selectByPrimaryByName(String name);

    int getAllMutilMediaCount();

    int insert(Multimedia record);

    int delete(Integer number);

    Multimedia insertSelective(Multimedia record);

    JsonResult uploadFile(MultipartFile file, HttpServletRequest request, HttpSession session);
}
