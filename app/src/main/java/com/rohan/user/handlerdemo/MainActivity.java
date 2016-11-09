package com.rohan.user.handlerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("Nice job");

        }
    };

    public void myHandler(View view) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                long futureTime= System.currentTimeMillis()+10000;
                while(System.currentTimeMillis()< futureTime){
                    synchronized (this){
                        try {
                            wait(futureTime- System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


                handler.sendEmptyMessage(0);
            }
        };
        Thread load= new Thread(r);
        load.start();


    }
}
