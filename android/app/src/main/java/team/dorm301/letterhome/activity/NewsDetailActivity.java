package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import butterknife.BindView;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.consts.IntentExtra;
import team.dorm301.letterhome.entity.News;
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
    }

    private void loadNews() {
        tvTitle.setText(news.getTitle());
        tvPublishTime.setText(DateUtils.toDefaultString(news.getPublishTime()));
        tvAuthor.setText(news.getAuthor());
        tvContent.setText(news.getContent());
    }
}
