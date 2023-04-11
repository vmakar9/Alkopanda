package com.example.dao;

import com.example.models.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsDAO extends JpaRepository<News,Integer> {
    List<News> findByType(String type);
}
