package me.yeba.app.yebameapp.Initialization;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import me.yeba.app.yebameapp.Networking.NetworkUtils;
import me.yeba.app.yebameapp.yApp;

/**
 * Created by Javier on 24-11-14.
 */
public class InitServices extends AsyncTask<SplashActivity, String, InitErrorType> {
    private Activity parentActivity;
    protected InitErrorType doInBackground(SplashActivity... p) {
        parentActivity = p[0];
        if (NetworkUtils.CheckInternetConnection()) {
            //((yApp)parentActivity.getApplication()).InitializeServices();

        } else {
            // Todo: No internet error
        }

        return InitErrorType.Error_1;
    }

    protected void onProgressUpdate(String progress) {
        // Todo: Update progress
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(InitErrorType result) {

        //parentActivity.StartMainActivity();
        //showDialog("Downloaded " + result + " bytes");

    }
}
