package team.dorm301.letterhome.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import team.dorm301.letterhome.R;

public class ToolbarLayout extends LinearLayout {

    private Button btToolbarLeft;
    private TextView tvToolbarTitle;
    private Button btToolbarRight;

    public ToolbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_toolbar, this);
        btToolbarLeft = findViewById(R.id.bt_toolbar_left);
        btToolbarRight = findViewById(R.id.bt_toolbar_right);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);

        btToolbarLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        btToolbarRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "没有选项", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    public Button getBtToolbarLeft() {
        return btToolbarLeft;
    }

    public Button getBtToolbarRight() {
        return btToolbarRight;
    }
}
