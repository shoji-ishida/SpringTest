package jp.catalyna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ishida on 2017/02/28.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/spring")
    public String home(Model model)
    {
        model.addAttribute("title", "Title");
        model.addAttribute("body", "Body");
        return "home";
    }
}
