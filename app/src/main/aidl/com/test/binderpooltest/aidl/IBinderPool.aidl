// IBinderPool.aidl
package com.test.binderpooltest.aidl;

// Declare any non-default types here with import statements

interface IBinderPool {

    IBinder queryBinder(int binderCode);
}
