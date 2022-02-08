package se.kth.iv1201.project.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Scope("session")
public class UserController {
        static final String DEFAULT_PAGE_URL = "/";
        static final String HOME_PAGE_URL = "/application";

    @Autowired
    /**
     * No page is specified, redirect to the login page.
     *
     * @return A response that redirects the browser to the welcome page.
     */
    @GetMapping("/")
    public String login() {
        return  "login";
    }
    @GetMapping("/signup")
    public String signup() {
        return  "signup";
    }
    @PostMapping("/application")
    public String application() {
        return  "application";
    }
}