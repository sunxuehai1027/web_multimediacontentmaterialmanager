package Service;

import Entity.User;
import page.PageVo;
import response.JsonResult;

import java.util.List;

public interface LoginRegisterService {

    int login(User user);

    int register(User user);

    JsonResult deleteByPrimaryKey(String name);

    JsonResult insertSelective(User record);

    User selectByPrimaryKey(String name);

    JsonResult updateByPrimaryKeySelective(User record);

    JsonResult updateByPrimaryKey(User record);

    //查询用户集合
    PageVo<User> selectAll(PageVo<User> pageVo);
}
