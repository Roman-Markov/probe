package com.example.mentalmath.trainings;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Роман on 30.09.2017.
 */

public abstract class ABaseField implements IField{

    protected ViewGroup mLayout;

    public ABaseField(LayoutInflater inflater, ViewGroup container, int rID) {
        mLayout = (ViewGroup) inflater.inflate(rID, container, false);
    }

    public ABaseField(ViewGroup container, @IdRes int rID) {
        mLayout = (ViewGroup) container.findViewById(rID);
    }

    @Override
    public ViewGroup getLayout() {
        return mLayout;
    }

}
