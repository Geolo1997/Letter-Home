package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.roughike.bottombar.*;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.fragment.DynamicFragment;
import team.dorm301.letterhome.fragment.ProfileFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_dynamic)
    Button selectedButton;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onViewClicked(selectedButton);
        initState();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.tab_dynamic) {
//                    // 选择指定 id 的标签
//                    BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_dynamic);
//                    nearby.setBadgeCount(5);
//                }
                switch (tabId) {
                    case R.id.tab_dynamic:
                        setFragment(R.id.fragment, DynamicFragment.class);
                        break;
                    case R.id.tab_send:
                        startActivity(SendLetterActivity.class);
                        break;
                    case R.id.tab_my:
                        setFragment(R.id.fragment, ProfileFragment.class);
                        break;
                }
            }
        });

//        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
//            @Override
//            public void onTabReSelected(@IdRes int tabId) {
//                if (tabId == R.id.tab_favorites) {
//                    // 已经选择了这个标签，又点击一次。即重选。
//                    nearby.removeBadge();
//                }
//            }
//        });
//
//        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
//            @Override
//            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
//                // 点击无效
//                if (newTabId == R.id.tab_restaurants ) {
//                    // ......
//                    // 返回 true 。代表：这里我处理了，你不用管了。
//                    return true;
//                }
//
//                return false;
//            }
//        });
//    }
//        setSupportActionBar(tb);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initState();
    }

    public void initState() {
        bottomBar.selectTabAtPosition(0);
        setFragment(R.id.fragment, DynamicFragment.class);
    }

    @OnClick({R.id.bt_dynamic, R.id.bt_send_letter, R.id.bt_profile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_dynamic:
                setFragment(R.id.fragment, DynamicFragment.class);
                break;
            case R.id.bt_send_letter:
                startActivity(SendLetterActivity.class);
                break;
            case R.id.bt_profile:
                //startActivity(ProfileActivity.class);
                setFragment(R.id.fragment, ProfileFragment.class);
                break;
        }
    }


}
