package Service.impl;

import Dao.UserMapper;
import Entity.User;
import Service.LoginRegisterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import page.PageVo;
import response.JsonResult;

import java.util.List;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 返回-1表示数据传入错误 0表示用户不存在  1表示用户存在
     *
     * @param user
     * @return
     */
    @Override
    public int login(User user) {
        String name = user.getName();
        User user1 = userMapper.selectByPrimaryKey(name);
        if (user1 == null) {
            return -1;
        } else {
            if (user1.getType() == 2) {
                //管理员用户登录成功
                return 11;
            } else {
                //普通登录成功
                return 1;
            }

        }
    }

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public JsonResult deleteByPrimaryKey(String name) {
        userMapper.deleteByPrimaryKey(name);
        return JsonResult.createBySuccess();
    }



    @Override
    public JsonResult insertSelective(User record) {
        return null;
    }

    @Override
    public User selectByPrimaryKey(String name) {
        return null;
    }

    @Override
    public JsonResult updateByPrimaryKeySelective(User record) {
        return null;
    }

    @Override
    public JsonResult updateByPrimaryKey(User record) {
        return null;
    }

    @Override
    public PageVo<User> selectAll(PageVo<User> pageVo) {
        PageHelper.startPage(pageVo.getPageIndex(), pageVo.getPageSize());
        List<User> lists = userMapper.selectAll(pageVo);
        PageInfo page = new PageInfo(lists);
        pageVo.setData(lists);
        pageVo.setTotal((int) page.getTotal());
        return pageVo;
    }


}
