package team.dorm301.letterhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;


public class WriteMailContentActivity extends BaseActivity {

    @BindView(R.id.theme_edit_text)
    EditText titleEditText;
    @BindView(R.id.content_edit_text)
    EditText contentEditText;
    @BindView(R.id.send_button)
    Button sendMailButton;


    @Override
    protected int getContentView() {
        return R.layout.activity_write_mail_content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //UtilsBarStyle.setActionBar(this);
    }

    @OnClick(R.id.send_button)
    public void onViewClicked() {
        Intent intent = new Intent(WriteMailContentActivity.this, WriteReceiverInformationActivity.class);
        intent.putExtra("title", titleEditText.getText().toString())
                .putExtra("content", contentEditText.getText().toString());
        startActivity(intent);
    }
}
