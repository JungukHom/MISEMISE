package com.skykallove.misemise.Manager;

/**
 * Created by sky on 2018-05-30.
 */

public class AirGradeWrapper {

    private int faceId = 0;
    private String quality = "";
    private String message = "";

    private AirGradeWrapper(int faceId, String quality, String message) {
        this.faceId = faceId;
        this.quality = quality;
        this.message = message;
    }

    public static AirGradeWrapper create(int faceId, String quality, String message) {
        return new AirGradeWrapper(faceId, quality, message);
    }

    public int getFaceId() {
        return faceId;
    }

    public String getQuality() {
        return quality;
    }

    public String getMessage() {
        return message;
    }
}
