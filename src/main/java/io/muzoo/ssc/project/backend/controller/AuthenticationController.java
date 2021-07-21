package io.muzoo.ssc.project.backend.controller;

import io.muzoo.ssc.project.backend.response.SimpleResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AuthenticationController {

    @GetMapping("/api/test")
    public String test(){
        return "If this shown. Login is successful";
    }

    @PostMapping("/api/login")
    public SimpleResponseDTO login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
                request.logout();
            }
            request.login(username,password);
            return SimpleResponseDTO
                            .builder()
                            .success(true)
                            .message("Login successfully")
                            .build();

        }catch (ServletException e){
            return
                    SimpleResponseDTO
                            .builder()
                            .success(false)
                            .message("Incorrect username or password")
                            .build();
        }
    }

    @GetMapping("/api/logout")
    public SimpleResponseDTO logout(HttpServletRequest request, HttpSession session){
        try{
            request.logout();
            return SimpleResponseDTO
                            .builder()
                            .success(true)
                            .message("You are successfully logout")
                            .build();


        } catch (ServletException e) {
            return SimpleResponseDTO
                            .builder()
                            .success(false)
                            .message("Failed to Logout")
                            .build();
        }
    }
}
