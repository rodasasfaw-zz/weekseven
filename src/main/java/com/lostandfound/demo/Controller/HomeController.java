package com.lostandfound.demo.Controller;

import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import com.lostandfound.demo.Repository.AppUserRepository;
import com.lostandfound.demo.Repository.ItemRepository;
import com.lostandfound.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class HomeController{
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ItemRepository itemRepository;


    @RequestMapping("/")
    public String index(){
        return "index";
    }


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
    public String processUser(@Valid @ModelAttribute("newuser") AppUser newusers, BindingResult result){
        if(result.hasErrors())
        {
            return "registerform";
        }
        newusers.addRole(roleRepository.findByRoleName("USER"));
        appUserRepository.save(newusers);
        return "redirect:/login";
    }

    @GetMapping("/addlostitem")
    private String addLostItem(Model model){

        model.addAttribute("lostitem", new Item());

        return "lostitemform";
    }

    @PostMapping("/showlostitem")
    public String showlostItem(@Valid @ModelAttribute("lostitem") Item items, Model model,BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return "lostitemform";
        }

        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        itemRepository.save(items);
        appUser.additem(items);
        appUserRepository.save(appUser);

        model.addAttribute("lostitem", itemRepository.findAll());


        return "lostandfoundlist";
    }




    @GetMapping("/addfoundlistitem/{id}")
    public String additemtofoundlist(@PathVariable("id") long id, Model model, Authentication auth){

        Item item = itemRepository.findOne(id);

        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        appUser.additem(item);
        item.setStatus("Found");
        model.addAttribute("fitemslist", itemRepository.findOne(id));
        itemRepository.save(item);
        appUserRepository.save(appUser);
        model.addAttribute("lostitem", itemRepository.findAll());
        return "lostitemlist";
    }

    @GetMapping("/list")



    @RequestMapping("/edit/{id}")
    public String editlostItem(@PathVariable("id") long id, Model model, Authentication auth){

        model.addAttribute("lostitem",itemRepository.findOne(id));

        return "lostitemform";
    }


    @RequestMapping("/delete/{id}")
    public String deleteLostItem(@PathVariable("id") long id , Model model, Authentication auth){
        model.addAttribute("lostitem",itemRepository.findOne(id));

        return "redirect:/";
    }

}
