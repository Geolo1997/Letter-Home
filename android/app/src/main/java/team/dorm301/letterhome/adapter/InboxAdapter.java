package team.dorm301.letterhome.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.activity.LetterDetailActivity;
import team.dorm301.letterhome.activity.SendLetterActivity;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.base.BaseRecyclerViewAdapter;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.util.StringUtils;

/**
 * Created by 桀骜
 * <p>
 * inbox_layout布局中RecycleView的适配器
 */

public class InboxAdapter extends BaseRecyclerViewAdapter<Letter, InboxAdapter.ViewHolder> {

    public InboxAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public int getItemViewId() {
        return R.layout.mail_item;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Letter letter = getDataList().get(i);
        String name = letter.getRecipient();
        String lastnameString = StringUtils.getLastName(name);
        viewHolder.receiverLastName.setText(lastnameString);
        viewHolder.mailTitleTextView.setText(letter.getSubject());
        viewHolder.mailContentTextView.setText(letter.getContent());
    }

    public class ViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {

        @BindView(R.id.receiver_last_name)
        TextView receiverLastName;
        @BindView(R.id.mail_title_text_view)
        TextView mailTitleTextView;
        @BindView(R.id.mail_content_text_view)
        TextView mailContentTextView;

        @OnClick(R.id.mail_item)
        public void onClick(View view) {
            int position = getAdapterPosition();
            Letter letter = getDataList().get(position);
            Intent intent = new Intent(view.getContext(), LetterDetailActivity.class);
            intent.putExtra("letter", letter);
            view.getContext().startActivity(intent);
        }

        public ViewHolder(View view) {
            super(view);
        }
    }
}
