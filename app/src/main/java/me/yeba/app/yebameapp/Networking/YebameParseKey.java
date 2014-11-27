package me.yeba.app.yebameapp.Networking;

/**
 * Created by Javier on 24-11-14.
 */
public enum YebameParseKey {
    CLASS_RIDE_KEY("Ride"),
    CLASS_DRIVER_KEY("Driver"),
    RIDE_USER_KEY("user"),
    RIDE_ORIGIN_LOCATION_KEY(""),
    RIDE_STATUS_KEY(""),
    /*RIDE__KEY(""),
    RIDE_KEY(""),
    RIDE_KEY(""),
    RIDE_KEY(""),
    RIDE_KEY(""),*/
    PUSH_TYPE_KEY("type");

    private String _key;
    private YebameParseKey(String key) {
        _key = key;
    }
    public String getKey(){
        return _key;
    }
    public String toString(){
        return _key;
    }
}
