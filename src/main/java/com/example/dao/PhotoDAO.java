package com.example.dao;

import com.example.models.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDAO extends JpaRepository<Photo,Integer> {
}
