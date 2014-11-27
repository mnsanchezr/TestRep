package me.yeba.app.yebameapp.User;

/**
 * Created by Javier on 24-11-14.
 */
public enum RideStatus {
    OPEN(1),
    TAKEN(2),
    CANCELED(3),
    IN_PROGRESS(4),
    COMPLETED(5);

    private final int _key;

    private RideStatus(int key) {
        _key = key;
    }

    public int getKey() {
        return _key;
    }

    public static RideStatus getStatus(String status) {
        int intStatus = 0;
        try {
            intStatus = Integer.parseInt(status);
        } catch (NumberFormatException ex) {
            //Todo: Invalid ride status
        }
        switch (intStatus) {
            case 1:
                return OPEN;
            case 2:
                return TAKEN;
            default:
                return CANCELED;
        }
    }
}
