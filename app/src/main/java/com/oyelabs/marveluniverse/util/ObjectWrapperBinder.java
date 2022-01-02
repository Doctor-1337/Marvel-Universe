package com.oyelabs.marveluniverse.util;

import android.os.Binder;

import com.oyelabs.marveluniverse.model.Result;

public class ObjectWrapperBinder extends Binder {


    private final Result mData;

    public ObjectWrapperBinder(Result mData) {
        this.mData = mData;
    }

    public Result getData() {
            return mData;
        }
    }

