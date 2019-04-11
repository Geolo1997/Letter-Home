package team.dorm301.letterhome.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import team.dorm301.letterhome.R;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();
    Unbinder unbinder;
    private BaseActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (BaseActivity) getActivity();
        return view;
    }

    protected abstract int getContentView();

    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
