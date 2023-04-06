package com.example.service;

import com.example.dao.CustomerDAO;
import com.example.dao.RestaurantDAO;
import com.example.models.customer.Customer;
import com.example.models.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDAO restaurantDAO;
    private final CustomerDAO customerDAO;

    public void saveRestaurant(Customer customer){
        if(customer.getId()>0){
            customerDAO.save(customer);
        }else {
            throw new RuntimeException("id lower that zero");
        }
    }

    public ResponseEntity<List<Restaurant>> getrestaurants(){
        List<Restaurant> restaurantList = restaurantDAO.findAll();
        return new ResponseEntity<>(restaurantList, HttpStatusCode.valueOf(200));
    }

    public void updateRestaurantById(Restaurant restaurant){
        restaurantDAO.save(restaurant);
    }

    public void deleteRestaurant(int id){
        restaurantDAO.deleteById(id);
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByName(String name){
        return new ResponseEntity<>(restaurantDAO.findRestaurantByName(name),HttpStatusCode.valueOf(200));
    }




}
