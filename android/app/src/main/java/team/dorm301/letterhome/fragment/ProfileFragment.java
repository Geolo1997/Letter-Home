package team.dorm301.letterhome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.ui.ToolbarLayout;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    ToolbarLayout toolbar;

    @Override
    protected int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        toolbar.setTitle("我的");
        Button backButton = toolbar.getBtToolbarLeft();
        backButton.setEnabled(false);
        backButton.setText("");
        return rootView;
    }
}
