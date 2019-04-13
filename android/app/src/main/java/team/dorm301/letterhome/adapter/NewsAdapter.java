package team.dorm301.letterhome.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import com.bumptech.glide.Glide;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.activity.NewsDetailActivity;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.base.BaseRecyclerViewAdapter;
import team.dorm301.letterhome.consts.IntentExtra;
import team.dorm301.letterhome.entity.News;
import team.dorm301.letterhome.util.DateUtils;

public class NewsAdapter extends BaseRecyclerViewAdapter<News, NewsAdapter.NewsViewHolder> {

    private View headView;

    public NewsAdapter(BaseActivity activity) {
        super(activity);
    }

    public void setHeadView(View headView) {
        this.headView = headView;
        notifyItemInserted(0);//插入下标0位置
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 1 : 0;
    }

    @Override
    public int getItemViewId() {
        return R.layout.item_news;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new NewsViewHolder(headView);
        }
        return super.onCreateViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        if (i == 0) {
            return;
        }
        News news = getDataList().get(i - 1);
        newsViewHolder.tvTitle.setText(news.getTitle());
        newsViewHolder.tvPublishTime.setText(DateUtils.toDefaultString(news.getPublishTime()));
        Glide.with(getActivity()).load(news.getImgUrl()).into(newsViewHolder.ivImg);
    }


    public class NewsViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {

        @Nullable
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @Nullable
        @BindView(R.id.tv_publish_time)
        TextView tvPublishTime;
        @Nullable
        @BindView(R.id.iv_img)
        ImageView ivImg;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Optional
        @OnClick(R.id.ll_news_item)
        public void onViewClicked() {
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra(IntentExtra.NEWS, getDataList().get(getAdapterPosition() - 1));
            getActivity().startActivity(intent);
        }
    }
}
