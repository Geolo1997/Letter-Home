package team.dorm301.letterhome.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.activity.NewsDetailActivity;
import team.dorm301.letterhome.adapter.MaterialBannerAdapter;
import team.dorm301.letterhome.adapter.NewsAdapter;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.consts.IntentExtra;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.NewsRequest;
import team.dorm301.letterhome.util.RecyclerViewUtils;

public class DynamicFragment extends BaseFragment {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    MaterialBanner<News> materialBanner;
    List<News> newsList;
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
        // 初始化轮播图控件
        initMaterialBanner();
        // 初始化RecyclerView
        initRecyclerView();
        // 更新数据
        loadNewsList();
        return rootView;
    }

    private void initRecyclerView() {
        newsAdapter = new NewsAdapter(getBaseActivity());
        newsAdapter.setHeadView(materialBanner);
        RecyclerViewUtils.setDefaultConfig(getContext(), rvNews);
        rvNews.setAdapter(newsAdapter);
        // 刷新事件监听
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNewsList();
                refresh.setRefreshing(false);
            }
        });
    }

    private void initMaterialBanner() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.layout_banner, null);
        materialBanner = header.findViewById(R.id.material_banner);
        materialBanner.setPages(new MaterialBannerAdapter.NetImageHolderCreator(), new ArrayList<News>());
        materialBanner.setIndicator(new CirclePageIndicator(getContext()));
        materialBanner.setOnItemClickListener(new MaterialBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra(IntentExtra.NEWS, newsList.get(i));
                getActivity().startActivity(intent);
            }
        });
        //设置高度是屏幕1/4
        materialBanner.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getActivity().getWindowManager().getDefaultDisplay().getHeight() / 3));
    }

    private void loadNewsList() {
        HttpClient.request(NewsRequest.class)
                .getNewsList(5)
                .subscribeOn(Schedulers.io())                   // 在IO线程发起网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<News> news) {
                        newsList = news;
                        materialBanner.setPages(new MaterialBannerAdapter.NetImageHolderCreator(),
                                news.subList(0, 2));
                        newsAdapter.setDataList(news.subList(3, news.size()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getBaseActivity().showErrorToast("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            News news = new News(i, new Date(), "作者编号" + i, "你好的开，分foil按单价法搜ID发的啥地方所发生的dfg",
                    "fdsfdsfdsfdsfdsfdsfdsfsdfdsfsdfdsfdsfdsfdsDFdfsdf");
            news.setImgUrl("https://pub-static.haozhaopian" +
                    ".net/static/web/site/features/cn/crop/images/crop_20a7dc7fbd29d679b456fa0f77bd9525d.jpg");
            newsList.add(news);
        }
        this.newsList = newsList;
        materialBanner.setPages(new MaterialBannerAdapter.NetImageHolderCreator(),
                newsList.subList(0, 3));
        newsAdapter.setDataList(newsList.subList(3, newsList.size()));
    }
}
