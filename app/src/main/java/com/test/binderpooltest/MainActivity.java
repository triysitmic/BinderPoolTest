package com.test.binderpooltest;

import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.text.ICUCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.test.binderpooltest.aidl.ISecurityCenter;
import com.test.binderpooltest.aidl.Icompute;
import com.test.binderpooltest.impl.ComputeImpl;
import com.test.binderpooltest.impl.SecurityCenterImpl;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Icompute compute;
    private ISecurityCenter securityCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"onCreate");

        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(MainActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        securityCenter = SecurityCenterImpl.asInterface(securityBinder);

        String msg = "2133113";

        try {
            final String password = securityCenter.encrypt(msg);
            Log.d(TAG, password);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textView = findViewById(R.id.tv);
                    textView.setText(password);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        compute = ComputeImpl.asInterface(computeBinder);
        try {
            Log.d(TAG,"" + compute.add(1,2));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
