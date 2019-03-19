package team.dorm301.letterhome.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.base.BaseRecyclerViewAdapter;
import team.dorm301.letterhome.entity.News;

public class NewsAdapter extends BaseRecyclerViewAdapter<News, NewsAdapter.NewsViewHolder> {


    public NewsAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public int getItemViewId() {
        return R.layout.item_news;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        News news = getDataList().get(i);
        newsViewHolder.tvTitle.setText(news.getTitle());
        newsViewHolder.tvContent.setText(news.getContent());
    }


    public class NewsViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @OnClick(R.id.ll_news_item)
        public void onViewClicked() {
            getActivity().showToast("！！");
        }
    }
}
