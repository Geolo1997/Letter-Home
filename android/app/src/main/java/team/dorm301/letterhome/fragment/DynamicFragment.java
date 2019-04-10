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

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.adapter.NewsAdapter;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.NewsRequest;
import team.dorm301.letterhome.ui.ToolbarLayout;
import team.dorm301.letterhome.util.RecyclerViewUtils;

public class DynamicFragment extends BaseFragment {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.toolbar)
    ToolbarLayout toolbar;

    private NewsAdapter newsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        // 设置toolbar
        toolbar.setTitle("新闻");
        Button backButton = toolbar.getBtToolbarLeft();
        backButton.setEnabled(false);
        backButton.setText("");
        // 设置适配器
        newsAdapter = new NewsAdapter(getBaseActivity());
        RecyclerViewUtils.setDefaultConfig(getContext(), rvNews);
        rvNews.setAdapter(newsAdapter);
        // 刷新事件监听
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
                refresh.setRefreshing(false);
            }
        });
        // 设置数据
        update();
        return rootView;
    }

    private void update() {
        HttpClient.request(NewsRequest.class)
                .getNewsList()
                .subscribeOn(Schedulers.io())                   // 在IO线程发起网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<News> news) {
                        newsAdapter.setDataList(news);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getBaseActivity().showToast("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
