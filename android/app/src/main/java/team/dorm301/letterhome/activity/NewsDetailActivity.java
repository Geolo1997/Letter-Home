package team.dorm301.letterhome.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;
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
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.shine_button)
    ShineButton shineButton;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private News news;

    @Override
    protected int getContentView() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_news_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(null);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//防止5.x以后半透明影响效果，使用这种透明方式
        }



        news = (News) getIntent().getSerializableExtra(IntentExtra.NEWS);
        // init actionbar
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        getToolbar().setTitleTextColor(Color.TRANSPARENT);
        getToolbar().inflateMenu(R.menu.right_menu);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_search:
                        //点击设置
                        showToast("按钮被点击了");
                        break;
                }
                return false;
            }
        });
        ctl.setTitle("");
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        ctl.setExpandedTitleColor(getResources().getColor(R.color.white));
        ctl.setExpandedTitleColor(Color.TRANSPARENT);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e(TAG,
                        "appBarLayoutHeight:" + appBarLayout.getHeight() + " getTotalScrollRange:" + appBarLayout.getTotalScrollRange() + " offSet:" + verticalOffset);
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    getToolbar().setTitleTextColor(getResources().getColor(R.color.white));
                    ctl.setTitle("详情");
                } else {
                    ctl.setTitle("");
                }
            }
        });
        loadNews();
        shineButton.bringToFront();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadNews() {
        tvTitle.setText(news.getTitle());
        tvPublishTime.setText(DateUtils.toDefaultString(news.getPublishTime()));
        tvAuthor.setText(news.getAuthor());
        tvContent.setText(news.getContent());
        Glide.with(getApplicationContext()).load(news.getImgUrl()).into(ivPic);
    }
}
