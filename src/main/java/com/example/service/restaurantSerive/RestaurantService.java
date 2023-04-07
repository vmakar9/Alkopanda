package com.example.service.restaurantSerive;

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

    public void saveRestaurant(Restaurant restaurant){
        if(restaurant.getId()>0){
            restaurantDAO.save(restaurant);
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

    public ResponseEntity<List<Restaurant>> getRestaurantByParking(boolean parking){
        return new ResponseEntity<>(restaurantDAO.findByParking(parking),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByWifi(boolean wifi){
        return new ResponseEntity<>(restaurantDAO.findByWifi(wifi),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingASC(Long raiting){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantbyRaitingASC(raiting),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingDESC(Long raiting){
        return  new ResponseEntity<>(restaurantDAO.sortRestaurantbyRaitingDESC(raiting),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceASC(Long minprice){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantbyMinPriceASC(minprice),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceDESC(Long minprice){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantbyMinPriceDESC(minprice),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceASC(Long maxprice){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantbyMaxPriceASC(maxprice),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceDESC(Long maxprice){
        return  new ResponseEntity<>(restaurantDAO.sortRestaurantbyMaxPriceDESC(maxprice),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceASC(Long averageprice){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantbyAveragePriceASC(averageprice),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceDESC(Long averageprice){
        return new ResponseEntity<>(restaurantDAO.sortRestaurantbyAveragePriceDESC(averageprice),HttpStatusCode.valueOf(200));
    }


}
