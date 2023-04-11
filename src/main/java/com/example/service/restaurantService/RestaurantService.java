package com.example.service.restaurantService;

import com.example.dao.RestaurantDAO;
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

    public void saveRestaurant(Restaurant restaurant){
        if(restaurant.getRestaurantId()>0){
            restaurantDAO.save(restaurant);
        }else {
            throw new RuntimeException("id lower that zero");
        }
    }

    public ResponseEntity<List<Restaurant>> getrestaurants(){
        List<Restaurant> restaurantList = restaurantDAO.findAll();
        return new ResponseEntity<>(restaurantList, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<Restaurant> getoneRestaurants(int restaurantId){
        Restaurant restaurant = restaurantDAO.findById(restaurantId).get();
        return new ResponseEntity<>(restaurant,HttpStatusCode.valueOf(200));
    }

    public void updateRestaurantById(Restaurant restaurant){
        restaurantDAO.save(restaurant);
    }

    public void deleteRestaurant(int restaurantId){
        restaurantDAO.deleteById(restaurantId);
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByName(String name){
        return new ResponseEntity<>(restaurantDAO.findRestaurantByName(name),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByParking(boolean parking){
        return new ResponseEntity<>(restaurantDAO.findByParking(parking),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByWifi(boolean wifi){
        return new ResponseEntity<>(restaurantDAO.findByWifi(wifi),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> getRestuarantByRating(Long raiting){
        return new ResponseEntity<>(restaurantDAO.findByRating(raiting),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingASC(){
        return new ResponseEntity<>(restaurantDAO.sortRestuarantByRatingASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingDESC(){
        return new ResponseEntity<>(restaurantDAO.sortRestuarantByRatingDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceASC(){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantByMinPriceASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceDESC(){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantByMinPriceDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceASC(){
        return new ResponseEntity<>(restaurantDAO.sortResturantByMaxPriceASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceDESC(){
        return  new ResponseEntity<>(restaurantDAO.sortRestuarantByMaxPriceDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceASC(){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantByAveragePriceASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceDESC(){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantByAveragePriceDESC(),HttpStatusCode.valueOf(200));
    }


}
