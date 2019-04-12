package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.UserRequest;

public class ChangeMessageActivity extends BaseActivity {

    @BindView(R.id.h_back)
    ImageView hBack;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.user_line)
    ImageView userLine;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_val)
    TextView userVal;

    @Override
    protected int getContentView() {
        return R.layout.activity_change_message;
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


        UserRequest userRequest = HttpClient.request(UserRequest.class);
        userRequest.getMyProfile()
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorToast("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
