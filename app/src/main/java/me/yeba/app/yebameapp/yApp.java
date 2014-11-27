package me.yeba.app.yebameapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.MapsInitializer;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;

import me.yeba.app.yebameapp.Activities.YebameMainActivity;
import me.yeba.app.yebameapp.User.YebameSession;

/**
 * Created by Miguel Sánchez Ruiz on 18-11-14.
 */
public class yApp extends Application {

    private YebameSession currentUser;
    private static Context appContext;//Todo: Remove static

    public static final boolean DEBUG_MODE = true;

    public YebameSession getCurrentUser() {
        return currentUser;
    }

    public static void Dbg_Print(String str){
        if(DEBUG_MODE){
            Log.d("Debug_Tag: " ,str);

            Toast.makeText(appContext, str, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate() {
        InitializeServices();
        super.onCreate();
    }
    private void InitializeServices() {
        appContext = this.getApplicationContext();
        Parse.initialize(appContext,
                getResources().getString(R.string.Parse_Application_ID),
                getResources().getString(R.string.Parse_Client_ID));

        ParseFacebookUtils.initialize(getResources().getString(R.string.Facebook_App_ID));
        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully installed.");
                } else {

                    Log.e("com.parse.push", "failed to install for push", e);
                }
            }
        });

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {

                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
        MapsInitializer.initialize(appContext);
        currentUser = new YebameSession();
    }

}
