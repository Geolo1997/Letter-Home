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

        News news5 = new News();
        news5.setAuthor("江南");
        news5.setTitle("龙族IV·奥丁之渊");
        news5.setContent("“这个世界上，只有Bug能挡住Bug,也只有怪物能与怪物为敌! ”路明非每 说一句话就会吐出一口血，“你已经暗示我了，昆古尼尔是Bug,我也是；昆古尼尔 是怪物，我也是，我才是这个世界上……最大的怪物！”“是的，你是这个世界上最大的怪物，哥哥你真棒！”");
        news5.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/5.jpg");
        newsList.add(news5);

        News news6 = new News();
        news6.setAuthor("硕硕");
        news6.setTitle("华胥引");
        news6.setContent("摘一朵红，开在阁楼案台， \n" +
                "抚一曲乐，飘在四季花前。 \n" +
                "前世今生，袭人 妙玉 几度销魂几声笑叹 \n" +
                "葬花 花葬 \n" +
                "怜你 香消花损 却遗憾负去 \n" +
                "怜你 花枝招展 却孤芳自赏 \n" +
                "怜你 才华经纶 却堕入尘俗 \n" +
                "怜你 精明细做 却不解情蛊 \n" +
                "怜你 温柔贤惠 却不得人心 \n" +
                "怜你 身世浮沉 却不得善终 \n" +
                "怜你 通灵古怪 却不谙人事 \n" +
                "怜你 情痴一枝 却不得开花 \n" +
                "如梦虚境 莫失莫忘 \n" +
                "红楼旧梦 莫痴莫念 \n" +
                "前世今生 因缘劫难 \n" +
                "红尘后世 且行且惜 \n" +
                "落红偏向华胥引 \n" +
                "惺眼一梦了无痕 \n" +
                "栏杆处泪眼阑干 \n" +
                "夕阳依旧红半天\n");
        news6.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/6.jpg");
        newsList.add(news6);

        News news7 = new News();
        news7.setAuthor("帆帆");
        news7.setTitle("毕业感言");
        news7.setContent("每年凤凰花开时，这就是一个学生飞向另一个世界的旅程，展开了青年的生活。几年的生活，回想起来真是甜蜜，但这些也将随着我们的毕业而写下一个完整的句点。留下一个个永远令人怀念而且独一无二的美好回忆！ 毕业了，回想起这几年的学生生活是这么的多采多姿啊！真希望时光能倒退，但那只是一个不可能实现的梦想罢了。天下没有不散的宴席，有聚就有散，有离别才会有相逢的一天，这是天经地义无法改变的事情。毕业后我要更加努力用功读书，为母校争光，才不会辜负了这些年来老师的细心教导与期望。");
        news7.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/7.jpg");
        newsList.add(news7);

        News news8 = new News();
        news8.setAuthor("超超");
        news8.setTitle("回首");
        news8.setContent("回首半生如梦 \n" +
                "何处停留 \n" +
                "住在心里的那个人 \n" +
                "藏在泪中 \n" +
                "\n" +
                "回首半生匆匆 \n" +
                "恍如一梦 \n" +
                "你像风来了又走 \n" +
                "我心满了又空\n");
        news8.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/8.jpg");
        newsList.add(news8);

        News news9 = new News();
        news9.setAuthor("维维");
        news9.setTitle("execution point");
        news9.setContent("exp（经验值）是一个缩写，它的全名是execution point（处决点数）一种用来量化你对别人造成痛苦的方式。每当你杀死别人，你的exp就将增加。当你有了足够的exp，你的love（等级）就会增加，同样的，love也是一个缩写。它的全名是level of violence（暴力指数）用来衡量一个人伤害别人的能力。你杀的越多，就越容易远离自己的本心，你越远离自己的本心，就越难被伤害，就越容易放任自己去伤害别人。");
        news9.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/9.jpg");
        newsList.add(news9);

        News news10 = new News();
        news10.setAuthor("飞飞");
        news10.setTitle("雪纷");
        news10.setContent("闻 \n" +
                "雪纷 \n" +
                "乱世人 \n" +
                "鼓吹轻衣 \n" +
                "她凌寒谁知 \n" +
                "漠北烽烟四起 \n" +
                "只待他金甲一袭 \n" +
                "暮色渐起清角吹寒 \n" +
                "终归踏马留她白青丝 \n" +
                "祈此去经年春风仍未改 \n" +
                "幸驿寄梅花遥知相思 \n" +
                "鸿雁有知飞尽天涯 \n" +
                "十载战乱百年情 \n" +
                "斑驳流年未觉 \n" +
                "似岁月暮暝 \n" +
                "归思难清 \n" +
                "候战平 \n" +
                "长亭 \n" +
                "等\n");
        news10.setImgUrl("https://letter-1253606956.cos.ap-beijing.myqcloud.com/10.jpg");
        newsList.add(news10);

        newsRepository.saveAll(newsList);
    }
}
