package com.siziksu.tic_tac_toe.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siziksu.tic_tac_toe.R;
import com.siziksu.tic_tac_toe.common.Logger;
import com.siziksu.tic_tac_toe.presenter.GenericView;
import com.siziksu.tic_tac_toe.presenter.MainPresenter;
import com.siziksu.tic_tac_toe.presenter.MainPresenterImpl;
import com.siziksu.tic_tac_toe.ui.commons.ActivityCommon;

public class MainActivity extends AppCompatActivity implements GenericView {

    private MainPresenter presenter;

    private ViewGroup blocker;
    private TextView statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.setDebug(false);

        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.getInstance(this).applyToolBarStyleWithHome(this, defaultToolbar);

        findViewById(R.id.replay).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.replay();
            }
        });

        statistics = (TextView) findViewById(R.id.statistics);

        blocker = ((RelativeLayout) findViewById(R.id.panel));
        blocker.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        presenter = new MainPresenterImpl(this);
        presenter.init(this);

        printStatistics();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.registerGenericView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregisterGenericView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_reset:
                presenter.resetStatistics();
                break;
        }
        return false;
    }

    @Override
    public void showBlocker() {
        blocker.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBlocker() {
        blocker.setVisibility(View.GONE);
    }

    public void printStatistics() {
        statistics.setText(presenter.getStatistics());
    }
}
