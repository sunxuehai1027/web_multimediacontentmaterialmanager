package Controllers;

import Entity.User;
import Service.LoginRegisterService;
import Service.impl.LoginRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegisterController {

    @Autowired
    private LoginRegisterServiceImpl loginRegisterService;

    @RequestMapping(value = "/api/Login", produces = "text/plain;charset=utf-8")
    public String login(User user) {
        int result = loginRegisterService.login(user);
        System.out.println("登录结果：" + result);
        return result + "";
    }

    @RequestMapping(value = "/api/Register", produces = "text/plain;charset=utf-8")
    public String register(User user) {
        int result = loginRegisterService.register(user);
        System.out.println("注册结果：" + result);
        return result + "";
    }
}
