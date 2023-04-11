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

    @GetMapping("/{newsId}")
    public ResponseEntity<News> getOneNews(@PathVariable int newsId){
        News news = newsService.getNewsById(newsId);
        return new ResponseEntity<>(news, HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/{newsId}")
    public void patch(@PathVariable int newsId,@RequestBody News news){
        News news1 = newsService.getNewsById(newsId);
        news1.setDescription(news.getDescription());
        news1.setNewsType(news.getNewsType());
        newsService.updateNewsById(news1);
    }

    @DeleteMapping("/{newsId}")
    public void deleteNews(@PathVariable int newsId){newsService.deleteNews(newsId);}

    @GetMapping("/{type}")
    public ResponseEntity<List<News>> getByRole(@PathVariable String type ){
        return newsService.getByType(type);
    }
}
