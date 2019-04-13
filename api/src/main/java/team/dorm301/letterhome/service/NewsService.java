package team.dorm301.letterhome.service;

import team.dorm301.letterhome.entity.News;

import java.util.List;

public interface NewsService {

    List<News> getAllNews(Long size);
}
