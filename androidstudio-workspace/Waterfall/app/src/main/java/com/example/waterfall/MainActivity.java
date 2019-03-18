package com.example.waterfall;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navbarBottom);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.ic_home:
                        break;

                    case R.id.ic_notifications:
                        Intent openNotifs = new Intent(MainActivity.this, Notifications.class);
                        startActivity(openNotifs);
                        break;

                    case R.id.ic_goals:
                        Intent openGoals = new Intent(MainActivity.this, Goals.class);
                        startActivity(openGoals);
                        break;

                    case R.id.ic_chart:
                        Intent openStats = new Intent(MainActivity.this, Statistics.class);
                        startActivity(openStats);
                        break;
                }

                return false;
            }
        });

        Menu bottomNavBar = bottomNavigation.getMenu();
        MenuItem item = bottomNavBar.getItem(0);
        item.setChecked(true);

        ProgressBar progressCircle = (ProgressBar) findViewById(R.id.circle_progress);
        TextView percentageDrank = (TextView) findViewById(R.id.text_percent_drank);
        TextView fractionDrank = (TextView) findViewById(R.id.text_fraction_drank);
    }
}
