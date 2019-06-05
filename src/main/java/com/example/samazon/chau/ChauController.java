package com.example.samazon.chau;

import com.example.samazon.jacob.Product;
import com.example.samazon.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/addcart{id}")
    public String addCart(@PathVariable("id") long id, Model model) {
        User user = userService.getCurrentUser();
        Product product = productService.getProduct(id);
        Cart activeCart = cartService.updateCart(product, user);
        model.addAttribute("cart", activeCart);
        return "redirect:/cart";
    }

    @RequestMapping("oder/{id}")
    public String viewOrder(Model model, @PathVariable("id") long id) {
        Cart cart = cartService.getOrderDetails(id);
        model.addAttribute("products", cart.getProducts());
        for (Product product : cart.getProducts()) {
            model.addAttribute("product", product);
        }
        double total = cartService.totals(cart);
        String message = "You spent over $50, Yogu got Free Shipping";
        if (total < 50.0) {
            total += 5.0;
            message = "$5 for Shipping";
        }
        model.addAttribute("message", message);
        model.addAttribute("total", total);
        model.addAttribute("Order", cart);
        return "detailProduct";
    }

    @RequestMapping("/checkout")
    public String checkoutCart(Model model) {
        User user = userService.getCurrentUser();
        Cart myCart = cartService.CheckoutCart(user);
        model.addAttribute("cart", myCart);
        Collection<Product> products = myCart.getProducts();
        for (Product product : products) {
            model.addAttribute("product", product);
        }
        double total = cartService.getTotal(myCart);
        String message = "You spent over $50, You got Free Shipping";
        if (total < 50.0) {
            total += 5.0;
            message = "$5 charged for Shipping";
        }
        model.addAttribute("message", message);
        model.addAttribute("total", total);
        model.addAttribute("products", products);
        return "confirm";
    }

    @RequestMapping("/cart")
    public String viewCart(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        Cart activeCart = user.getCarts();
        double total = cartService.getTotal(activeCart);
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
        return "cart";
    }
    @RequestMapping("/remove/{id}")
    public String removeItem(@PathVariable("id") long id, Authentication auth) {
        User user = userService.findByUsername(auth.getName());
        Product product = productService.getProduct(id);
        cartService.removeItem(product, user);
        return "redirect:/cart";
    }
}


