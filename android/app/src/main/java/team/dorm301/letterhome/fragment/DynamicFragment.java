package team.dorm301.letterhome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseFragment;

public class DynamicFragment extends BaseFragment {

    @BindView(R.id.tv_dynamic)
    TextView tvDynamic;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getContentView() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        tvContent.setText("动态");
        return rootView;
    }
}
