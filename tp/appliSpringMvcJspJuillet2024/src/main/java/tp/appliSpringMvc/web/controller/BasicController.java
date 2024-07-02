package tp.appliSpringMvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/site/basic")
public class BasicController {
    @RequestMapping("helloWorld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello_world");
        return "displayBasicMessage" ;//.jsp ou .html(thymeleaf)
    }

    @RequestMapping("calculTva")
    public String calculTva(Model model,
                            @RequestParam(name="ht",defaultValue = "0") double ht,
                            @RequestParam(name="taux",defaultValue = "0") double taux) {
        double tva=ht*taux/100;
        double ttc=ht+tva;
        model.addAttribute("tva", tva);
        model.addAttribute("ttc", ttc);
        model.addAttribute("ht", ht); //pour réafficher la valeur saisie précédemment
        model.addAttribute("taux", taux);//pour réafficher la valeur saisie précédemment
        return "calcul_tva" ;//.jsp ou .html(thymeleaf)
    }
}