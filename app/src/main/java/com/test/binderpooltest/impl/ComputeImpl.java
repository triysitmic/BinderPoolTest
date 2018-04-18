package com.test.binderpooltest.impl;

import android.os.RemoteException;

import com.test.binderpooltest.aidl.Icompute;

/**
 * Created by zhengyanda on 2018/4/18.
 */

public class ComputeImpl extends Icompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
