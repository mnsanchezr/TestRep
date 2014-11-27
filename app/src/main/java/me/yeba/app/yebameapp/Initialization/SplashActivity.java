package me.yeba.app.yebameapp.Initialization;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import me.yeba.app.yebameapp.Activities.YebameMainActivity;
import me.yeba.app.yebameapp.Networking.NetworkUtils;
import me.yeba.app.yebameapp.R;
import me.yeba.app.yebameapp.yApp;

public class SplashActivity extends Activity {

    private RotateAnimation SplashAnimation;
    private ImageView SplashImageView;
    private InitServices initServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashImageView = (ImageView) findViewById(R.id.SplashImage);
        SplashAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.142f);
        SplashAnimation.setInterpolator(new LinearInterpolator());
        SplashAnimation.setRepeatCount(Animation.INFINITE);
        SplashAnimation.setDuration(2000);
        SplashImageView.startAnimation(SplashAnimation);
        initServices = new InitServices();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initServices.execute(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        initServices.cancel(false);
    }
    protected void StartMainActivity() {

        final Intent i = new Intent(this, YebameMainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);

    }


}
