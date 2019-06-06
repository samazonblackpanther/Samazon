//package com.example.samazon.jin;
//
//import javax.mail.internet.MimeMessage;
//
//import com.example.samazon.chau.CartRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class SimpleEmailController {
//
//    @Autowired
//    private JavaMailSender sender;
//
//    @Autowired
//    CartRepository cartRepository;
//
//    @RequestMapping("/simpleemail")
//    @ResponseBody
//    String home() {
//        try {
//            sendEmail();
//            return "Email Sent!";
//        } catch (Exception ex) {
//            return "Error in sending email: " + ex;
//        }
//    }
//
//    private void sendEmail() throws Exception (Model model){
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        model.addAttribute("");
//        helper.setTo("jinskwon2@gmail.com");
//        helper.setText("How are you?");
//        helper.setSubject("Order Confirmation");
//
//        sender.send(message);
//    }
//}
//
