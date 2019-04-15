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
        if(size>10)size= 10L;
        List<News> primaryNewsList = newsRepository.findAll();
        int length = primaryNewsList.size();
        System.out.println("----------------length"+size);
        if (CommonService.ids.size() >= 0.75 * length) {
            CommonService.ids.clear();
        }
        Calendar currentTime = Calendar.getInstance();
        List<News> newsList = new ArrayList<>();

        for (int i = 0; i < size; i ++) {
            int index = (int) (Math.random()*primaryNewsList.size()-1);
//            int index = Math.toIntExact(CommonService.getRandomUniqueId(0L, (long) length - 1));
            News news = primaryNewsList.get(index);
//            currentTime.add(Calendar.MINUTE, -CommonService.getRandomNumberInts(0, 15));
            currentTime.add(Calendar.MINUTE, -index);
            news.setPublishTime(currentTime);
            newsList.add(news);
            primaryNewsList.remove(index);
        }
        return newsList;
    }
}
