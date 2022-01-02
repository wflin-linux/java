package com.wflin.Controller;

import com.wflin.Entity.vo.UserLoginVo;
import com.wflin.Respostory.UserRespostpy;
import com.wflin.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
//        2424357107@qq.com

@Controller
@CrossOrigin
public class Logincontroller {
    @Autowired
    UserRespostpy userRespostory;

    //页面控制
    @GetMapping({"/","/login"})
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    //登陆
    @ResponseBody
    @PostMapping("/tologin")
    public Response tologin(@RequestBody UserLoginVo userloginVo, HttpServletRequest request){
        String email = userloginVo.getEmail();
        Integer code = userloginVo.getCode();

        String emailAddress = (String)request.getSession().getAttribute("email");
        String codeId = (String) request.getSession().getAttribute("code");
        if (emailAddress.equals(email) && codeId.equals(String.valueOf(code))){
            // 登陆之后验证码弃用
            String code1 = "-1";
            request.getSession().setAttribute("code",code1);
            return new Response(200,"登陆成功");
        }else {
            return new Response(201,"验证码错误");
        }
    }
}
