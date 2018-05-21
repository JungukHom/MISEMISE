package com.skykallove.misemise.Activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.skykallove.misemise.Fragment.AlarmFragment;
import com.skykallove.misemise.Fragment.MainFragment;
import com.skykallove.misemise.Fragment.SettingsFragment;
import com.skykallove.misemise.Fragment.ShareFragment;
import com.skykallove.misemise.Fragment.WHOFragment;
import com.skykallove.misemise.Fragment.WeFragment;
import com.skykallove.misemise.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // 메인
    // who 기준
    // 우리 어플 기준 (미세미세=8단계)
    // 알람
    // 공유하기
    // 설정

    private int currentFragment = R.id.nav_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new MainFragment());

        setTitle("");

        // TODO: 2018-05-21 HashKey
        // Log.d("HashKey : ", HashKeyManager.getKey("com.skykallove.misemise", getPackageManager()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
            showFinishAlertDialog();
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

        if (id == currentFragment) {
            closeDrawer();
            return false;
        }

        if (id == R.id.nav_main) {
            fragment = new MainFragment();
        } else if (id == R.id.nav_who) {
            fragment = new WHOFragment();
        } else if (id == R.id.nav_we) {
            fragment = new WeFragment();
        } else if (id == R.id.nav_alarm) {
            fragment = new AlarmFragment();
        } else if (id == R.id.nav_share) {
            fragment = new ShareFragment();
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        }

        if (replaceFragment(fragment)) {
            currentFragment = id;
        }

        closeDrawer();
        return true;
    }

    private boolean replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
            return true;
        }
        else {
            return false;
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
