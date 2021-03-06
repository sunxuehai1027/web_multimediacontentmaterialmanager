package Controllers;

import Entity.User;
import Service.impl.LoginRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import page.PageVo;
import response.JsonResult;

import javax.servlet.http.HttpSession;

@RestController
public class LoginRegisterController {

    @Autowired
    private LoginRegisterServiceImpl loginRegisterService;

    /**
     * @author sunxuehai
     * @description 用户登录
     * @date 2020/3/9
     * @param user 用户
     */
    @RequestMapping(value = "/api/Login", produces = "text/plain;charset=utf-8")
    public String login(User user, HttpSession session) {
        System.out.println("收到的用户名以及密码：" + user.toString());
        int result = loginRegisterService.login(user);
        System.out.println("登录结果：" + result);
        if (result == 1 || result == 11) {
            session.setAttribute("username", user.getName());
        }
        return result + "";
    }

    /**
     * @author sunxuehai
     * @description 用户登出
     * @date 2020/3/9
     * @param user 用户
     */
    @RequestMapping(value = "/api/LoginOut", produces = "text/plain;charset=utf-8")
    public String loginOut(User user, HttpSession session) {
        System.out.println("退出登录收到的用户名：" + user.getName());
        session.setAttribute("username", null);
        return 1 + "";
    }

    /**
     * @author sunxuehai
     * @description 用户注册
     * @date 2020/3/9
     * @param user 用户
     */
    @RequestMapping(value = "/api/Register", produces = "text/plain;charset=utf-8")
    public String register(User user) {
        user.setType(0);
        int result = loginRegisterService.register(user);
        System.out.println("注册结果：" + result);
        return result + "";
    }
    /**
     * @author sunxuehai
     * @description 查询用户集合
     * @date 2020/3/9
     * @param pageVo 用户
     */
    @RequestMapping(value = "/api/selectUser", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public JsonResult selectAll(PageVo<User> pageVo) {
        PageVo<User> userPageVo = loginRegisterService.selectAll(pageVo);
        return JsonResult.createBySuccess("获取用户集合",userPageVo);
    }



}
