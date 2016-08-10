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

import com.siziksu.tic_tac_toe.App;
import com.siziksu.tic_tac_toe.R;
import com.siziksu.tic_tac_toe.common.ActivityCommon;
import com.siziksu.tic_tac_toe.common.Logger;
import com.siziksu.tic_tac_toe.presenter.IGamePresenter;
import com.siziksu.tic_tac_toe.presenter.IGameView;

public class MainActivity extends AppCompatActivity implements IGameView {

    private IGamePresenter presenter;

    private ViewGroup blocker;
    private TextView statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.enableLog(false);
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.get().applyToolBarStyleWithHome(this, defaultToolbar);
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
        presenter = App.gameModule().getGame();
        presenter.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        presenter.printStatistics();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
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
    public void showBlocker(boolean value) {
        blocker.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    public void printStatistics(String stats) {
        statistics.setText(stats);
    }
}
