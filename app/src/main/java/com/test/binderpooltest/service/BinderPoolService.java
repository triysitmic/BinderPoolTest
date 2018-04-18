package com.test.binderpooltest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.binderpooltest.BinderPool;

/**
 * Created by zhengyanda on 2018/4/18.
 */

public class BinderPoolService extends Service {

    private static final String TAG = "BinderPoolService";

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return mBinderPool;
    }
}
