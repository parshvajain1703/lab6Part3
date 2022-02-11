package com.example.lab6_services_bound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import java.security.Provider;

public class MainActivity extends AppCompatActivity {
    private DemoService demoService;
    private boolean isBound;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DemoService.DemoBinder demoBinder = (DemoService.DemoBinder)iBinder;
            demoService = demoBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, DemoService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    public void onFirstMessageClicked(View view) {
        TextView textView = findViewById(R.id.text_view);
        textView.setText(demoService.getFirstMessage());
    }

    public void onSecondMessageClicked(View view) {
        TextView textView = findViewById(R.id.text_view);
        textView.setText(demoService.getSecondMessage());
    }
}