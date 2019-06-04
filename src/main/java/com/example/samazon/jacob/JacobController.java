package com.example.samazon.jacob;

import com.cloudinary.utils.ObjectUtils;
import com.example.samazon.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class JacobController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryConfig cloudc;



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
        return "redirect:/";
    }
}
