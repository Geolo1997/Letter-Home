package team.dorm301.letterhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<News> getAllNews(@RequestParam Long size) {
        return newsService.getAllNews(size);
    }
}
