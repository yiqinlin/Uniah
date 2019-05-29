package com.uniah.mobile.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class UniAnimImageView extends AppCompatImageView {
    private AnimationDrawable animationDrawable;

    public UniAnimImageView(Context context) {
        super(context);
        inti();
    }

    public UniAnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inti();
    }

    public UniAnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }

    public void inti() {
        animationDrawable = (AnimationDrawable) getDrawable();
        animationDrawable.start();
    }

    public void startAnimation() {
        animationDrawable.start();
    }

    public void stopAnimation() {
        animationDrawable.setVisible(true, true);
        animationDrawable.stop();
    }

    public void pauseAnimation() {
        animationDrawable.stop();
    }
}
