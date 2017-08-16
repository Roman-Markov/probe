package com.example.mentalmath;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Environment;
import android.support.v13.app.FragmentPagerAdapter;

import java.io.File;

/**
 * Created by Роман on 08.08.2017.
 */

public class SampleAdapter extends FragmentPagerAdapter {
    private Context ctxt;
    private final String[] TITLES = new String[] {
        "Internal", "External", "Public"
    };
    private final String FILE = "test.txt";
    private final static int TAB_INTERNAL = 0;
    private final static int TAB_EXTERNAL = 1;

    public SampleAdapter(Context ctxt, FragmentManager fm) {
        super(fm);
        this.ctxt = ctxt;
    }

    @Override
    public Fragment getItem(int position) {
        File fileToEdit;
        switch(position) {
            case TAB_INTERNAL:
                fileToEdit=new File(ctxt.getFilesDir(), FILE);
                break;
            case TAB_EXTERNAL:
                fileToEdit=new File(ctxt.getExternalFilesDir(null), FILE);
                break;
            default:
                fileToEdit=
                        new File(Environment.
                                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                                FILE);
                break;
        }
        return(EditorFragment.newInstance(fileToEdit));
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public String getPageTitle(int position) {
        return TITLES[position];
    }
}
