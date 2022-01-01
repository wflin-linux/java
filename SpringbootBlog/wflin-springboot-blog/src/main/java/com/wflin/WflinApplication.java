package com.wflin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class WflinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WflinApplication.class, args);

    }
    @RequestMapping(value = "/index")
    public String one(Model m){
        m.addAttribute("msg","你大爷");
        m.getAttribute("id");
        System.out.println(m.getAttribute("id"));

        return "index.html";
    }

}
