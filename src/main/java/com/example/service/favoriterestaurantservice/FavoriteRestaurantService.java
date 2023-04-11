package com.example.service.favoriterestaurantservice;

import com.example.dao.CustomerDAO;
import com.example.dao.RestaurantDAO;
import com.example.models.customer.Customer;
import com.example.models.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteRestaurantService {
    private CustomerDAO customerDAO;
    private RestaurantDAO restaurantDAO;

    public void addToFavorites(Integer customerId,Integer restaurantId){
        Customer customer = customerDAO.findById(customerId).orElseThrow(()->  new RuntimeException("Customer not found"));
        Restaurant restaurant = restaurantDAO.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not found"));

        customer.getFavoriteRestaurants().add(restaurant);
        restaurant.getFavoritedByCustomers().add(customer);

        customerDAO.save(customer);
        restaurantDAO.save(restaurant);
    }

    public void removeFromFavorites(Integer customerId,Integer restaurantId){
        Customer customer = customerDAO.findById(customerId).orElseThrow(()->  new RuntimeException("Customer not found"));
        Restaurant restaurant = restaurantDAO.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not found"));

        customer.getFavoriteRestaurants().remove(restaurant);
        restaurant.getFavoritedByCustomers().remove(customer);

        customerDAO.save(customer);
        restaurantDAO.save(restaurant);
    }

}
