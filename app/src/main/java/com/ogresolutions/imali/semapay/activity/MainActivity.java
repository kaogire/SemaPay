package com.ogresolutions.imali.semapay.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ogresolutions.imali.semapay.R;
import com.ogresolutions.imali.semapay.dialog.CardRegister;
import com.ogresolutions.imali.semapay.dialog.DialogCard;
import com.ogresolutions.imali.semapay.dialog.DialogContact;
import com.ogresolutions.imali.semapay.fragment.AddContact;
import com.ogresolutions.imali.semapay.fragment.BillsHome;
import com.ogresolutions.imali.semapay.fragment.Launcher;
import com.ogresolutions.imali.semapay.fragment.SendOne;
import com.ogresolutions.imali.semapay.helper.Card;
import com.ogresolutions.imali.semapay.helper.Request;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    NavigationView mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    //    slide menu items
    private FragmentManager mFragmentManager;
    public static final String FRAGMENT_TAG = "fragment_tag";
    Card theCard;


    private int mSelectedId;
    public static Request request = new Request();
    private boolean mUserSawDrawer = false;
    private static final String SELECTED_ITEM_ID = "selected_item_id";
    private static final String FIRST_TIME = "first_time";

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (NavigationView) findViewById(R.id.nav_view);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

//        mDrawerToggle.syncState();
        if (!didUserSeeDrawer()) {
            showDrawer();
            markDrawerSeen();
        } else {
            hideDrawer();
        }
        mSelectedId = savedInstanceState == null ? R.id.nav_kwetu : savedInstanceState.getInt(SELECTED_ITEM_ID);
        navigate(mSelectedId);
    }

    private boolean didUserSeeDrawer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    private void markDrawerSeen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = true;
        sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).apply();
    }

    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void navigate(int mSelectedId) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mSelectedId == R.id.nav_kwetu) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            transaction.replace(R.id.frame_layout, new Launcher(), "home").commit();
            setTitle("Home");
        }
        if (mSelectedId == R.id.nav_send) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            transaction.replace(R.id.frame_layout, new SendOne(), FRAGMENT_TAG).addToBackStack(null).commit();
            setTitle("Send Money");
        }
        if (mSelectedId == R.id.nav_create_acc) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Intent createAccountIntent = new Intent(this, CreateAccount.class);
            startActivity(createAccountIntent);
        }
        if (mSelectedId == R.id.nav_bill) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            transaction.replace(R.id.frame_layout, new BillsHome(), FRAGMENT_TAG).addToBackStack(null).commit();
            setTitle("Pay Bill");
        }
        if (mSelectedId == R.id.nav_add_card) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Intent createAccountIntent = new Intent(this, CardRegister.class);
            startActivity(createAccountIntent);
            setTitle("Register a card");
        }
        if (mSelectedId == R.id.nav_remove_card) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Intent createAccountIntent = new Intent(this, DialogCard.class);
            startActivity(createAccountIntent);
            setTitle("All cards a card");
        }
        if (mSelectedId == R.id.nav_con) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            transaction.replace(R.id.frame_layout, new AddContact(), FRAGMENT_TAG).addToBackStack(null).commit();
            setTitle("Add to contacts");
        }
        if (mSelectedId == R.id.nav_all_con) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Intent createAccountIntent = new Intent(this, DialogContact.class);
            startActivity(createAccountIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void goToFragment(Fragment fragment, String title){
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        transaction.replace(R.id.frame_layout, fragment, FRAGMENT_TAG).addToBackStack(null).commit();
        setTitle(title);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mSelectedId = item.getItemId();

        navigate(mSelectedId);
        return true;
    }
}
