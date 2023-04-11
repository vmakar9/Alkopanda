package com.example.service.newsservice;

import com.example.dao.NewsDAO;
import com.example.models.news.News;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {
    private NewsDAO newsDAO;

    public void createNews(News news){
        newsDAO.save(news);
    }

    public ResponseEntity<List<News>> getNews(){
        List<News> newsList = newsDAO.findAll();
        return new ResponseEntity<>(newsList, HttpStatusCode.valueOf(200));
    }

    public News getNewsById(int newsId){
        return newsDAO.findById(newsId).get();
    }

    public void updateNewsById(News news){
         newsDAO.save(news);
    }

    public void deleteNews(int newsId){
        newsDAO.deleteById(newsId);
    }

    public ResponseEntity<List<News>> getByType(String type){
        return new ResponseEntity<>(newsDAO.findByType(type),HttpStatusCode.valueOf(200));
    }




}
