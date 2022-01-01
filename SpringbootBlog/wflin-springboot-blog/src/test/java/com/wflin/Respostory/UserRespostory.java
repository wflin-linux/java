package com.wflin.Respostory;

import com.wflin.Entity.Blog;
import com.wflin.Entity.Users;
import com.wflin.Util.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRespostory {
    @Autowired
    UserRespostpy userRespostory;
    @Autowired
    BlogRespostpy blogRespostpy;
    @Test
    public void user(){
        System.out.println(userRespostory.findAll());
    }
    @Test
    public void user2() {
        Users byEmail = userRespostory.findByEmail("2424357107@qq.com");
        System.out.println(byEmail!=null);
    }
    @Test
    public void user3(){
        int view = 1;
        Blog blog = new Blog(
                "wflin","","docker","docker","2","2","docker",view
        );
        blogRespostpy.save(blog);
        blogRespostpy.findAll();
        System.out.println(blogRespostpy.findAll());
    }
    @Test
    public void user4(){
        List<Blog> all = blogRespostpy.findAll();
        for (int i = 0; i < all.size() ; i++){
            Blog blog = all.get(i);
            if (blog.getView()==0){
                i -= 1;
                all.remove(blog);
            }
        }
        System.out.println(all);
    }
    @Test
    public void user5(){
        List<Blog> all = blogRespostpy.findBlogsByParam("docker");
        for (int i = 0; i < all.size() ; i++){
            Blog blog = all.get(i);
            if (blog.getView()==0){
                i -= 1;
                all.remove(blog);
            }
        }
        System.out.println(all);
    }
    @Test
    public void user6(){
        Pageable pageable = PageRequest.of(1, 5);
        List<Blog> all = blogRespostpy.findAll();
        for (int i = 0; i < all.size() ; i++){
            Blog blog = all.get(i);
            if (blog.getView()==0){
                i -= 1;
                all.remove(blog);
            }
        }
        // 进行封装分页,查询所有记录之前
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > all.size() ? all.size() : ( start + pageable.getPageSize());
        Page<Blog> blogs = new PageImpl<>(all.subList(start, end), pageable, all.size());
        Response response = new Response(200,"",blogs);
        System.out.println(blogs);
    }
    @Test
    void user7(){
        Users user = new Users("tom","111");
        System.out.println(user.toString());
    }

    @Test
    void user8(){
        int sizeLimit = 5;
        List<Users> all = userRespostory.findAll();
        for (int i = 0; i < all.size() ; i++) {
            Users user = all.get(i);
            if (user.getView() == 0) {
                i -= 1;
                all.remove(user);
            }
        }
        if (all.size() < sizeLimit) {
            sizeLimit = all.size();
        }
        System.out.println(all);
        // 进行封装分页,查询所有记录之前
        Pageable pageable = PageRequest.of(0, sizeLimit);
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > all.size() ? all.size() : ( start + pageable.getPageSize());
        PageImpl<Users> users = new PageImpl<>(all.subList(start, end), pageable, all.size());
        System.out.println(users);
    }
    @Test
    public void user9(){
        Optional<Users> byId = userRespostory.findById(1);
        System.out.println(byId.get());
    }
}
