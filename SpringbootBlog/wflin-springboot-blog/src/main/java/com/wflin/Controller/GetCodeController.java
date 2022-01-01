package com.wflin.Controller;

import com.wflin.Config.MailTool;
import com.wflin.Respostory.UserRespostpy;
import com.wflin.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class GetCodeController {
    @Autowired
    UserRespostpy userRespostory;
//验证码
@RequestMapping("/getCode")
@ResponseBody
public Response getCode(String email , HttpServletRequest request){
        //        2424357107@qq.com
        //        1731659274@qq.com
        //        1731659274@qq.com
        //        1667932971@qq.com
        String status = request.getParameter("status");
        // 登陆验证码判断
        if("0".equals(status)&&(userRespostory.findByEmail(email)==null)) {
        System.out.println(email+"\t没有被注册，无法登陆\n");
        return new Response(200,"邮箱未注册");
        }
        // 注册验证码判断
        if("1".equals(status)&&(userRespostory.findByEmail(email)!=null)) {
        if (userRespostory.findByEmail(email)!=null) {
//              return "你已经注册了"+email+"账号";
        return new Response(200,"你已经注册了"+email+"账号");
        }
        }
        //随机生成一个验证码
        Integer code=new Random().nextInt(500);
        request.getSession().setAttribute("code",code.toString());
        request.getSession().setAttribute("email",email);
        String code1 =(String) request.getSession().getAttribute("code");
        String emai1l =(String) request.getSession().getAttribute("email");
        new Thread(
        new Runnable() {
        @Override
        public void run() {
                MailTool.send(email,code);
        }
        }
        ).start();
        return new Response(200,"验证码发送成功");
        }
}
