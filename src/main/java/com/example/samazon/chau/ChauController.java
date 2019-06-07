
package com.example.samazon.chau;

import com.example.samazon.chau.CartService;
import com.example.samazon.chau.ProductService;
import com.example.samazon.jacob.Product;
import com.example.samazon.jacob.ProductRepository;
import com.example.samazon.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

@Controller
public class ChauController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/addcart")
    public String addCart(@RequestParam("product_id") long product_id, Model model) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(product_id).get();


        System.out.println(product.getName());
        cartService.updateCart(product, user.getCarts()) ;
        model.addAttribute("cart", user.getCarts() );
        model.addAttribute("user", user);
        return "chau/shoppingcart";
//            return "redirect:/homepage";

    }

    @RequestMapping("order/{id}")
    public String viewOrder(Model model, @PathVariable("id") long id) {
        Cart cart = userService.getCurrentUser().getCarts();
        model.addAttribute("products", cart.getProducts());
        for (Product product : cart.getProducts()) {
            model.addAttribute("product", product);
        }
        double total = cartService.totals(cart);
        String message = "You spent over $50, You got Free Shipping";
        if (total < 50.0) {
            total += 5.0;
            message = "$5 for Shipping";
        }
        model.addAttribute("message", message);
        model.addAttribute("total", total);
        model.addAttribute("Order", cart);
        return "jin/detailProduct";
    }


    @GetMapping("/checkout")
    public String checkout(Model model){
        User user = userService.getCurrentUser();
        Cart myCart = userService.getCurrentUser().getCarts();
        model.addAttribute("cart", myCart);
        Collection<Product> products = myCart.getProducts();
        for (Product product : products) {
            model.addAttribute("product", product);
        }
        double total = cartService.totals(myCart);
        String message = "You spent over $50, You got Free Shipping";
        if (total < 50.0) {
            total += 5.0;
            message = "$5 charged for Shipping";
        }
        model.addAttribute("user", user);
        model.addAttribute("message", message);
        model.addAttribute("total", total);
        model.addAttribute("products", products);
        return "chau/confirmation";
    }

    @PostMapping("/checkout")
    public String billing(Model model){
        Cart cart =userService.getCurrentUser().getCarts();
        cartService.cartHistory(cart);
        return "chau/confirmation";
    }

    @RequestMapping("/cart")
    public String viewCart(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        Cart activeCart = user.getCarts();
        double total = cartService.totals(activeCart);
        String message = "You spent over $50, You get Free Shipping!";
        if (total < 50.0) {
            total += 5.0;
            message = "You need to spend $50 to get Free Shipping";
        }
        model.addAttribute("total", total);
        model.addAttribute("message", message);

        for (Product product : activeCart.getProducts()) {
            model.addAttribute("product", product);
        }

        model.addAttribute("products", activeCart.getProducts());
        return "chau/shoppingcart";
    }
    @RequestMapping("/remove/{id}")
    public String removeItem(@PathVariable("id") long id, Authentication auth) {
        User user = userService.getCurrentUser();
        Product product = productService.getProduct(id);
        cartService.removeItem(product,user.getCarts());
        return "redirect:/chau/shoppingcart";
    }
}
