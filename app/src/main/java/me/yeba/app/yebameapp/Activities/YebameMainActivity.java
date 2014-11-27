package me.yeba.app.yebameapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.lang.reflect.Method;

import me.yeba.app.yebameapp.Dialogs.Errors.RetryDialog;
import me.yeba.app.yebameapp.Initialization.InitServices;
import me.yeba.app.yebameapp.Initialization.SplashActivity;
import me.yeba.app.yebameapp.Maps.MapUtils;
import me.yeba.app.yebameapp.Maps.StreetMap;
import me.yeba.app.yebameapp.R;
import me.yeba.app.yebameapp.yApp;

import static me.yeba.app.yebameapp.yApp.DEBUG_MODE;

public class YebameMainActivity extends Activity {

    private StreetMap map;
    private InitServices initServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_yebame_main);
        map = new StreetMap((MapFragment) this.getFragmentManager().findFragmentById(R.id.map_fragment));
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*if (App.getCurrentUser().n()) {

        } else {

        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Todo: Not only Facebook
        switch (requestCode) {
            case 0:
                ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onNewIntent(Intent intent){
        yApp.Dbg_Print("Main Intent Action: "+intent.getAction());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement


        switch (id) {
            case R.id.action_profile:
                Intent i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.action_driver:

                double lat = map.getMarkerLocation().latitude;
                double lon = map.getMarkerLocation().longitude;
                yApp.Dbg_Print("Mi Latitud: " + lat + "\nMi Longitud: " + lon);
                break;
            case R.id.action_friends:
                (new RetryDialog()).show(getFragmentManager(), "RetryDialog");
                break;
            case R.id.action_share:
                yApp.Dbg_Print("Dir: " + map.getMarkerAddress());
                break;
            case R.id.action_help:
                ParseFacebookUtils.logIn(this,0,new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        yApp.Dbg_Print("Logged In");
                    }
                });
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yebame_main, menu);
        return true;
    }
    // Show ActionBar Icons
    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {
        if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }
                catch(NoSuchMethodException e){
                    //Log.e(TAG, "onMenuOpened", e);
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
