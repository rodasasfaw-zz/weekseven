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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class HomeController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ItemRepository itemRepository;


    @RequestMapping("/")
    public String index(Model model, Authentication auth) {

        model.addAttribute("lostitem",itemRepository.findAllByStatus("Lost"));

        return "lostitemlist";

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

    @PostMapping("/addlostitem")
    public String addlostItems(@Valid @ModelAttribute("lostitem") Item items, Model model, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return "lostitemform";
        }

        if (items.getImage().isEmpty()){
            if(items.getCategory().equals("Clothes")){
                items.setImage("https://getthelabel.btxmedia.com/pws/client/images/landing-pages/mens/2017/310717/knitwear.jpg");
            }
            else if (items.getCategory().equals("Pets")){
                items.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxb9vQmD4Pn_OapoWNSjzf_pT5FZLa4pg60oqSuRs6QAavAAKc");
            }
            else{
                items.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4uFAtuoJaEjS96aI7bHq01YVojEGF3HzyxA87JynYUQ_Qy5llFA");
            }
        }

        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());

        itemRepository.save(items);
        appUser.additem(items);
        appUserRepository.save(appUser);

        model.addAttribute("lostitem", itemRepository.findAll());


        return "lostitemlist";
    }


    @GetMapping("/adminaddforuser")
    public String adminaddforuser(Model model) {

        model.addAttribute("lostitem", new Item());
        model.addAttribute("loggedinusers", appUserRepository.findAll());
        return "adminlostitemform";
    }
    @PostMapping("/adminaddforuser")
    public String adminaddforusers( @Valid @ModelAttribute("lostitem") Item items,
                                    @RequestParam( "loggedinuser")long userid, BindingResult result,
                                    Model model) {

        if (result.hasErrors()) {

            return "adminlostitemform";
        }

        if (items.getImage().isEmpty()){
            if(items.getCategory().equals("Clothes")){
                items.setImage("https://getthelabel.btxmedia.com/pws/client/images/landing-pages/mens/2017/310717/knitwear.jpg");
            }
           else if (items.getCategory().equals("Pets")){
                items.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxb9vQmD4Pn_OapoWNSjzf_pT5FZLa4pg60oqSuRs6QAavAAKc");
            }
            else{
                items.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4uFAtuoJaEjS96aI7bHq01YVojEGF3HzyxA87JynYUQ_Qy5llFA");
            }
        }


        itemRepository.save(items);
        AppUser newappuser = appUserRepository.findAppUserById(userid);
        newappuser.additem(items);

        appUserRepository.save(newappuser);

        model.addAttribute("lostitem", itemRepository.findAll());

        return "lostitemlist";

    }



    @RequestMapping("/mylist")
    public String userlist( Model model, Authentication auth) {

        AppUser currentuser = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("lostitem", itemRepository.findByAppUsers(currentuser));
        return "mylostorfoundlist";


    }


    @RequestMapping("/adminlist")
    public String showadminList(Model model, Authentication auth) {
        model.addAttribute("lostitem",itemRepository.findAll());

        return "adminlostandfoundlist";

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




    @GetMapping("/searchlostitem")
    private String searchLostItem(Model model) {

        model.addAttribute("lostitem", new Item());

        return "base";
    }
    @PostMapping("/searchlostitem")
    public String showSearchResults(HttpServletRequest request, Model model){
        String searchCategory = request.getParameter("search");
        model.addAttribute("search", searchCategory);
        model.addAttribute("searchlostitem", itemRepository.findAllByCategoryContainingIgnoreCase(searchCategory));
        return "searchresult";
    }


    @RequestMapping("/edit/{id}")
    public String editlostItem(@PathVariable("id") long id, Model model){

       Item item = itemRepository.findOne(id);
        model.addAttribute("lostitem", item);

        return "lostitemform";
    }



}