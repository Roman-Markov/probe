package com.example.mentalmath.core;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mentalmath.R;

/**
 * Created by Роман on 07.10.2017.
 */

public class Helper {

    public static int getColorById(@ColorRes int resourceId, View v) {
        int color;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            color = v.getContext().getColor(resourceId);
        } else{
            color = v.getResources().getColor(resourceId);
        }
        return color;
    }

    public static SpannableStringBuilder paintString(CharSequence str, @ColorRes int rId, View v) {
        SpannableStringBuilder tempSpannable = new SpannableStringBuilder(str);

        int color = Helper.getColorById(R.color.red, v);
        tempSpannable.setSpan(new ForegroundColorSpan(color), 0, tempSpannable.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        str = tempSpannable;
        return (SpannableStringBuilder) str;
    }

    public static SpannableStringBuilder insertStringToStart(CharSequence begin, SpannableStringBuilder origin) {
        SpannableStringBuilder temp = new SpannableStringBuilder();
        temp.append(begin);
        temp.append(origin);
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
}
