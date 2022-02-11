package com.example.lab6_services_bound;
//package android.os.IBinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DemoService extends Service {
    private final DemoBinder binder = new DemoBinder();

    public DemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }

    public String getFirstMessage() {
        return "This is the first message";
    }

    public String getSecondMessage() {
        return "This is the second message";
    }

    class DemoBinder extends Binder implements IBinder {
        public DemoService getService() {
            return DemoService.this;
        }
    }

}