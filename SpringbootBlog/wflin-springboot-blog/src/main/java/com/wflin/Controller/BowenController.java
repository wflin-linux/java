package com.wflin.Controller;

import com.wflin.Entity.Blog;
import com.wflin.Respostory.BlogRespostpy;
import com.wflin.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Blog")
public class BowenController {

    @Autowired
    BlogRespostpy blogRespostpy;

    @GetMapping("/findallBlogSubPages/{page}")
    public Response findSubPages(@PathVariable int page){
        int sizeLimit = 5;
        List<Blog> all = blogRespostpy.findAll();
        for (int i = 0; i < all.size() ; i++) {
            Blog blog = all.get(i);
            if (blog.getView() == 0) {
                i -= 1;
                all.remove(blog);
            }
        }
        if (all.size() < sizeLimit) {
            sizeLimit = all.size();
        }
        // 进行封装分页,查询所有记录之前
        Pageable pageable = PageRequest.of(page-1, sizeLimit);
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > all.size() ? all.size() : ( start + pageable.getPageSize());
        Page<Blog> blogs = new PageImpl<>(all.subList(start, end), pageable, all.size());
        return new Response(200,"",blogs);
    }

    @GetMapping("/findBlogsByParam")
    public Response findBlogsByParam(HttpServletRequest request){
        String param = request.getParameter("Param");
        List<Blog> all = blogRespostpy.findBlogsByParam(param);
        for (int i = 0; i < all.size() ; i++){
            Blog blog = all.get(i);
            if (blog.getView()==0){
                i -= 1;
                all.remove(blog);
            }
        }
        if (all.size() == 0) {
            all = blogRespostpy.findAll();
            return  new Response(200, "没有相关内容", all);
        }
        Pageable pageable = PageRequest.of(1, 5);
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > all.size() ? all.size() : ( start + pageable.getPageSize());
        Page<Blog> blogs = new PageImpl<>(all.subList(start, end), pageable, all.size());
        return  new Response(200, "查询结束", all);
    }

    @GetMapping("/del")
    @ResponseBody
    public Response del(HttpServletRequest request){
        String id = request.getParameter("id");
        blogRespostpy.findByBlogIdAndVew(Integer.valueOf(id),0);
        return new Response(200,"删除成功");
    }

    @GetMapping("/editView")
    @ResponseBody
    public Response editView(String id) {
        Optional<Blog> byId = blogRespostpy.findById(Integer.valueOf(id));
        return new Response(200, "",byId.get());
    }
    @ResponseBody
    @PostMapping("/save")
    public Response save(@RequestBody Blog blog){
        System.out.println(blog);
        blogRespostpy.save(blog);
        return new Response(200,"博文添加成功");
    }
}
