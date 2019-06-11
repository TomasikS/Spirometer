package com.example.kostra;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.AppBarLayout;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

import static com.example.kostra.R.*;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.activity_main);
        LinearLayout main = findViewById(id.mainLayout);

        final BottomNavigationView navigation = findViewById(id.navigation);

        FragmentPagerAdapter adapterViewPager;

        final ViewPager vpPager = (ViewPager) findViewById(id.ViewPager);
        adapterViewPager = new PageViewer(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);


        final Toolbar toolbar = findViewById(R.id.customtoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///final Button settingsButton = toolbar.findViewById(id.clickButton);
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //    Toast.makeText(MainActivity.this, "Example action button.", Toast.LENGTH_SHORT).show();

                Intent intentMenu = new Intent(MainActivity.this, Settings.class);

                startActivity(intentMenu);


            }

        });

//
//        View View = getLayoutInflater().inflate(layout.stack, null);
//
//
//        LinearLayout ll = View.findViewById(R.id.buttonConatiner);


//        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, string.navigation_drawer_open, string.navigation_drawer_close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//
//                ///// drawerLayout.setClickable(true);
//                // Toast.makeText(MainActivity.this, "Example action.", Toast.LENGTH_SHORT).show();
//
//
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                /// mAnimationDrawable.start();
//            }
//        };
        // mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
//        mDrawerToggle.syncState();
        //   drawerLayout.addDrawerListener(mDrawerToggle);


        final View menuItemHistory = findViewById(id.navigation_dashboard);
        final View menuItemHome = findViewById(R.id.navigation_home);
        final View menuItemAverage = findViewById(id.navigation_notifications);


        vpPager.getViewTreeObserver().

                addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {


                        if (vpPager.getCurrentItem() == 0) {
//                    menuItemHistory.setBackgroundColor(Color.WHITE);
//                    menuItemHome.setBackgroundColor(Color.GREEN);
//                    menuItemAverage.setBackgroundColor(Color.WHITE);
                            getSupportActionBar().setTitle("Last Measure");

                            navigation.getMenu().getItem(0).setChecked(true);

                        }

                        if (vpPager.getCurrentItem() == 1) {

//                    menuItemHistory.setBackgroundColor(Color.GREEN);
//                    menuItemHome.setBackgroundColor(Color.WHITE);
//                    menuItemAverage.setBackgroundColor(Color.WHITE);
                            getSupportActionBar().setTitle("History");

                            navigation.getMenu().getItem(1).setChecked(true);


                            //Toast.makeText(MainActivity.this, "index 1", Toast.LENGTH_SHORT).show();
                        }
                        if (vpPager.getCurrentItem() == 2) {
//                    menuItemHistory.setBackgroundColor(Color.WHITE);
//                    menuItemHome.setBackgroundColor(Color.WHITE);
//                    menuItemAverage.setBackgroundColor(Color.GREEN);
                            // Toast.makeText(MainActivity.this, "index 2", Toast.LENGTH_SHORT).show();
                            getSupportActionBar().setTitle("Total Average");

                            navigation.getMenu().getItem(2).setChecked(true);
                        }


                    }
                });


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //  Log.d("Item selected :", ((Integer) item.getItemId()).toString());

                switch (item.getItemId()) {
                    case id.navigation_dashboard:
                        //   Intent HistoryIntent = new Intent(MainActivity.this, history.class);
                        //  startActivity(HistoryIntent);
                        vpPager.setCurrentItem(1);
                        // Toolbar toolbar = findViewById(id.toolbar_main);

//                        menuItemHistory.setBackgroundColor(Color.GREEN);
//                        menuItemHome.setBackgroundColor(Color.WHITE);
//                        menuItemAverage.setBackgroundColor(Color.WHITE);
                        navigation.getMenu().getItem(1).setChecked(true);

//                        if (navigation.getMenu().getItem(1).isEnabled()) {
//
//
//                        }
                        getSupportActionBar().setTitle("History");

                        break;
                    case id.navigation_home:
                        vpPager.setCurrentItem(0);
                        // Intent MainActivityIntent = new Intent(MainActivity.this, MainActivity.class);
                        // startActivity(MainActivityIntent);
                        //  Toolbar toolbar2 = findViewById(id.toolbar_main);

//                        menuItemHistory.setBackgroundColor(Color.WHITE);
//                        menuItemHome.setBackgroundColor(Color.GREEN);
//                        menuItemAverage.setBackgroundColor(Color.WHITE);

                        navigation.getMenu().getItem(0).setChecked(true);

                        getSupportActionBar().setTitle("Last Measure");
                        break;

                    case id.navigation_notifications:
                        vpPager.setCurrentItem(2);
                        // Intent AverageIntent = new Intent(MainActivity.this, total_average.class);
                        //startActivity(AverageIntent);

//
//                        menuItemHistory.setBackgroundColor(Color.WHITE);
//                        menuItemHome.setBackgroundColor(Color.WHITE);
//                        menuItemAverage.setBackgroundColor(Color.GREEN);
                        navigation.getMenu().getItem(2).setChecked(true);

                        getSupportActionBar().setTitle("Total Average");

                        break;

                }
                return false;
            }
        });


        FloatingActionButton button = findViewById(R.id.floatingActionButton);


