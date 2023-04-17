package com.example.controller.photocontroller;

import com.example.dao.PhotoDAO;
import com.example.dao.RestaurantDAO;
import com.example.models.photo.Photo;
import com.example.models.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/restaurants/{restaurantId}/photos")
public class PhotoController {

    private RestaurantDAO restaurantDAO;
    private PhotoDAO photoDAO;

    @PostMapping("")
    public ResponseEntity<Photo> createPhoto(@PathVariable int restaurantId, @RequestBody Photo photo) {
        Optional<Restaurant> optionalRestaurant = restaurantDAO.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            photo.setRestaurant(restaurant);
            restaurant.getPhotos().add(photo);
            photoDAO.save(photo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public List<Photo> getAllPhotosByRestaurantId(@PathVariable int restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantDAO.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getPhotos();
        } else {
            return Collections.emptyList();
        }
    }

    @PutMapping("/{photoId}")
    public ResponseEntity<Photo> updatePhone(@PathVariable int restaurantId, @PathVariable int photoId, @RequestBody Photo photo) {
        Optional<Restaurant> optionalRestaurant = restaurantDAO.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Optional<Photo> optionalPhoto = photoDAO.findById(photoId);
            if (optionalPhoto.isPresent()) {
                Photo existingPhoto = optionalPhoto.get();
                existingPhoto.setImageUrl(photo.getImageUrl());
                existingPhoto.setDescription(photo.getDescription());
                photoDAO.save(existingPhoto);
                return ResponseEntity.ok(existingPhoto);
            } else {
                return ResponseEntity.notFound().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable int restaurantId,@PathVariable int photoId){
        Optional<Restaurant> optionalRestaurant = restaurantDAO.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            Optional<Photo> optionalPhoto = photoDAO.findById(photoId);
            if(optionalPhoto.isPresent()){
                Photo photo = optionalPhoto.get();
                photoDAO.delete(photo);
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }

        }else {
            return ResponseEntity.notFound().build();
        }
    }


}