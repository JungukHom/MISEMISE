package com.skykallove.misemise.Activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.skykallove.misemise.Fragment.AlarmFragment;
import com.skykallove.misemise.Fragment.ContactFragment;
import com.skykallove.misemise.Fragment.MainFragment;
import com.skykallove.misemise.Fragment.ShareFragment;
import com.skykallove.misemise.Fragment.WHOFragment;
import com.skykallove.misemise.Fragment.WeFragment;
import com.skykallove.misemise.R;
import com.skykallove.misemise.Service.AlarmService;
import com.skykallove.misemise.Service.RestartService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String ALARM_PREF_NAME = "alarmPrefName";
    private static final String ALARM_CITY_NAME = "alarmCityName";

    public int currentFragmentID = R.id.nav_main;
    public Fragment currentFragment = null;

    public static MainActivity instance = null;

    public static List<String> alarmTime = new ArrayList<>();

    public void addAlarmTime(String time) {
        alarmTime = getAlarmTime();
        alarmTime.add(time);
        saveMyAlarmTime(alarmTime);
    }

    public List<String> getAlarmTime() {
        SharedPreferences pref = getSharedPreferences(ALARM_PREF_NAME, MODE_PRIVATE);
        Set<String> _result = pref.getStringSet("alarm", new HashSet<String>());

        List<String> result = new ArrayList<>();
        for (String str : _result) {
            result.add(str);
        }
        return result;
    }

    public void saveMyAlarmTime(List<String> info) {
        SharedPreferences pref = getSharedPreferences(ALARM_PREF_NAME, MODE_PRIVATE);
        Set<String> values = new HashSet<>();
        for (int i = 0; i < info.size(); i++) {
            values.add(info.get(i));
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet("alarm", values);
        editor.commit();
    }

    public int getCityInfo() {
        SharedPreferences prefs = getSharedPreferences(ALARM_CITY_NAME, MODE_PRIVATE);
        return prefs.getInt("defaultCity", 0);
    }

    public void saveCityInfo(int cityIndex) {
        SharedPreferences prefs = getSharedPreferences(ALARM_CITY_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("defaultCity", cityIndex);
    }

    public void saveCityInfo(String cityInfo) {
        SharedPreferences prefs = getSharedPreferences(ALARM_CITY_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("defaultCityName", cityInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarColor();

        instance = this;

        replaceFragment(MainFragment.getInstance(), R.layout.fragment_main);

        setTitle("");
//        Log.i("test_a", a);
//        Log.i("test_b", b);

        // test codes
        // TODO: 2018-05-21 HashKey
        // Log.d("HashKey : ", HashKeyManager.getKey("com.skykallove.misemise", getPackageManager()));

        // basic codes
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // basic codes

        startService(new Intent(this, AlarmService.class));
    }

    private void setActionBarColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == MainFragment.getInstance()) {
                showFinishAlertDialog();
            } else {
                replaceFragment(MainFragment.getInstance(), R.layout.fragment_main);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == currentFragmentID) {
            closeDrawer();
            return false;
        }

        if (id == R.id.nav_main) {
            fragment = MainFragment.getInstance();
        } else if (id == R.id.nav_who) {
            fragment = WHOFragment.getInstance();
        } else if (id == R.id.nav_we) {
            fragment = WeFragment.getInstance();
        } else if (id == R.id.nav_alarm) {
            fragment = AlarmFragment.getInstance();
        } else if (id == R.id.nav_share) {
            fragment = ShareFragment.getInstance();
        } else if (id == R.id.nav_contact) {
            fragment = ContactFragment.getInstance();
        }

        replaceFragment(fragment, id);

        closeDrawer();
        return true;
    }

    public void replaceFragment(Fragment fragment, int resID) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();

            currentFragmentID = resID;
            currentFragment = fragment;
        } else {
            Log.i("test", "fragment is null");
        }
    }

    private void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void showFinishAlertDialog() {
        AlertDialog.Builder finishDialog = new AlertDialog.Builder(this);
        finishDialog.setMessage("정말로 종료하시겠습니까?");

        finishDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.gc();
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        finishDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = finishDialog.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
            }
        });
        finishDialog.create().show();
    }
}
