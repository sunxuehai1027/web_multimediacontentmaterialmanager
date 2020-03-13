package Dao;

import Entity.User;
import org.apache.ibatis.annotations.Param;
import page.PageVo;


import java.util.List;

public interface UserMapper {

    //关键字删除用户

    int deleteByPrimaryKey(String name);

    //注册用户

    int insert(User record);

    //添加用户

    int insertSelective(User record);

    //查询用户

    User selectByPrimaryKey(String name);

    //修改用户密码

    int updateByPrimaryKeySelective(User record);

    //修改密码和类型

    int updateByPrimaryKey(User record);

    //查询用户集合
    List<User> selectAll(PageVo<User> pageVo);
}