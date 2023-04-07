package com.example.dao;


import com.example.models.restaurant.Restaurant;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantDAO extends JpaRepository<Restaurant,Integer> {
    List<Restaurant> findRestaurantByName(String name);

    @OrderBy("rating ASC ")
    List<Restaurant> sortRestaurantbyRaitingASC(Long rating);

    @OrderBy("rating DESC ")
    List<Restaurant> sortRestaurantbyRaitingDESC(Long rating);

    @OrderBy("minprice ASC ")
    List<Restaurant> sortRestaurantbyMinPriceASC(Long minprice);

    @OrderBy("minprice DESC ")
    List<Restaurant> sortRestaurantbyMinPriceDESC(Long minprice);

    @OrderBy("maxprice ASC ")
    List<Restaurant> sortRestaurantbyMaxPriceASC(Long maxprice);

    @OrderBy("maxprice DESC ")
    List<Restaurant> sortRestaurantbyMaxPriceDESC(Long maxprice);

    @OrderBy("averageprice ASC ")
    List<Restaurant> sortRestaurantbyAveragePriceASC(Long averageprice);

    @OrderBy("averageprice DESC ")
    List<Restaurant> sortRestaurantbyAveragePriceDESC(Long averageprice);

    List<Restaurant> findByWifi(boolean wifi);

    List<Restaurant> findByParking(boolean parking);




}