//        Button setttingsbutton;
//        View view = (View) nw.getParent();
//        setttingsbutton = view.findViewById(R.id.settingsButton);
//
//
//        setttingsbutton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
//            }
//        });


//        TextView idtetxt = findViewById(R.id.new_game);
//        TextView firstnameText = findViewById(R.id.new_game2);
//        TextView lastnameText = findViewById(R.id.new_game3);


//        idtetxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
//            }
//        });


//        mDrawerToggle.setDrawerIndicatorEnabled(false);
//
//
//        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getApplicationContext(), "data saved inner left", Toast.LENGTH_SHORT).show();
//                drawerLayout.openDrawer(Gravity.LEFT);
//                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                    Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
//                    ///sendMessage();
//
//
////                    View inflatedView = getLayoutInflater().inflate(layout.nav_header_main, null);
////
////                    LinearLayout ll = inflatedView.findViewById(R.id.nav_header);
////                    Button settingsButton = ll.findViewById(R.id.settingsButton);
////                    settingsButton.setOnTouchListener(new View.OnTouchListener() {
////                        @Override
////                        public boolean onTouch(View v, MotionEvent event) {
////                            Toast.makeText(getApplicationContext(), "data saved to database by button", Toast.LENGTH_SHORT).show();
////                            return false;
////                        }
////                    });
//
//
//                }
//
//            }
//        });


//        drawerLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                drawerLayout = (DrawerLayout) findViewById(id.drawer_layout2);
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//                // Toast.makeText(getApplicationContext(), String.valueOf(x), Toast.LENGTH_SHORT).show();
//                // Toast.makeText(getApplicationContext(), String.valueOf(y), Toast.LENGTH_SHORT).show();
//                if ((x >= 445) && (x <= 800) && (y < 150) && (y > 50))
//
//
//                //
//                //
//
////                settingsButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Toast.makeText(getApplicationContext(), "data saved button 2", Toast.LENGTH_SHORT).show();
////                    }
////                });
//                {
//                    Toast.makeText(getApplicationContext(), "data saved to database", Toast.LENGTH_SHORT).show();
//
//                    TextView id = findViewById(R.id.new_game);
//                    TextView firstnameText = findViewById(R.id.new_game2);
//                    TextView lastnameText = findViewById(R.id.new_game3);
//                    EditText editText = findViewById(R.id.textid);
//
//
//                    id.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(getApplicationContext(), "data click", Toast.LENGTH_SHORT).show();
//                        }
//                    });
////                    LinearLayout linearLayout = findViewById(R.id.mainLayout);
////                    drawerLayout.removeViewAt(2);
////                    drawerLayout.removeViewAt(0);
////                    drawerLayout.removeViewAt(0);
////                    Toast.makeText(getApplicationContext(), String.valueOf(drawerLayout.getChildCount()), Toast.LENGTH_SHORT).show();
////                    // drawerLayout.addView(navigation);
////                    /// drawerLayout.inflate(getBaseContext(), R.layout.savedsettings, null);
////
////
////                    EditText firstname = new EditText(getBaseContext());
////                    EditText lastname = new EditText(getBaseContext());
////
////
////
////                    drawerLayout.addView(firstname);
////                    drawerLayout.addView(lastname);
////                    drawerLayout.addView(id);
//                }
//                return false;
//            }
//        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//
//            case id.new_game: {
//                Toast.makeText(getApplicationContext(), "dds", Toast.LENGTH_SHORT).show();
//                break;
//            }
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            Toast.makeText(getApplicationContext(), "dds", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        Toast.makeText(getApplicationContext(), "dds", Toast.LENGTH_SHORT).show();
//        return super.onOptionsItemSelected(item);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.layout.activity_main_drawer, menu);
////        View inflatedView = getLayoutInflater().inflate(layout.activity_main_drawer, null);
//
//        return true;
//    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_main_drawer, menu);
//        return true;
//    }


    //    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//        Toast.makeText(getApplicationContext(), "dds", Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        mDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return true;
//    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_settings, menu);
//        return true;
//    }
//
//    public void showMenu(View v) {
//        PopupMenu popup = new PopupMenu(this, v);
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(menu.activity_settings, popup.getMenu());
//        popup.show();
//    }


}















