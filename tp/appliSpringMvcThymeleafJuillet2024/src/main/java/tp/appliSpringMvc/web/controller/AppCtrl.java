package tp.appliSpringMvc.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/site/app")
public class AppCtrl {
    @RequestMapping("/toWelcome")
    String toWelcome(Model model) {
        model.addAttribute("message", "bienvenu(e)");
        model.addAttribute("title","welcome");
        return "welcome";
    }

    //version avec spring_security + spring_mvc
    @RequestMapping("/logout")
    public String clientLogout(Model model,
                               HttpServletRequest request, HttpServletResponse response,
                               SessionStatus sessionStatus) {
       /*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            //httpSession.invalidate() is indirectly called
        }
        */

        sessionStatus.setComplete();
        model.addAttribute("message", "session terminée");
        model.addAttribute("title","welcome");
        return "welcome";
    }
}