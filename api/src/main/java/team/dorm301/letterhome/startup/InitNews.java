package team.dorm301.letterhome.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitNews implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(InitNews.class);

    private final NewsRepository newsRepository;

    @Autowired
    public InitNews(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<News> news = newsRepository.findAll();

        if (!news.isEmpty()) {
            logger.info("新闻已存在，退出");
            return;
        }

        List<News> newsList = new ArrayList<>();

        for (int i = 0; i < 30; i ++) {
            News news1 = new News();
            news1.setAuthor("姜尼玛");
            news1.setTitle("尼玛论尼玛");
            news1.setContent("嘤嘤嘤");
            news1.setImgUrl("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1700514426,3179612910&fm=173&app=49&f=JPEG?w=218&h=146&s=ED02A0543E491E5348973E9C0300709C");
            newsList.add(news1);
        }

        newsRepository.saveAll(newsList);
    }
}
