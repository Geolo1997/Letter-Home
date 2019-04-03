package team.dorm301.letterhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;

public class SendLetterActivity extends BaseActivity {

    @BindView(R.id.send_button)
    Button sendButton;
    @BindView(R.id.theme_edit_text)
    EditText themeEditText;
    @BindView(R.id.content_edit_text)
    EditText contentEditText;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_letter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.send_button)
    public void onViewClicked() {
        Intent intent = new Intent(this, WriteReceiverInformationActivity.class);
        intent.putExtra("title",themeEditText.getText().toString());
        intent.putExtra("content",contentEditText.getText().toString());
        startActivity(intent);
    }
}
