package Service;

import Dto.Args;
import Entity.Multimedia;

import java.util.ArrayList;

public interface IMultimediaService {

    Multimedia getMultiMediaByNumber(Integer number);

    ArrayList<Multimedia> selectByPrimaryKeyOffset(Args args);

    int getAllMutilMediaCount();

    int insert(Multimedia record);
}
