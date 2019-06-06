package com.example.samazon.jacob;

import com.cloudinary.utils.ObjectUtils;
import com.example.samazon.chau.Cart;
import com.example.samazon.chau.CartRepository;
import com.example.samazon.chau.CartService;
import com.example.samazon.jin.History;
import com.example.samazon.jin.HistoryRepository;
import com.example.samazon.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

@Controller
public class JacobController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    UserService userService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CloudinaryConfig cloudc;


    @RequestMapping("/homepage")
    public String homePage(Model model){

        // Shopping Cart
        if (userService.getCurrentUser() != null){
            //For Shopping Cart
            int cartCount = 0;

            if (userService.getCurrentUser().getCarts() != null){
                cartCount += cartService.countItems(userService.getCurrentUser().getCarts());
            }
            model.addAttribute("cart", userService.getCurrentUser().getCarts());
            model.addAttribute("cartnumber", cartCount);
        }


        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());

//        model.addAttribute("cart", userService.getCurrentUser().getCarts());
//        model.addAttribute("products", userService.getCurrentUser().getCarts().getProducts());



        return "security/index";
    }


    @GetMapping("/addproduct")
    public String showProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("currentuser", userService.getCurrentUser());
        return "jacob/addproduct";
    }

    @PostMapping("/postproduct")
    public String processForm(@Valid Product product, BindingResult result, @RequestParam("file")MultipartFile file, Model model) {
        model.addAttribute("product", product);

        if (result.hasErrors()) {
            return "jacob/addproduct";
        }
        if (file.isEmpty()) {

        } else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                product.setImage(uploadResult.get("url").toString());
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/addproduct";
            }

        }

        productRepository.save(product);
        return "redirect:/homepage";
    }

    //Search Function
    @RequestMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model){

        User user = userService.getCurrentUser();

        ArrayList<Product> productList = new ArrayList<>();
        productList.clear();
        productList = productRepository.findAllByNameContainingIgnoreCase(keyword);
        if(productList != null){
            model.addAttribute("productlist", productList);
        }

        ArrayList<Product> historyList = new ArrayList<>();
        historyList.clear();

        if (user != null){
            if (user.getHistory() != null){
                ArrayList<History> history = historyRepository.findAllByProductsContains_NameContainingIgnoreCase(keyword);

                for (History h : history){
                    if (h.getUser() == userService.getCurrentUser()){
                        for (Product product : h.getProducts()){
                            historyList.add(product);
                        }
                    }
                }
                model.addAttribute("historylist", historyList);
            }
        }

        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("keyword", keyword);

        // Shopping Cart
        if (userService.getCurrentUser() != null){
            //For Shopping Cart
            int cartCount = 0;

            if (userService.getCurrentUser().getCarts() != null){
                cartCount += cartService.countItems(userService.getCurrentUser().getCarts());
            }
            model.addAttribute("cart", userService.getCurrentUser().getCarts());
            model.addAttribute("cartnumber", cartCount);
        }


        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());



        return "jacob/searchlist";
    }
}
