package com.wflin.Controller;

import com.wflin.Entity.Users;
import com.wflin.Respostory.UserRespostpy;
import com.wflin.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    UserRespostpy userRespostory;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/toregister")
    @ResponseBody
    public Response toregiter(HttpServletRequest request){
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String codeID = request.getParameter("code");;
        String code =(String) request.getSession().getAttribute("code");
        if (code.equals(codeID)){
            String code1 = "-1";
            request.getSession().setAttribute("code",code1);
            Users users = new Users(name,email);
            userRespostory.save(users);
            return new Response(200,"账号注册成功");
        }
        return new Response(200,"验证码错误");
    }
}
