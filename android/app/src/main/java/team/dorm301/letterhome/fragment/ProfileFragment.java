package team.dorm301.letterhome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.activity.LoginActivity;
import team.dorm301.letterhome.activity.ProfileActivity;
import team.dorm301.letterhome.activity.InboxActivity;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.entity.LogInfo;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.AuthRequest;
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

    @OnClick(R.id.bt_logout)
    public void onLogout() {
        HttpClient.request(AuthRequest.class)
                .logout()
                . enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        DAOService.getInstance().saveLogInfo(null);
                        Yunzhi.clearToken();
                        getBaseActivity().startActivityAndFinish(LoginActivity.class);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }

    @OnClick(R.id.ll_profile)
    public void toProfileClicked() {
        getBaseActivity().startActivity(ProfileActivity.class);
    }
}
