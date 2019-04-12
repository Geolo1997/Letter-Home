package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.UserRequest;
import team.dorm301.letterhome.ui.ToolbarLayout;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.h_back)
    ImageView hBack;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.user_line)
    ImageView userLine;
//    @BindView(R.id.toolbar)
//    ToolbarLayout toolbar;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_val)
    TextView userVal;
    @BindView(R.id.et_change_name)
    EditText etChangeName;
    @BindView(R.id.et_change_phone_number)
    EditText etChangePhoneNumber;
    @BindView(R.id.et_change_mailbox_number)
    EditText etChangeMailboxNumber;
    @BindView(R.id.bt_confim)
    Button btConfim;
    @BindView(R.id.cb_choose_man)
    CheckBox cbChooseMan;
    @BindView(R.id.cb_choose_woman)
    CheckBox cbChooseWoman;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_message;
    }

    public void loadProfile(){
        HttpClient.request(UserRequest.class)
                .getMyProfile()
                .subscribeOn(Schedulers.io())                   // 在IO线程发起网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(User user) {
                        userName.setText(user.getUsername()) ;
                        userVal.setText(user.getPhone());
                        etChangeName.setText(user.getUsername());
                        etChangeMailboxNumber.setText(user.getEmail());
                        etChangePhoneNumber.setText(user.getPhone());
                        if (user.isSex()){
                            cbChooseMan.setChecked(true);
                        }
                        else cbChooseWoman.setChecked(true);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText( ProfileActivity.this,"网络错误，更新失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.test123)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(hBack);
        Glide.with(this).load(R.drawable.test123)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(hHead);
        this.loadProfile();
    }

    @OnClick(R.id.bt_confim)
    public void onViewClicked() {
        User user = new User();
        user.setUsername(etChangeName.getText().toString());
        user.setEmail(etChangeMailboxNumber.getText().toString());
        user.setPhone(etChangePhoneNumber.getText().toString());
        if(cbChooseMan.isChecked())
        {
            user.setSex(cbChooseMan.isChecked());
        }
        if (cbChooseWoman.isChecked())
        {
            user.setSex(false);
        }
        HttpClient.request(UserRequest.class)
                .updateProfile(user)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText( ProfileActivity.this,"更新成功",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText( ProfileActivity.this,"网络错误，更新失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
