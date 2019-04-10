package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.fragment.DynamicFragment;
import team.dorm301.letterhome.fragment.ProfileFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_dynamic)
    Button selectedButton;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onViewClicked(selectedButton);
    }

    @OnClick({R.id.bt_dynamic, R.id.bt_send_letter, R.id.bt_profile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_dynamic:
                setFragment(R.id.fragment, DynamicFragment.class);
                break;
            case R.id.bt_send_letter:
                startActivity(SendLetterActivity.class);
                break;
            case R.id.bt_profile:
                //startActivity(ProfileActivity.class);
                setFragment(R.id.fragment, ProfileFragment.class);
                break;
        }
    }


}
