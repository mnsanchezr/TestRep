package me.yeba.app.yebameapp.User;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by Javier on 24-11-14.
 */
public class Ride {

    // Class Key
    private final static String CLASS_RIDE_KEY = "";
    // Class Ride Keys
    private final static String RIDE_STATUS_KEY = "";
    private final static String RIDE_ORIGIN_KEY = "";
    //private final static String RIDE_ORIGIN_LOCATION_KEY    = "";
    //private final static String RIDE_DESTINATION_KEY        = "";
    //private final static String RIDE_USER_KEY               = "";
    private final static String RIDE_AMOUNT_KEY = "";
    private final static String RIDE_ID_KEY = "";
    private final static String RIDE_ORIGIN_LOCATION_KEY = "";
    private final static String RIDE_DESTINATION_KEY = "";
    private final static String RIDE_USER_KEY = "";

    private ParseObject _ride;

    private Ride() {
    }

    private Ride(ParseObject ride) {
        _ride = ride;
        this.Update();
    }

    public RideStatus getStatus() {
        return RideStatus.getStatus(_ride.getString(RIDE_STATUS_KEY));
    }

    public void Update() {
        if (_ride.isDirty()) {
            _ride.saveInBackground(new rideCallback());
        }
    }

    public void Cancel() {
        //Todo: Cancelar ride
    }

    private class rideCallback extends SaveCallback {
        @Override
        public void done(ParseException e) {
            if (e == null) {
                //Todo: ride ask done
            } else {
                //Todo: Error saved ride
            }
        }
    }


    public static final class RideBuilder {

        private RideBuilder() {
        }

        public static Ride askForRide(ParseUser user, String destination, String origin, LatLng originLocation, int amount) {
            ParseObject rideRequest = new ParseObject(CLASS_RIDE_KEY);
            rideRequest.put(RIDE_STATUS_KEY, RideStatus.OPEN.getKey());
            rideRequest.put(RIDE_USER_KEY, user);
            rideRequest.put(RIDE_ORIGIN_KEY, origin);
            rideRequest.put(RIDE_ORIGIN_LOCATION_KEY,
                    new ParseGeoPoint(originLocation.latitude, originLocation.longitude));
            rideRequest.put(RIDE_DESTINATION_KEY, destination);
            rideRequest.put(RIDE_AMOUNT_KEY, amount);
            return new Ride(rideRequest);
        }

        public static Ride acceptRide(String rideId) {
            final Ride ride = new Ride();
            new ParseQuery<ParseObject>(CLASS_RIDE_KEY)
                    .whereEqualTo(RIDE_ID_KEY, rideId)
                    .findInBackground(new acceptRideCallback(ride));
            return ride;
        }

        private static class acceptRideCallback extends FindCallback<ParseObject> {
            private Ride _requestedRide;

            public acceptRideCallback(Ride requestedRide) {
                _requestedRide = requestedRide;
            }

            @Override
            public void done(List<ParseObject> rides, ParseException e) {
                if (e != null) {
                    _requestedRide._ride = rides.get(0);

                } else {
                    //Todo: Ride don't exist or internet error
                }
            }
        }
    }

}
