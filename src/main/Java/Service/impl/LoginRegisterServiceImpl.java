package Service.impl;

import Dao.UserMapper;
import Entity.User;
import Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user1 = userMapper.selectByPrimaryKey(user.getName());
        if (user1 == null) {
            return -1;
        } else if (user.getName().equals(user1.getName())) {
            return 1;
        } else if (user.getName().equals(user1.getName())) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }
}
