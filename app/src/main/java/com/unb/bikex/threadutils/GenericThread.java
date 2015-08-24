package com.unb.bikex.threadutils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Charles on 8/16/2015.
 */
public abstract class GenericThread implements Runnable{
    protected ICallbackThread iListener;

    public abstract void doBackground();

    public void setListener(ICallbackThread iListener){
        this.iListener = iListener;
    }

    public void notifyListener() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                iListener.notifyListener();
            }
        });
    }

    @Override
    public void run(){
        doBackground();
        notifyListener();
    }

}
