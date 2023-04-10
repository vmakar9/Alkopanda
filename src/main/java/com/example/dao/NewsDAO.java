package com.example.dao;

import com.example.models.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDAO extends JpaRepository<News,Integer> {
}
