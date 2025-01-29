package com.docker.helloworld;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GoogleLoginController {
    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                String username = oAuth2User.getAttribute("name");
                System.out.println("Authenticated username: " + username);
                model.addAttribute("user",username);
            }
            return "home";
        } else {
            return "login";
        }
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("THis is Login");
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();

        SecurityContextHolder.clearContext();

        String googleLogoutUrl = "https://accounts.google.com/Logout";

        return "redirect:" + googleLogoutUrl;
    }




}
