package team.dorm301.letterhome.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import java.util.ArrayList;
import java.util.List;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.adapter.NewsAdapter;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.entity.News;

public class DynamicFragment extends BaseFragment {


    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    NewsAdapter newsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        newsAdapter = new NewsAdapter(getBaseActivity());
        newsAdapter.setDataList(getDataList());
        // 设置RecyclerView管理器
        rvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
        // 设置添加或删除item时的动画，这里使用默认动画
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.setAdapter(newsAdapter);
        return rootView;
    }

    private List<News> getDataList() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTitle("标题" + i + "....");
            news.setContent("内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。");
            newsList.add(news);
        }
        return newsList;
    }
}
