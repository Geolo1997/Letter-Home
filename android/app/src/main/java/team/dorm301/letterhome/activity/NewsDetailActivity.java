package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;
import java.util.Calendar;
import java.util.Date;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.consts.IntentExtra;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.ui.ToolbarLayout;
import team.dorm301.letterhome.util.DateUtils;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.shine_button)
    ShineButton shineButton;

    private News news;

    @Override
    protected int getContentView() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        news = (News) getIntent().getSerializableExtra(IntentExtra.NEWS);
        loadNews();
        shineButton.bringToFront();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolbarTitle("资讯详情");
    }

    private void loadNews() {
        tvTitle.setText(news.getTitle());
        tvPublishTime.setText(DateUtils.toDefaultString(news.getPublishTime()));
        tvAuthor.setText(news.getAuthor());
        tvContent.setText(news.getContent());
        Glide.with(getApplicationContext()).load(news.getImgUrl()).into(ivPic);
    }
}
