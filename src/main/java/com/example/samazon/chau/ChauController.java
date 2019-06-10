
package com.example.samazon.chau;

import com.example.samazon.chau.CartService;
import com.example.samazon.chau.ProductService;
import com.example.samazon.jacob.*;
import com.example.samazon.jin.HistoryRepository;
import com.example.samazon.jin.HistoryService;
import com.example.samazon.jin.SendEmail;
import com.example.samazon.security.*;
//import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
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


    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @PostMapping("/addcart")
    public String addCart(@RequestParam("product_id") long product_id, Model model) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(product_id).get();
        Cart cart = user.getCarts();

        cartService.updateCart(product, user.getCarts()) ;
        model.addAttribute("cart", user.getCarts() );
        model.addAttribute("products", user.getCarts().getProducts());
        model.addAttribute("user", user);
        model.addAttribute("history", userService.getCurrentUser().getHistory());


        return "redirect:/cart";
    }

    @GetMapping("/wishlist")
    public String addWish( Model model) {
        User user = userService.getCurrentUser();

        model.addAttribute("wishlist", user.getWishlist());
        model.addAttribute("cart", user.getCarts() );
        model.addAttribute("products", user.getCarts().getProducts());
        model.addAttribute("user", user);



        shoppingCartService.shoppingCartLoader(model);

        return "chau/wishlist";

    }

    @PostMapping("/wishlist")
    public String addWish(@RequestParam("product_id") long product_id, Model model) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(product_id).get();

        wishlistService.updateWish(product, user.getWishlist());
        model.addAttribute("wishlist", user.getWishlist());
        model.addAttribute("cart", user.getCarts() );
        model.addAttribute("products", user.getCarts().getProducts());
        model.addAttribute("user", user);



        shoppingCartService.shoppingCartLoader(model);


        return "chau/wishlist";

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




        User user = userService.getCurrentUser();
        shoppingCartService.shoppingCartLoader(model);
        model.addAttribute("user", userService.getCurrentUser());

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
        model.addAttribute("email", user.getEmail());
        model.addAttribute("total", total);
        model.addAttribute("products", products);

        historyService.genHistory(user);

        shoppingCartService.shoppingCartLoader(model);

        try {
            sendEmail();
            System.out.println("email");
            return "chau/confirmation";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }

    }

    @PostMapping("/checkout")
    public String billing(Model model){
        Cart cart =userService.getCurrentUser().getCarts();
        historyService.cartHistory(cart);

        return "redirect:/homepage";
    }

    @RequestMapping("/cart")
    public String viewCart(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        Cart cart = user.getCarts();
        double total = cartService.totals(cart);
        String message = "You spent over $50, You get Free Shipping!";
        if (total < 50.0) {
            total += 5.0;
            message = "You need to spend $50 to get Free Shipping";
        }
        model.addAttribute("total", total);
        model.addAttribute("message", message);


        model.addAttribute("cart", cart);
        model.addAttribute("products", cart.getProducts());

        shoppingCartService.shoppingCartLoader(model);


        return "chau/shoppingcart";
    }

    @RequestMapping("/remove/{id}")
    public String removeItem(@PathVariable("id") long id) {
        User user = userService.getCurrentUser();
        Product product = productService.getProduct(id);
        cartService.removeItem(product,user.getCarts());
        return "redirect:/cart";
    }



    private void sendEmail()throws Exception{

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String strEmail=userService.getCurrentUser().getEmail();

        helper.setTo(strEmail);

        helper.setText("Your order is completed.");
        helper.setSubject("Order Confirmation");

        sender.send(message);
    }

    @RequestMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") long id, Model model){
        User user  = userService.getCurrentUser();
        model.addAttribute("product", productRepository.findById(id).get());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("cart", userService.getCurrentUser().getCarts());

        shoppingCartService.shoppingCartLoader(model);


        return "jacob/addproduct";
    }

}
