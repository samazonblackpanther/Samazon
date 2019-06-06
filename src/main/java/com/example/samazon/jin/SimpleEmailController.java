package com.example.samazon.jin;

import javax.mail.internet.MimeMessage;
import javax.persistence.Id;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.security.UserRepository;
import com.example.samazon.security.UserService;
import javassist.compiler.ast.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/simpleemail")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
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
}

