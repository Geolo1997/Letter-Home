package team.dorm301.letterhome.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.activity.InboxActivity;
import team.dorm301.letterhome.activity.LoginActivity;
import team.dorm301.letterhome.activity.ProfileActivity;
import team.dorm301.letterhome.base.BaseFragment;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.AuthRequest;

public class ProfileFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.logout)
    LinearLayout logout;
    @BindView(R.id.ll_profile)
    LinearLayout llProfile;
    @BindView(R.id.history_view)
    LinearLayout historyView;
    @BindView(R.id.ll_wallet_buttons)
    LinearLayout walletButtons;
    private int isWalletButtonsOn;



    @Override
    protected int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().setToolbarTitle("我的");
        isWalletButtonsOn = 0;
        return rootView;
    }




    @OnClick({R.id.ll_profile,R.id.wallet, R.id.history_view, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_profile:
                getBaseActivity().startActivity(ProfileActivity.class);
                break;
            case  R.id.wallet:
                if(isWalletButtonsOn==1){
                    walletButtons.setVisibility(View.GONE);
                    isWalletButtonsOn = 0;
                }
                else{
                    walletButtons.setVisibility(View.VISIBLE);
                    togetherProperty(walletButtons);
                    isWalletButtonsOn = 1;
                }
                break;
            case R.id.history_view:
                getBaseActivity().startActivity(InboxActivity.class);
                break;
            case R.id.logout:
                HttpClient.request(AuthRequest.class)
                        .logout()
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                DAOService.getInstance().saveLogInfo(null);
                                Yunzhi.clearToken();
                                getBaseActivity().startActivityAndFinish(LoginActivity.class);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                getBaseActivity().showErrorToast("网络错误");
                            }
                        });
                break;
        }
    }

    public void togetherProperty(View myView) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(myView, "alpha", 0f, 1.0f);
//        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(myView, "translationX", 100, 400);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(myView, "translationY", -30f, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim, transYAnim);
        set.setDuration(300);
        set.start();
    }

//    @OnClick(R.id.history_view)
//    public void onViewClicked() {
//        getBaseActivity().startActivity(InboxActivity.class);
//    }
//
//
//    @OnClick(R.id.ll_profile)
//    public void toProfileClicked() {
//        getBaseActivity().startActivity(ProfileActivity.class);
//    }


}
