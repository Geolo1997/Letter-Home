package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;

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

    }
}
