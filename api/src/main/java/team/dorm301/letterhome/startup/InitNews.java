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

        for (int i = 0; i < 5; i ++) {
            News news1 = new News();
            news1.setAuthor("姜尼玛");
            news1.setTitle("尼玛论尼玛");
            news1.setContent("嘤嘤嘤");
            news1.setImgUrl("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1700514426,3179612910&fm=173&app=49&f=JPEG?w=218&h=146&s=ED02A0543E491E5348973E9C0300709C");
            newsList.add(news1);
        }

        News news0 = new News();
        news0.setAuthor("亦舒");
        news0.setTitle("叹息桥");
        news0.setContent("做不到是你自己的事，午夜梦回，你爱怎么回味就怎么回味，但人前人后，我要你装出什么都没有发生过的样子。你可以的，我们都可以，人都是这般活下来的。");
        news0.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/0.jpg");
        newsList.add(news0);

        News news1 = new News();
        news1.setAuthor("七堇年");
        news1.setTitle("澜本嫁衣");
        news1.setContent("我的感情碰洒了，还剩一半。我把杯子扶起来，兑满，留给第二个人。他又碰洒了。我还是扶起，兑满，留给第三个人。感情是越来像一杯酒。感情是越来越淡，但是他们每个人，获得的都是我完整的，全部的，一杯酒。");
        news1.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/1.jpg");
        newsList.add(news1);

        News news2 = new News();
        news2.setAuthor("张宗子");
        news2.setTitle("书时光");
        news2.setContent("相信宿命，人就不会抱怨。对于眼前的机会，就不容易错失。珍贵的事物最初总是以最不显眼的方式出现的，不仅不够美妙，不够令人激动，甚或比普通还普通，比平常还平常。我们必须学会从茫茫万物中一眼看出能够决定我们未来和幸福的契机，抓住它，再也不放手。");
        news2.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/2.jpg");
        newsList.add(news2);

        News news3 = new News();
        news3.setAuthor("君子江山");
        news3.setTitle("一生一世笑繁华");
        news3.setContent("“君惊澜，你说，爱情会不会就像是烟花，很美，却不过灿烂一瞬？” \n" +
                "“如果爱情就是烟花，最美不过刹那，你我便不等下一场烟花起，不等这一场烟花落。就这样，静静死在它灿烂的瞬间，一刹永恒！”\n");
        news3.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/3.jpg");
        newsList.add(news3);

        News news4 = new News();
        news4.setAuthor("刘慈欣");
        news4.setTitle("三体");
        news4.setContent("“没有救世的能力不是你的错，但给世界以希望后又打碎它就是一种不可饶恕的罪恶了。 ” \n" +
                "——记得《肖申克的救赎》里Red说“Hope is a dangerous thing. Drive a man insane. It's got no place here. Better get used to the idea.”我一直觉得Red的劝告很现实，尽管我也相信希望。所以，如果你不愿意，不要给别人以希望。不要给人以希望又打碎它。\n");
        news4.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/4.jpg");
        newsList.add(news4);

        newsRepository.saveAll(newsList);
    }
}
