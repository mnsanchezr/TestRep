package me.yeba.app.yebameapp.Maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;

/**
 * Created by Javier on 20-11-14.
 */
public final class MapUtils {
    private MapUtils(){}

    public static void setUpMap(GoogleMap map) {
        map.setMyLocationEnabled(true);
        UiSettings mapUi = map.getUiSettings();
        mapUi.setZoomControlsEnabled(false);
        mapUi.setCompassEnabled(true);
        mapUi.setMyLocationButtonEnabled(true);
    }
}
