package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.ResourceService;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import edu.just.resource_management_system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/SignIn")
    public String SignIn(User user){
        //添加新用户信息
        userService.saveUser(user);
        //返回首页
        return "home";
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @param model 用于传递信息给视图
     * @return 跳转到主页或者显示错误信息
     */
    @PostMapping("/login1")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("userPassword") String password, Model model) {

        // 加密密码
        String encryptedPassword = MD5Util.encryptToMD5(password);

        // 调用服务进行登录验证
        User user = userService.login(userName, encryptedPassword);

        if (user != null) {
            // 登录成功，跳转到主页
            // 可以把用户信息添加到 Model 里
            model.addAttribute("user", user);
            return "home";
        } else {
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "用户名或密码错误");
            // 还停留在登录页面
            model.addAttribute("error", "用户名或密码错误，请重新输入");
            return "index";
        }
    }

    /**
     * @param keyword
     * @param tagName
     * @param languageName
     * @param modelMap
     * @return
     */
    @PostMapping("/search")
    public String searchIdAndKeyword(@RequestParam("keyword")String keyword,
                                     @RequestParam("tagName")String tagName,
                                     @RequestParam("languageName")String languageName,
                                     ModelMap modelMap){
        List<Resource> resources = resourceService.findResourcesByIdsAndKeyword(tagName, languageName, keyword);
        modelMap.addAttribute("resources",resources);
        return "results";
    }

    /**
     * 用户实现对语言的查询 点击语言相关的资源 然后跳转到查询结果页
     * @return
     */
    @RequestMapping("/search/{language}")
    public String searchLanguage(Model model){
        //跳转到查询结果页
        return "";
    }
}
