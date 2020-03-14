package Dao;

import Dto.Args;
import Entity.Multimedia;

import java.util.ArrayList;

public interface MultimediaMapper {

    int deleteByPrimaryKey(Integer number);

    int insert(Multimedia record);

    Multimedia insertSelective(Multimedia record);

    Multimedia selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Multimedia record);

    int updateByPrimaryKey(Multimedia record);

    ArrayList<Multimedia> selectByPrimaryKeyOffset(Args args);

    int getAllMutilMediaCount();

    ArrayList<Multimedia> selectByPrimaryByName(String name);
}