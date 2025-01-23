package com.docker.helloworld;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class GoogleLoginController {
    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                String username = oAuth2User.getAttribute("name");  // You can use "name" or "given_name" depending on the provider
                System.out.println("Authenticated username: " + username);
                model.addAttribute("user",username);
            }
            return "home";  // Return your home page
        } else {
            return "login";  // Redirect to login if not authenticated
        }
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("THis is Login");
        return "login";  // Return your custom login page (login.html)
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the session to log out from your Spring Boot app
        request.getSession().invalidate();

        // Clear Spring Security context
        SecurityContextHolder.clearContext();

        // Redirect to Google's logout page, which will log out the user from their Google account
        String googleLogoutUrl = "https://accounts.google.com/Logout";

        return "redirect:" + googleLogoutUrl;
    }




}
