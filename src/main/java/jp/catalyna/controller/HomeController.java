package jp.catalyna.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ishida on 2017/02/28.
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @RequestMapping(value = "/spring")
    public String home(Model model)
    {
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        model.addAttribute("title", "Title");
        model.addAttribute("body", "Body");
        return "home";
    }
}
