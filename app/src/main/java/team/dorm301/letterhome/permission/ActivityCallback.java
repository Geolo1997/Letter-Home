package team.dorm301.letterhome.permission;

import android.content.Intent;

public interface ActivityCallback {
    void onSuccess(Intent data);

    void onFailure();
}
