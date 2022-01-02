package com.wflin.Controller;

import com.wflin.Entity.Users;
import com.wflin.Respostory.UserRespostpy;
import com.wflin.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/User")
@ResponseBody
public class UserController {
    @Autowired
    UserRespostpy userRespostory;


    @GetMapping("findallUserSubPages/{page}")
    public Response findallUserSubPages(@PathVariable int page) {
        int sizeLimit = 5;
        List<Users> all = userRespostory.findAll();
        for (int i = 0; i < all.size(); i++) {
            Users user = all.get(i);
            if (user.getView() == 0) {
                i -= 1;
                all.remove(user);
            }
        }
        if (all.size() < sizeLimit) {
            sizeLimit = all.size();
        }
        // 进行封装分页,查询所有记录之前
        Pageable pageable = PageRequest.of(page - 1, sizeLimit);
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > all.size() ? all.size() : (start + pageable.getPageSize());
        PageImpl<Users> users = new PageImpl<>(all.subList(start, end), pageable, all.size());
        return new Response(200, "", users);
    }

    @GetMapping("/deluser")
    public Response deluser(HttpServletRequest request) {
        String id = request.getParameter("id");
        userRespostory.findByUserIdAndVew(Integer.valueOf(id), 0);
        return new Response(200, "用户注销成功");
    }

    @GetMapping("/search")
    public Response searchUser(HttpServletRequest request){
        String param = request.getParameter("Param");
        Users byEmail = userRespostory.findByEmail(param);
        Users byname = userRespostory.findByname(param);
        List<Users> users = new ArrayList();
        users.add(byname);
        users.add(byEmail);
        return new Response(200, "查询完成",users);
    }
}
