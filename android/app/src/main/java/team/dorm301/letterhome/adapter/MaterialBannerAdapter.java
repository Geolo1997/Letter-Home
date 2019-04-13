package team.dorm301.letterhome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.holder.Holder;
import com.freegeek.android.materialbanner.holder.ViewHolderCreator;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.entity.News;

public class MaterialBannerAdapter {

    public static class NetImageHolder implements Holder<News> {

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.txt_title)
        TextView txtTitle;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_net_image_banner, null);
            ButterKnife.bind(this, view);
            return view;
        }

        @Override
        public void updateUI(Context context, int i, News news) {
            txtTitle.setText(news.getTitle());
            Glide.with(context).load(news.getImgUrl()).into(imageView);
        }
    }

    public static class NetImageHolderCreator implements ViewHolderCreator<NetImageHolder> {

        @Override
        public NetImageHolder createHolder() {
            return new NetImageHolder();
        }
    }
}
