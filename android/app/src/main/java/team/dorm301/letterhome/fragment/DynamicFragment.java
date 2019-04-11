package team.dorm301.letterhome.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.adapter.NewsAdapter;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.ui.ToolbarLayout;
import team.dorm301.letterhome.util.RecyclerViewUtils;

public class DynamicFragment extends BaseFragment {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private NewsAdapter newsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().setToolbarTitle("资讯");
        // 设置适配器
        newsAdapter = new NewsAdapter(getBaseActivity());
        RecyclerViewUtils.setDefaultConfig(getContext(),rvNews);
        rvNews.setAdapter(newsAdapter);
        // 刷新事件监听
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsAdapter.setDataList(getDataList());
                refresh.setRefreshing(false);
            }
        });
        // 设置数据
        newsAdapter.setDataList(getDataList());
        return rootView;
    }

    private List<News> getDataList() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTitle("标题" + i + "....");
            news.setAuthor("作者" + i);
            news.setPublishTime(new Date());
            news.setContent("内容内容" + i + "。。。。。。。内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。内容内容。。。。。。。");
            newsList.add(news);
        }
        return newsList;
    }
}
