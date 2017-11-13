package com.example.mentalmath.trainchoice;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Роман on 13.11.2017.
 */

abstract class ADescriptionInflater implements IDescriptionInflater {
    protected Resources mResources;
    protected Context mContext;
    protected int mKind;

    public ADescriptionInflater(int kind) {
        mKind = kind;
    }

    @Override
    public void init(Activity activity) {
        mContext = activity;
        mResources = mContext.getResources();
        fillMaps();
    }

    @Override
    public int getKind() {
        return mKind;
    }

    protected void fillMaps(){};
}
