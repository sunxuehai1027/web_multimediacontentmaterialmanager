package Service;

import Dto.Args;
import Entity.Multimedia;

import java.util.ArrayList;

public interface IMultimediaService {

    Multimedia getMultiMediaByNumber(Integer number);

    ArrayList<Multimedia> selectByPrimaryKeyOffset(Args args);

    ArrayList<Multimedia> selectByPrimaryByName(String name);

    int getAllMutilMediaCount();

    int insert(Multimedia record);

    int delete(Integer number);
}
