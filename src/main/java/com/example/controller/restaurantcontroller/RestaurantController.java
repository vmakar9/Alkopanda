package com.example.controller.restaurantcontroller;



import com.example.models.restaurant.Restaurant;
import com.example.service.restaurantSerive.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/restaurant")
@AllArgsConstructor
public class RestaurantController {
    private RestaurantService  restaurantService;


    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        return restaurantService.getrestaurants();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Restaurant restaurant){
           restaurantService.saveRestaurant(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getOneRestaurant(@PathVariable int id){
        return restaurantService.getoneRestaurants(id).getBody();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        restaurantService.deleteRestaurant(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id,@RequestBody Restaurant restaurant){
        Restaurant restaurant1 = restaurantService.getoneRestaurants(id).getBody();

        restaurant1.setName(restaurant.getName());
        restaurant1.setDirectorname(restaurant.getDirectorname());
        restaurant1.setDescription(restaurant.getDescription());
        restaurant1.setRating(restaurant.getRating());
        restaurant1.setParking(restaurant.isParking());
        restaurant1.setWifi(restaurant.isWifi());
        restaurant1.setMinprice(restaurant.getMinprice());
        restaurant1.setMaxprice(restaurant.getMaxprice());
        restaurant1.setAverageprice(restaurant.getAverageprice());
        restaurantService.updateRestaurantById(restaurant1);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String name){
        return restaurantService.getRestaurantByName(name);
    }

    @GetMapping("/parking/{parking}")
    public ResponseEntity<List<Restaurant>> getRestaurantByParking(@PathVariable boolean parking){
        return restaurantService.getRestaurantByParking(parking);
    }

    @GetMapping("/wifi/{wifi}")
    public ResponseEntity<List<Restaurant>> getRestaurantByWifi(@PathVariable boolean wifi){
        return restaurantService.getRestaurantByWifi(wifi);
    }

    @GetMapping("/ratingASC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingASC(){
        return restaurantService.sortRestaurantByRaitingASC();
    }

    @GetMapping("/ratingDESC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingDESC(){
        return restaurantService.sortRestaurantByRaitingDESC();
    }

    @GetMapping("/minpriceASC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceASC(){
        return restaurantService.sortRestaurantByMinPriceASC();
    }

    @GetMapping("/minpriceADESC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceDESC(){
        return restaurantService.sortRestaurantByMinPriceDESC();
    }

    @GetMapping("/maxpriceASC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceASC(){
        return restaurantService.sortRestaurantByMaxPriceASC();
    }

    @GetMapping("/maxpriceDESC/")
    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceDESC(){
        return restaurantService.sortRestaurantByMaxPriceDESC();
    }

    @GetMapping("/averagepriceASC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceASC(){
        return restaurantService.sortRestaurantByAveragePriceASC();
    }

    @GetMapping("/averagepriceDESC")
    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceDESC(){
        return restaurantService.sortRestaurantByAveragePriceDESC();
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Restaurant>> getRestaurantByRating(@PathVariable Long rating){
        return restaurantService.getRestuarantByRating(rating);
    }






}
