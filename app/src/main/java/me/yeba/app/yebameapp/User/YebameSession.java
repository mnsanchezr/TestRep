package me.yeba.app.yebameapp.User;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Javier on 18-11-14.
 */
public class YebameSession {

    private ParseUser _currentUser;
    private UserProfile _profile;
    private Ride _currentRide;
    private double _radius;

    public YebameSession() {
    }

    public boolean isDriver() {
        return false;
    }

    public boolean isLoggedIn() {
        return true;
    }

    public void tryLogIn(Activity activity, int activityCode, final UserCallback callback) {
        final YebameSession user = this;
        LogInCallback parseCall = new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                // Todo: User Errors
                callback.done(user, UserError.CLEAR);
            }
        };
        ParseFacebookUtils.logIn(activity, activityCode, parseCall);
    }

    public void endLogIn(int requestCode, int resultCode, Intent data) {
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

    public void getDriversNearLocation(LatLng location,UserCallback m) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Driver");
        ParseGeoPoint geoPoint = new ParseGeoPoint(location.latitude, location.longitude);
        query.whereWithinKilometers("", geoPoint, _radius);
        query.whereEqualTo("", true);
        m.done(this,null);
        return;
    }
    public UserProfile getProfile(){
        return _profile;
    }
    public void saveProfile(){

    }
    public void askForRide(){}
    public void cancelRide(){}

}
