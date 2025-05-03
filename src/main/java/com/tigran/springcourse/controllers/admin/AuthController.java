package com.tigran.springcourse.controllers.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AuthController {
    @GetMapping("/login_page")
    public String loginPage(){
        return "loginPage";
    }
    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession httpSession){
        boolean hasErrors = false;
        if (!login.equals("admin")){
            hasErrors = true;
            model.addAttribute("wrong_login","Wrong login");
        }
        if (!password.equals("admin")){
            hasErrors = true;
            model.addAttribute("wrong_password","Wrong password");
        }
        if (hasErrors){
            model.addAttribute("hasErrors",hasErrors);
            return "loginPage";
        }
        httpSession.setAttribute("admin","admin12345");
        return "redirect:appointments";
    }
    @GetMapping("/logout")
    public String logOut(HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null){
            httpSession.invalidate();
            return "redirect:/";
        }
        return "redirect:/";
    }
}
