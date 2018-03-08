package com.weekseven.demo.Controller;

import com.weekseven.demo.Model.AppUser;
import com.weekseven.demo.Model.Articles;
import com.weekseven.demo.Repository.AppUserRepository;
import com.weekseven.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;


@Controller
public class HomeController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AppUserRepository appUserRepository;



//    @RequestMapping("/")
//    public String index(Model model, Authentication auth) {
//
//
//
//        return "index";
//
//    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String newUser(Model model) {
        model.addAttribute("newuser", new AppUser());

        return "registerform";
    }

    @PostMapping("/register")
    public String processUser(@Valid @ModelAttribute("newuser") AppUser newusers, BindingResult result) {
        if (result.hasErrors()) {
            return "registerform";
        }
        newusers.addRole(roleRepository.findByRoleName("USER"));
        appUserRepository.save(newusers);
        return "redirect:/login";
    }

        @GetMapping("/")
        public @ResponseBody String showNews(){
            RestTemplate restTemplate = new RestTemplate();
            Articles article = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&apiKey=b4a688706b10461dac7feec1fd2e3e70", Articles.class);

            return article.getValue().getTitle();
        }


}