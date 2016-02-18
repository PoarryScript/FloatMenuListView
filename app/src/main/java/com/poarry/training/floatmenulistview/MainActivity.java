package com.poarry.training.floatmenulistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.poarry.training.floatmenulistview.adapter.ListViewAdapter;
import com.poarry.training.floatmenulistview.view.SuspendedTopListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.list_view)
    SuspendedTopListView mListview;
    private View mFloatMenuView;

    private Button firstMenu;
    private Button secondMenu;
    private Button thirdMenu;
    private ViewPager mViewPager;
    private ListViewAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        View mHeadviews = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewpager_layout, null);
        mFloatMenuView = mHeadviews.findViewById(R.id.fixheader);

        mListview.addHeaderView(mHeadviews, null, false);
        mListview.setFloatMenuView(mFloatMenuView);
        madapter = new ListViewAdapter(this);
        mListview.setAdapter(madapter);
        firstMenu = (Button) mFloatMenuView.findViewById(R.id.first_tag);
        firstMenu.setOnClickListener(this);
        secondMenu = (Button) mFloatMenuView.findViewById(R.id.second_tag);
        secondMenu.setOnClickListener(this);
        thirdMenu = (Button) mFloatMenuView.findViewById(R.id.third_tag);
        thirdMenu.setOnClickListener(this);
        mViewPager = (ViewPager) mHeadviews.findViewById(R.id.head_top);
        mViewPager.setOnClickListener(this);

        initToolBar();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_tag:
                Toast.makeText(MainActivity.this, "First_Menu", Toast.LENGTH_SHORT).show();
                mListview.setScroll2Top();
                break;
            case R.id.second_tag:
                Toast.makeText(MainActivity.this, "Second_Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.third_tag:
                Toast.makeText(MainActivity.this, "Third_Menu", Toast.LENGTH_SHORT).show();
                break;

            case R.id.head_top:
                Toast.makeText(MainActivity.this, "Head_View", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
