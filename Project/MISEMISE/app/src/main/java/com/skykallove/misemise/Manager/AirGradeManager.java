package com.skykallove.misemise.Manager;

import android.graphics.Color;
import android.util.Log;

import com.skykallove.misemise.R;

/**
 * Created by sky on 2018-05-30.
 */

public class AirGradeManager {

    private AirGradeManager() {
    }

    public static AirGradeWrapper get(String type, String data) {
        AirGradeWrapper result;
        switch (type) {
            case "PM10":
                result = getPM10(data);
                break;
            case "PM25":
                result = getPM10(data);
                break;
            case "O3":
                result = getO3(data);
                break;
            case "NO2":
                result = getNO2(data);
                break;
            case "CO":
                result = getCO(data);
                break;
            case "SO2":
                result = getSO2(data);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    public static int getBackgroundColorId(String data) {
        int id;
            switch (data) {
            case "최고":
                id = R.color.grade_01;
                break;

            case "좋음":
                id = R.color.grade_02;
                break;

            case "양호":
                id = R.color.grade_03;
                break;

            case "보통":
                id = R.color.grade_04;
                break;

            case "나쁨":
                id = R.color.grade_05;
                break;

            case "상당히 나쁨":
                id = R.color.grade_06;
                break;

            case "매우 나쁨":
                id = R.color.grade_07;
                break;

            case "최악":
                id = R.color.grade_08;
                break;

            default:
                id = R.color.white;
        }
        return id;
    }

    public static AirGradeWrapper getPM10(String pm10) {
        int data = Integer.parseInt(pm10);
        AirGradeWrapper result;

        if (0 <= data && data <= 15) {
            result = AirGradeWrapper.create(R.drawable.face_01, "최고", "최고message");
        } else if (16 <= data && data <= 30) {
            result = AirGradeWrapper.create(R.drawable.face_02, "좋음", "좋음message");
        } else if (31 <= data && data <= 40) {
            result = AirGradeWrapper.create(R.drawable.face_03, "양호", "양호message");
        } else if (41 <= data && data <= 50) {
            result = AirGradeWrapper.create(R.drawable.face_04, "보통", "보통message");
        } else if (51 <= data && data <= 75) {
            result = AirGradeWrapper.create(R.drawable.face_05, "나쁨", "나쁨message");
        } else if (76 <= data && data <= 100) {
            result = AirGradeWrapper.create(R.drawable.face_06, "상당히 나쁨", "상당히 나쁨message");
        } else if (101 <= data && data <= 150) {
            result = AirGradeWrapper.create(R.drawable.face_07, "매우 나쁨", "매우 나쁨message");
        } else if (151 <= data) {
            result = AirGradeWrapper.create(R.drawable.face_08, "최악", "최악message");
        } else {
            result = AirGradeWrapper.create(R.drawable.face_04, "오류", "불러오는 중 오류 발생");
            Log.e("test", "input data is wrong.");
        }
        return result;
    }

    public static AirGradeWrapper getPM25(String pm25) {
        int data = Integer.parseInt(pm25);
        AirGradeWrapper result;

        if (0 <= data && data <= 8) {
            result = AirGradeWrapper.create(R.drawable.face_01, "최고", "최고message");
        } else if (9 <= data && data <= 15) {
            result = AirGradeWrapper.create(R.drawable.face_02, "좋음", "좋음message");
        } else if (16 <= data && data <= 20) {
            result = AirGradeWrapper.create(R.drawable.face_03, "양호", "양호message");
        } else if (21 <= data && data <= 25) {
            result = AirGradeWrapper.create(R.drawable.face_04, "보통", "보통message");
        } else if (26 <= data && data <= 37) {
            result = AirGradeWrapper.create(R.drawable.face_05, "나쁨", "나쁨message");
        } else if (38 <= data && data <= 50) {
            result = AirGradeWrapper.create(R.drawable.face_06, "상당히 나쁨", "상당히 나쁨message");
        } else if (51 <= data && data <= 75) {
            result = AirGradeWrapper.create(R.drawable.face_07, "매우 나쁨", "매우 나쁨message");
        } else if (76 <= data) {
            result = AirGradeWrapper.create(R.drawable.face_08, "최악", "최악message");
        } else {
            result = AirGradeWrapper.create(R.drawable.face_04, "오류", "불러오는 중 오류 발생");
            Log.e("test", "input data is wrong.");
        }

        return result;
    }

    public static AirGradeWrapper getO3(String o3) {
        float data = Float.parseFloat(o3);
        AirGradeWrapper result;

        if (0 <= data && data <= 0.02) {
            result = AirGradeWrapper.create(R.drawable.face_01, "최고", "최고message");
        } else if (0.02 < data && data <= 0.03) {
            result = AirGradeWrapper.create(R.drawable.face_02, "좋음", "좋음message");
        } else if (0.03 < data && data <= 0.06) {
            result = AirGradeWrapper.create(R.drawable.face_03, "양호", "양호message");
        } else if (0.06 < data && data <= 0.09) {
            result = AirGradeWrapper.create(R.drawable.face_04, "보통", "보통message");
        } else if (0.09 < data && data <= 0.12) {
            result = AirGradeWrapper.create(R.drawable.face_05, "나쁨", "나쁨message");
        } else if (0.12 < data && data <= 0.15) {
            result = AirGradeWrapper.create(R.drawable.face_06, "상당히 나쁨", "상당히 나쁨message");
        } else if (0.15 < data && data <= 0.38) {
            result = AirGradeWrapper.create(R.drawable.face_07, "매우 나쁨", "매우 나쁨message");
        } else if (0.38 < data) {
            result = AirGradeWrapper.create(R.drawable.face_08, "최악", "최악message");
        } else {
            result = AirGradeWrapper.create(R.drawable.face_04, "오류", "불러오는 중 오류 발생");
            Log.e("test", "input data is wrong.");
        }

        return result;
    }

    public static AirGradeWrapper getNO2(String no2) {
        float data = Float.parseFloat(no2);
        AirGradeWrapper result;

        if (0 <= data && data <= 0.02) {
            result = AirGradeWrapper.create(R.drawable.face_01, "최고", "최고message");
        } else if (0.02 < data && data <= 0.03) {
            result = AirGradeWrapper.create(R.drawable.face_02, "좋음", "좋음message");
        } else if (0.03 < data && data <= 0.05) {
            result = AirGradeWrapper.create(R.drawable.face_03, "양호", "양호message");
        } else if (0.05 < data && data <= 0.06) {
            result = AirGradeWrapper.create(R.drawable.face_04, "보통", "보통message");
        } else if (0.06 < data && data <= 0.13) {
            result = AirGradeWrapper.create(R.drawable.face_05, "나쁨", "나쁨message");
        } else if (0.13 < data && data <= 0.20) {
            result = AirGradeWrapper.create(R.drawable.face_06, "상당히 나쁨", "상당히 나쁨message");
        } else if (0.20 < data && data <= 1.10) {
            result = AirGradeWrapper.create(R.drawable.face_07, "매우 나쁨", "매우 나쁨message");
        } else if (1.10 < data) {
            result = AirGradeWrapper.create(R.drawable.face_08, "최악", "최악message");
        } else {
            result = AirGradeWrapper.create(R.drawable.face_04, "오류", "불러오는 중 오류 발생");
            Log.e("test", "input data is wrong.");
        }

        return result;
    }

    public static AirGradeWrapper getCO(String co) {
        float data = Float.parseFloat(co);
        AirGradeWrapper result;

        if (0 <= data && data <= 1) {
            result = AirGradeWrapper.create(R.drawable.face_01, "최고", "최고message");
        } else if (1.0 < data && data <= 2.0) {
            result = AirGradeWrapper.create(R.drawable.face_02, "좋음", "좋음message");
        } else if (2.0 < data && data <= 5.5) {
            result = AirGradeWrapper.create(R.drawable.face_03, "양호", "양호message");
        } else if (5.5 < data && data <= 9.0) {
            result = AirGradeWrapper.create(R.drawable.face_04, "보통", "보통message");
        } else if (9.0 < data && data <= 12.0) {
            result = AirGradeWrapper.create(R.drawable.face_05, "나쁨", "나쁨message");
        } else if (12.0 < data && data <= 15.0) {
            result = AirGradeWrapper.create(R.drawable.face_06, "상당히 나쁨", "상당히 나쁨message");
        } else if (15.0 < data && data <= 32.0) {
            result = AirGradeWrapper.create(R.drawable.face_07, "매우 나쁨", "매우 나쁨message");
        } else if (32.0 < data) {
            result = AirGradeWrapper.create(R.drawable.face_08, "최악", "최악message");
        } else {
            result = AirGradeWrapper.create(R.drawable.face_04, "오류", "불러오는 중 오류 발생");
            Log.e("test", "input data is wrong.");
        }

        return result;
    }

    public static AirGradeWrapper getSO2(String so2) {
        float data = Float.parseFloat(so2);
        AirGradeWrapper result;

        if (0 < data && data <= 0.01) {
            result = AirGradeWrapper.create(R.drawable.face_01, "최고", "최고message");
        } else if (0.01 < data && data <= 0.02) {
            result = AirGradeWrapper.create(R.drawable.face_02, "좋음", "좋음message");
        } else if (0.02 < data && data <= 0.04) {
            result = AirGradeWrapper.create(R.drawable.face_03, "양호", "양호message");
        } else if (0.04 < data && data <= 0.05) {
            result = AirGradeWrapper.create(R.drawable.face_04, "보통", "보통message");
        } else if (0.05 < data && data <= 0.10) {
            result = AirGradeWrapper.create(R.drawable.face_05, "나쁨", "나쁨message");
        } else if (0.10 < data && data <= 0.15) {
            result = AirGradeWrapper.create(R.drawable.face_06, "상당히 나쁨", "상당히 나쁨message");
        } else if (0.15 < data && data <= 0.60) {
            result = AirGradeWrapper.create(R.drawable.face_07, "매우 나쁨", "매우 나쁨message");
        } else if (0.60 < data) {
            result = AirGradeWrapper.create(R.drawable.face_08, "최악", "최악message");
        } else {
            result = AirGradeWrapper.create(R.drawable.face_04, "오류", "불러오는 중 오류 발생");
            Log.e("test", "input data is wrong.");
        }

        return result;
    }
}
