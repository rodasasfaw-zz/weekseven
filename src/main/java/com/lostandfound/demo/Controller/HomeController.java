package com.lostandfound.demo.Controller;

import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import com.lostandfound.demo.Model.Role;
import com.lostandfound.demo.Repository.AppUserRepository;
import com.lostandfound.demo.Repository.ItemRepository;
import com.lostandfound.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ItemRepository itemRepository;


    @RequestMapping("/")
    public String indexx(Model model, Authentication auth) {

        model.addAttribute("lostitem",itemRepository.findAllByStatus("Lost"));

        return "lostandfoundlist";


    }
    @RequestMapping("/adminlist")
    public String index(Model model, Authentication auth) {
        model.addAttribute("lostitem",itemRepository.findAll());

        return "adminlostandfoundlist";

    }

    @RequestMapping("/mylist")
    public String userlist(Model model, Authentication auth) {
        AppUser currentuser = appUserRepository.findAppUserByUsername(auth.getName());

        model.addAttribute("lostitem", itemRepository.findByAppUsers(currentuser));
        return "lostandfoundlist";


    }


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

    @GetMapping("/addlostitem")
    private String addLostItem(Model model) {

        model.addAttribute("lostitem", new Item());

        return "lostitemform";
    }

    @PostMapping("/showlostitem")
    public String showlostItem(@Valid @ModelAttribute("lostitem") Item items, Model model, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return "lostitemform";
        }

        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        itemRepository.save(items);
       appUser.additem(items);
        appUserRepository.save(appUser);

        model.addAttribute("lostitem", itemRepository.findAll());


        return "redirect:/mylist";
    }


    @RequestMapping("/addfoundlistitem/{id}")
    public String additemtofoundlist(@PathVariable("id") long id) {

        Item item = itemRepository.findOne(id);


        if(item.getStatus().equalsIgnoreCase("Lost"))
            item.setStatus("Found");
        else
            item.setStatus("Lost");
        itemRepository.save(item);

        return "redirect:/";
    }


    @RequestMapping("/edit/{id}")
    public String editlostItem(@PathVariable("id") long id, Model model, Authentication auth) {

        model.addAttribute("lostitem", itemRepository.findOne(id));

        return "lostitemform";
    }


    @RequestMapping("/delete/{id}")
    public String deleteLostItem(@PathVariable("id") long id, Model model, Authentication auth) {
        model.addAttribute("lostitem", itemRepository.findOne(id));

        return "redirect:/showlostitem";
    }

    @GetMapping("/adminaddforuser")
    public String adminaddforuser(Model model) {

        //Iterable<AppUser> loggedinuserslist = appUserRepository.findAll();
        model.addAttribute("lostitem", new Item());
        model.addAttribute("loggedinusers", appUserRepository.findAll());
        return "adminlostitemform";
    }
    @PostMapping("/showadminaddeditems")
    public String adminaddforusers( @Valid @ModelAttribute("lostitem") Item items,
                                    @RequestParam( "loggedinuser")long userid, BindingResult result,
                                    Model model) {

        if (result.hasErrors()) {

            return "adminlostitemform";
        }
        itemRepository.save(items);
        AppUser newappuser = appUserRepository.findAppUserById(userid);
newappuser.additem(items);

        appUserRepository.save(newappuser);

         model.addAttribute("lostitem", itemRepository.findAll());

        return "redirect:/";

    }
    @PostMapping("/searchlostitem")
    public String showSearchResults(HttpServletRequest request, Model model){
        String query = request.getParameter("search");
        model.addAttribute("search", query);
        model.addAttribute("searchlostitem", itemRepository.findAll());
        return "searchresult";
    }
}