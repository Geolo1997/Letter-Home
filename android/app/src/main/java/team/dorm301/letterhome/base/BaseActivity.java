package team.dorm301.letterhome.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.*;
import com.sdsmdg.tastytoast.TastyToast;
import java.util.HashMap;
import java.util.Map;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.permission.ActivityCallback;
import team.dorm301.letterhome.permission.ActivityRequestCode;
import team.dorm301.letterhome.permission.PermissionCallback;
import team.dorm301.letterhome.permission.PermissionRequestCode;
import team.dorm301.letterhome.util.ActivityCollector;

import pers.geolo.util.SingletonHolder;


public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private final Map<ActivityRequestCode, ActivityCallback> activityCallbackMap = new HashMap<>();
    private final Map<PermissionRequestCode, PermissionCallback> permissionCallbackMap = new HashMap<>();

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @Nullable
    @BindView(R.id.bt_back)
    Button btBack;

    @Optional
    @OnClick(R.id.bt_back)
    public void back() {
        finish();
    }

    protected abstract int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().add(this);
        setContentView(getContentView());
        ButterKnife.bind(this);
        initToolBar();
        setSupportActionBar(toolbar);
    }

    protected void initToolBar() {
        if (toolbar != null) {
            toolbar.setTitle("");
        }
    }

    public void setBackEnable(boolean enable) {
        if (enable) {
            btBack.setVisibility(View.VISIBLE);
        } else {
            btBack.setVisibility(View.GONE);
        }
    }

    public void setToolbarTitle(String toolbarTitle) {
        if (this.toolbarTitle != null) {
            this.toolbarTitle.setText(toolbarTitle);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().remove(this);
    }

    public void addActivityRequest(ActivityRequestCode requestCode, ActivityCallback callback) {
        activityCallbackMap.put(requestCode, callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ActivityRequestCode activityRequestCode = ActivityRequestCode.values()[requestCode];
        ActivityCallback callback = activityCallbackMap.get(activityRequestCode);
        if (callback != null) {
            if (resultCode == RESULT_OK) {
                callback.onSuccess(data);
            } else {
                callback.onFailure();
            }
        }
    }

    public void requestPermission(PermissionRequestCode requestCode, String permission, PermissionCallback callback) {
        permissionCallbackMap.put(requestCode, callback);
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode.ordinal());
    }

    public boolean havePermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionRequestCode permissionRequestCode = PermissionRequestCode.values()[requestCode];
        PermissionCallback callback = permissionCallbackMap.get(permissionRequestCode);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            callback.onSuccess();
        } else {
            callback.onFailure();
        }
    }


    public void startActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public void startActivityAndFinish(Class<? extends Activity> activityClass) {
        startActivity(activityClass);
        finish();
    }

    public void setFragment(int viewId, Class<? extends BaseFragment> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(viewId, SingletonHolder.getInstance(fragmentClass));
        transaction.commit();
    }

    public void setFragment(int viewId, Class<? extends BaseFragment> fragmentClass, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        BaseFragment baseFragment = SingletonHolder.getInstance(fragmentClass);
        baseFragment.setArguments(bundle);
        transaction.replace(viewId, baseFragment);
        transaction.commit();
    }

    public void showToast(String message) {
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        TastyToast.makeText(getApplicationContext(), message, TastyToast.LENGTH_SHORT, TastyToast.INFO);
    }

    public void showErrorToast(String message) {
        TastyToast.makeText(getApplicationContext(), message, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    public void showLongToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    public int getColorOf(int res) {
        return ContextCompat.getColor(this, res);
    }

    public Drawable getResources(int res) {
        return ResourcesCompat.getDrawable(getResources(), res, null);
    }
}
