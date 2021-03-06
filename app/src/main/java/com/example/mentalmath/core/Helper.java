package com.example.mentalmath.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import io.reactivex.internal.functions.ObjectHelper;

/**
 * Created by Роман on 07.10.2017.
 */

public class Helper {

    public static Context mGlobalContext;

    public static int getColorById(@ColorRes int resourceId, View v) {
        int color;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            color = v.getContext().getColor(resourceId);
        } else{
            color = v.getResources().getColor(resourceId);
        }
        return color;
    }

    public static SpannableStringBuilder paintString(final CharSequence str, @ColorRes int rId, View v) {
        SpannableStringBuilder tempSpannable = new SpannableStringBuilder(str);

        int color = Helper.getColorById(rId, v);
        tempSpannable.setSpan(new ForegroundColorSpan(color), 0, tempSpannable.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return tempSpannable;
    }

    public static SpannableStringBuilder insertStringToStart(CharSequence begin, SpannableStringBuilder origin) {
        SpannableStringBuilder temp = new SpannableStringBuilder();
        temp.append(begin);
        temp.append(origin);
        return temp;
    }

    public static SpannableStringBuilder insertStringToEnd(final SpannableStringBuilder origin, CharSequence end) {
        SpannableStringBuilder temp = new SpannableStringBuilder();
        temp.append(origin);
        temp.append(end);
        return temp;
    }

    public static void shiftChildren(LinearLayout layout, View view, int pos) {
        if (layout.getChildCount() == 0) {
            layout.addView(view);
        } else {
            View cursor = layout.getChildAt(pos);
            layout.addView(view, pos);
            for (int i = pos + 1; i < layout.getChildCount(); i++) {
                View temp = layout.getChildAt(i);
                // cursor points to View, which has a parent, though it was overlayed another View on it's position
                layout.removeView(cursor);
                layout.addView(cursor, i);
                cursor = temp;
            }
            layout.removeView(cursor);
            layout.addView(cursor);
        }
    }

    public static int getIndexFromStringArray(String[] array, String value) {
        ObjectHelper.requireNonNull(array, "Array can not be null");
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndexFromStringArray(int arrayId, String value) {
        String[] array = new String[0];
        try {
            array = mGlobalContext.getResources().getStringArray(arrayId);
        } catch (Resources.NotFoundException e) {
            Log.e("getIndexFromStringArray", "Array resource is not found for id: " + arrayId);
        }
        return getIndexFromStringArray(array, value);
    }

    public static void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View focusView = activity.getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }
    }
}
