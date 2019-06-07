package com.example.samazon.jacob;

import com.example.samazon.security.User;
import com.example.samazon.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    UserRepository userRepository;

    public WishlistService() {
    }

    public void genWish(User user){

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        user.setWishlist(wishlist);
        userRepository.save(user);
    }

    public void updateWish(Product product, Wishlist wishlist){

        wishlist.setProducts(product);
        wishlistRepository.save(wishlist);

    }

    public void deleteWish(Product product, Wishlist wishlist){

        wishlist.getProducts().remove(product);
        wishlistRepository.save(wishlist);

    }
}
