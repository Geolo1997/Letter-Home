package team.dorm301.letterhome.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import butterknife.BindView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import lrq.com.addpopmenu.PopMenu;
import lrq.com.addpopmenu.PopMenuItem;
import lrq.com.addpopmenu.PopMenuItemListener;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.fragment.DynamicFragment;
import team.dorm301.letterhome.fragment.ProfileFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.space)
    SpaceNavigationView spaceNavigationView;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(R.id.fragment, DynamicFragment.class);
        initSpaceNavigationView(savedInstanceState);
    }

    private void initSpaceNavigationView(Bundle savedInstanceState) {
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("资讯", R.drawable.news));
        spaceNavigationView.addSpaceItem(new SpaceItem("我的", R.drawable.mine));
        spaceNavigationView.setCentreButtonIcon(R.drawable.write_letter);
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                PopMenu mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                        .addMenuItem(new PopMenuItem("写信", getResources(R.drawable.write_letter)))
                        .addMenuItem(new PopMenuItem("私密", getResources(R.drawable.write_letter)))
                        .addMenuItem(new PopMenuItem("家庭圈", getResources(R.drawable.write_letter)))

                        .setOnItemClickListener(new PopMenuItemListener() {
                            @Override
                            public void onItemClick(PopMenu popMenu, int position) {
                                switch (position) {
                                    case 0:
                                        startActivity(SendLetterActivity.class);
                                        break;
                                }
                            }
                        })
                        .build();
                mPopMenu.show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        setFragment(R.id.fragment, DynamicFragment.class);
                        break;
                    case 1:
                        setFragment(R.id.fragment, ProfileFragment.class);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });
    }
}
