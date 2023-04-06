package com.example.dao;

import com.example.models.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantDAO extends JpaRepository<Restaurant,Integer> {
    List<Restaurant> findRestaurantByName(String name);
}
