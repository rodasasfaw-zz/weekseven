package com.lostandfound.demo.Controller;

import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import com.lostandfound.demo.Repository.AppUserRepository;
import com.lostandfound.demo.Repository.ItemRepository;
import com.lostandfound.demo.Repository.RoleRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class HomeController{
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/newuser")
    public String newUser(Model model){
        model.addAttribute("appUser", new AppUser());
        return "UserForm";
    }

    @PostMapping("/signup")
    public String processUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result){
        if(result.hasErrors())
        {
            return "registerform";
        }
        appUser.addRole(roleRepository.findByRoleName("USER"));
        appUserRepository.save(appUser);
        return "redirect:/login";
    }

    @GetMapping("/addlostitem")
    private String addLostItem(Model model){

        model.addAttribute("lostitem", new Item());

        return "lostitemform";
    }

    @PostMapping("/addlostitem")
    public String addPotLuckInfo(@Valid @ModelAttribute("lostitem") Item lostitems, Model model,BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return "lostitemform";
        }
        AppUser appUser = appUserRepository.findAppUserByUsername(auth.name());
        lostitems.addAppUser(appUser);
        itemRepository.save(lostitems);

        return "redirect:/";
    }

    @RequestMapping("/")
    public String homePage(){

        return "index";
    }
    @RequestMapping("/signup")
    public String signup(){

        return "registerform";
    }
    @RequestMapping("/login")
    public String login(){

        return "login";
    }
    @RequestMapping("/list")
    public String list(){

        return "lostandfoundlist";
    }
    @RequestMapping("/addlost")
    public String lostform(){

        return "lostitemform";
    }

}
