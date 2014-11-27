package me.yeba.app.yebameapp.Networking;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import me.yeba.app.yebameapp.User.Ride;
import me.yeba.app.yebameapp.User.Ride.RideBuilder;

/**
 * Created by Javier on 24-11-14.
 */
public final class ParseUtils {
    //region Database Constant Keys
    // Class Keys

    private final static String CLASS_DRIVER_KEY    = "Driver";


    // Class Driver Keys
    private final static String DRIVER_LOCATION_KEY     = "";
    private final static String DRIVER_AGREEMENT_KEY    = "";
    private final static String DRIVER_VERIFY_KEY       = "";
    private final static String DRIVER_USER_KEY         = "";
    private final static String DRIVER_ACTIVE_KEY       = "";
    //endregion

    private ParseUtils(){}

    public static boolean getDriversNearby(LatLng location) {
        int radius = 5;
        new ParseQuery<ParseObject>(CLASS_DRIVER_KEY)
                .whereWithinKilometers(DRIVER_LOCATION_KEY,
                        new ParseGeoPoint(location.latitude, location.longitude), radius)
                .whereEqualTo(DRIVER_ACTIVE_KEY, true)
                .whereEqualTo(DRIVER_AGREEMENT_KEY, true)
                .whereEqualTo(DRIVER_VERIFY_KEY, true)
                .whereNotEqualTo(DRIVER_USER_KEY, ParseUser.getCurrentUser())//Todo: change current user
                .findInBackground(new getDriversNearbyCallback());
        return true;
    }
    private static class getDriversNearbyCallback extends FindCallback<ParseObject> {
        @Override
        public void done(List<ParseObject> drivers, ParseException e) {
        }
    }

    public static void acceptRide(String rideId){

    }
}
