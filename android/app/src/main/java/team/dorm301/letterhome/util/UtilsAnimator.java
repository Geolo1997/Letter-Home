package team.dorm301.letterhome.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class UtilsAnimator {
    public static void showOn(View view) {
        showOn(view,1000);
    }
    public static void showOff(View view) {
        showOff(view,1000);
    }
    public static void showOn(View view,int dur){
        ObjectAnimator trans = ObjectAnimator.ofFloat(view, "translationX", -100f, 0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(trans).with(alpha);
        animatorSet.setDuration(dur);
        animatorSet.start();
    }
    public static void showOff(View view,int dur){
        ObjectAnimator trans = ObjectAnimator.ofFloat(view, "translationX", 0f, -100f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(trans).with(alpha);
        animatorSet.setDuration(dur);
        animatorSet.start();
    }
}
