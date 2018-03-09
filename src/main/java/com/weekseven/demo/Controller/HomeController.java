package com.weekseven.demo.Controller;

import com.weekseven.demo.Model.AppUser;

import com.weekseven.demo.Model.Article;
import com.weekseven.demo.Model.NewsApi;
import com.weekseven.demo.Model.Profile;
import com.weekseven.demo.Repository.AppUserRepository;
import com.weekseven.demo.Repository.ProfileRepository;
import com.weekseven.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class HomeController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ProfileRepository profileRepository;





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
        public String showNews(@Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){

            RestTemplate restTemplate = new RestTemplate();
            newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);
           model.addAttribute("article",newsApis.getArticles());

            return "index";
        }

    @RequestMapping("/mytopiclist")
    public String userlist( Model model, Authentication auth) {

        AppUser currentuser = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("mytopic", profileRepository.findByAppusers(currentuser));
        return "mytopiclist";


    }

    @GetMapping("/addmytopic")
    public String addmyTopic(Model model){
        model.addAttribute("mytopic", new Profile());
        return "mytopicform";

    }
    @PostMapping("/addmytopic")
    public String showmyTopic(@Valid @ModelAttribute("mytopic") Profile profiles, Model model, BindingResult result, Authentication auth){
        if (result.hasErrors()) {
            return "mytopicform";
        }
        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        profileRepository.save(profiles);
        appUser.addprofile(profiles);
        appUserRepository.save(appUser);

        model.addAttribute("mytopic", profileRepository.findAll());



        return "mytopiclist";
    }

    @GetMapping("/abc")
    public String showabc(@Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){

        RestTemplate restTemplate = new RestTemplate();
        newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?sources=abc-news&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);
        model.addAttribute("article",newsApis.getArticles());

        return "index";
    }

    @GetMapping("/fox")
    public String showfox(@Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){

        RestTemplate restTemplate = new RestTemplate();
        newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?sources=fox-news&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);
        model.addAttribute("article",newsApis.getArticles());

        return "index";
    }
    @GetMapping("/cnn")
    public String showcnn(@Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){

        RestTemplate restTemplate = new RestTemplate();
        newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);
        model.addAttribute("article",newsApis.getArticles());

        return "index";
    }

    @GetMapping("/msnbc")
    public String showmsnbc(@Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){

        RestTemplate restTemplate = new RestTemplate();
        newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?sources=msnbc&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);
        model.addAttribute("article",newsApis.getArticles());

        return "index";
    }


    @GetMapping("/selectcategory")
        public String selectCategory(Model model){
        model.addAttribute("profile",new Profile());
        return "selectcategory";

        }
        @PostMapping("/{category}")
        public String PoliticsNews(HttpServletRequest request, @Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){
                String category = request.getParameter("category");
            RestTemplate restTemplate = new RestTemplate();
            newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&category="+category+"&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);
            model.addAttribute("article",newsApis.getArticles());

            return "index";
        }
    @GetMapping("/searchtopic")
    private String searchLostItem(Model model) {

        model.addAttribute("article",new Article());

        return "index";
    }
    @PostMapping("/searchtopic")
    public String showSearchResults(HttpServletRequest request,@Valid @ModelAttribute("newsApi") NewsApi newsApis, Model model){
        String topic=request.getParameter("searchtopic");
        String searchtopic = request.getParameter("search");
        RestTemplate restTemplate = new RestTemplate();
        newsApis = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?q="+searchtopic+"&apiKey=d4a451c5d3c74eda940441948d032ab5", NewsApi.class);

        model.addAttribute("search", searchtopic);
        model.addAttribute("searchtopic", profileRepository.findByTopicContainingIgnoreCase(searchtopic));
        model.addAttribute("searchtopic",newsApis.getArticles());
        return "searchresult";
    }




}