package me.yeba.app.yebameapp.Networking;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import me.yeba.app.yebameapp.Activities.YebameMainActivity;
import me.yeba.app.yebameapp.User.RideStatus;
import me.yeba.app.yebameapp.yApp;

/**
 * Created by Javier on 24-11-14.
 */
public class YebameParseReceiver extends ParsePushBroadcastReceiver {
    /*@Override
    public void onReceive(Context context, Intent intent) {
        yApp.Dbg_Print("Receiver Action: " + intent.getAction());
        for(String s:intent.getExtras().keySet()) {
            yApp.Dbg_Print("Receiver Extras: " + s);
        }
        yApp.Dbg_Print("Receiver Data: " + intent.getDataString());

        super.onReceive(context, intent);
    }*/

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        yApp.Dbg_Print("OpenReceiver Action: " + intent.getAction());
        for (String s : intent.getExtras().keySet()) {
            yApp.Dbg_Print("OpenReceiver Extras: " + s);
        }
        yApp.Dbg_Print("OpenReceiver Data: " + intent.getDataString());

        ParseAnalytics.trackAppOpenedInBackground(intent);

        /*String uriString = null;
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            uriString = pushData.optString("uri");
        } catch (JSONException e) {
            Log.v("com.parse.ParsePushReceiver", "Unexpected JSONException when receiving push data: ", e);
        }
        Class<? extends Activity> cls = getActivity(context, intent);
        Intent activityIntent;
        if (uriString != null && !uriString.isEmpty()) {
            activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        } else {
            activityIntent = new Intent(context, YebameMainActivity.class);
        }
        activityIntent.putExtras(intent.getExtras());
        if (Build.VERSION.SDK_INT >= 16) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(cls);
            stackBuilder.addNextIntent(activityIntent);
            stackBuilder.startActivities();
        } else {
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(activityIntent);
        }*/
    }
    @Override
    protected void onPushReceive(Context context, Intent intent) {
        yApp.Dbg_Print("PushReceiver Action: "+intent.getAction());
        for(String s:intent.getExtras().keySet()) {
            yApp.Dbg_Print("PushReceiver Extras: " + s);
        }
        yApp.Dbg_Print("PushReceiver Data: "+intent.getDataString());

        String pushData = intent.getExtras().getString("com.parse.Data");
        if (!pushData.isEmpty()) {
            yApp.Dbg_Print("Push JSON: " + pushData);
            try {
                JSONObject decoder = new JSONObject(pushData);
                if (decoder.has(YebameParseKey.PUSH_TYPE_KEY.getKey())) {
                    rideInterpreter(RideStatus.getStatus(decoder.getString(
                            YebameParseKey.PUSH_TYPE_KEY.getKey())));
                }
            } catch (JSONException e) {
                //Todo: invalid push
            }
        }
        super.onPushReceive(context, intent);
    }

    private void rideInterpreter(RideStatus status) {
        switch (status) {
            case OPEN:

                break;
            case COMPLETED:
                break;
            default:
                break;
        }
    }
}
