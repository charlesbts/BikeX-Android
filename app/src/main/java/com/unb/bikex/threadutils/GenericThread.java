package com.unb.bikex.threadutils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Charles on 8/16/2015.
 */
public abstract class GenericThread implements Runnable{
    protected ICallbackThread iListener;
    protected int threadId;

    public abstract void doBackground();

    public void setListener(ICallbackThread iListener){
        this.iListener = iListener;
    }

    public void notifyListener(final int threadId) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                iListener.notifyListener(threadId);
            }
        });
    }

    @Override
    public void run(){
        doBackground();
        notifyListener(threadId);
    }

}
