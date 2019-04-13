package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.util.DateUtils;

public class LetterDetailActivity extends BaseActivity {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.title_text_view)
    TextView titleTextView;
    @BindView(R.id.content_text_view)
    TextView contentTextView;
    @BindView(R.id.sender_username_text_view)
    TextView senderUsernameTextView;
    @BindView(R.id.time_text_view)
    TextView timeTextView;
    @BindView(R.id.receiver_address_text_view)
    TextView receiverAddressTextView;

    private Letter letter;

    @Override
    protected int getContentView() {
        return R.layout.activity_letter_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        letter = (Letter) getIntent().getSerializableExtra("letter");
        loadLetterDetail();
    }

    public void loadLetterDetail() {
        titleTextView.setText(letter.getSubject());
        contentTextView.setText(letter.getContent());
        senderUsernameTextView.setText(letter.getUser().getUsername());
        timeTextView.setText(DateUtils.toDefaultString(letter.getSendTime()));
        String receiverInfo = letter.getTarget() + " " + letter.getRecipient();
        receiverAddressTextView.setText(receiverInfo);
    }
}
