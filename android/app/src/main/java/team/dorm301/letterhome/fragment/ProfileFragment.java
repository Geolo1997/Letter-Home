package team.dorm301.letterhome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.activity.InboxActivity;
import team.dorm301.letterhome.activity.LetterDetailActivity;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.ui.ToolbarLayout;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.tv_profile)
    TextView tvProfile;

    @Override
    protected int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().setToolbarTitle("我的");
        return rootView;
    }

    @OnClick(R.id.history_view)
    public void onViewClicked() {
        getBaseActivity().startActivity(InboxActivity.class);
    }
}
