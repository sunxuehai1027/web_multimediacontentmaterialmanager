package Service.impl;

import Dao.MultimediaMapper;
import Dto.Args;
import Entity.Multimedia;
import Service.IMultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
