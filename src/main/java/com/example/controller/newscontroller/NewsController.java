package com.example.controller.newscontroller;


import com.example.models.news.News;
import com.example.service.newsservice.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

    @GetMapping("")
    public ResponseEntity<List<News>> getNews(){
        return newsService.getNews();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNews(@RequestBody News news){
        newsService.createNews(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getOneNews(@PathVariable int id){
        News news = newsService.getNewsById(id);
        return new ResponseEntity<>(news, HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id,@RequestBody News news){
        News news1 = newsService.getNewsById(id);
        news1.setDescription(news.getDescription());
        newsService.updateNewsById(news1);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable int id){newsService.deleteNews(id);}
}
