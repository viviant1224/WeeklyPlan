package viviant.cn.weeklyplan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.fragment.CurrentPlanFragment;
import viviant.cn.weeklyplan.fragment.HistoryPlanFragment;
import viviant.cn.weeklyplan.fragment.PlanChartFragment;
import viviant.cn.weeklyplan.fragment.RoleFragment;
import viviant.cn.weeklyplan.fragment.TagFragment;
import viviant.cn.weeklyplan.fragment.TrashPlanFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HistoryPlanFragment.OnFragmentInteractionListener,
            RoleFragment.OnRoleFragmentInteractionListener,TrashPlanFragment.OnTrashPlanFragmentInteractionListener,
            TagFragment.OnTagFragmentInteractionListener,CurrentPlanFragment.OnCurrentFragmentInteractionListener,
            PlanChartFragment.OnPlanChartFragmentInteractionListener{

    FragmentManager fragmentManager;

    private RelativeLayout mainContent;

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onRoleFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTagFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCurrentFragmentInteraction(Uri uri) {

    }

    @Override
    public void OnPlanChartFragmentInteraction(Uri uri) {

    }

    @Override
    public void OnTrashPlanFragmentInteraction(String uri) {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainContent = (RelativeLayout)findViewById(R.id.main_content);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WritePlanActivity.class);

                startActivity(intent);
            }
        });
        fragmentManager = getSupportFragmentManager();



        Planthing mPlanthing = (Planthing)getIntent().getSerializableExtra(Constants.INTENT_PLAN_THING);
        if (mPlanthing != null) {
            CurrentPlanFragment currentPlanFragment = new CurrentPlanFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, currentPlanFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);//还原图标色彩
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(getString(R.string.share));
        oks.setTitleUrl("http://sharesdk.cn");
        oks.setText("ssss");
        oks.setUrl("http://sharesdk.cn");
        oks.setComment("ssss");
        oks.setSite(getString(R.string.app_name));
        oks.setSiteUrl("http://sharesdk.cn");

        oks.show(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_current) {
            CurrentPlanFragment currentPlanFragment = new CurrentPlanFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, currentPlanFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.plan_chart) {
            PlanChartFragment planChartFragment = new PlanChartFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, planChartFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_role) {
            RoleFragment roleFragment = new RoleFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, roleFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_tag) {
            TagFragment tagFragment = new TagFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, tagFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_trash) {

            TrashPlanFragment trashPlanFragment = new TrashPlanFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, trashPlanFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {
            showShare();

        } else if (id == R.id.nav_setting) {

            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
