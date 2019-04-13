package team.dorm301.letterhome.service;

import com.mengyunzhi.core.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.repository.NewsRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> getAllNews(Long size) {
        List<News> primaryNewsList = newsRepository.findAll();
        int length = primaryNewsList.size();
        if (CommonService.ids.size() >= 0.75 * length) {
            CommonService.ids.clear();
        }
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            int index = Math.toIntExact(CommonService.getRandomUniqueId(0L, (long) length - 1));
            News news = primaryNewsList.get(index);
            Calendar currentTime = Calendar.getInstance();
            currentTime.add(Calendar.MINUTE, -CommonService.getRandomNumberInts(0, 15));
            news.setPublishTime(currentTime);
            newsList.add(news);
        }
        return newsList;
    }
}
