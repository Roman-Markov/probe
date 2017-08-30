package com.example.mentalmath;

import android.app.Fragment;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Fragment which shows statistics
 */

public class StatisticsFragment extends ListFragment {

    private DataBaseHelper db = null;
    private Cursor current = null;
    private AsyncTask task = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity(), R.layout.statistics_row, current,
                new String[]{
                        DataBaseHelper.TYPE,
                        DataBaseHelper.DATE,
                        DataBaseHelper.TIME
                }, new int[]{
                R.id.kind, R.id.date, R.id.result}, 0);

        setListAdapter(adapter);

        if (current == null) {
            db = DataBaseHelper.getInstance(getActivity());
            task=new LoadCursorTask().execute();
        }
    }


    abstract private class BaseTask<T> extends AsyncTask<T, Void, Cursor> {
        @Override
        public void onPostExecute(Cursor result) {
            ((CursorAdapter) getListAdapter()).changeCursor(result);
            current = result;
            task = null;
        }

        Cursor doQuery() {
            Cursor result =
                    db.getReadableDatabase().query(
                            DataBaseHelper.TABLE, new String[] {
                                    "ROWID AS _id", DataBaseHelper.TYPE,
                                    DataBaseHelper.DATE, DataBaseHelper.TIME},
                            null, null, null, null,DataBaseHelper.TIME);
            Log.e(getClass().getSimpleName(), "Count: " + result.getCount());
            return result;
        }
    }

    private class LoadCursorTask extends BaseTask<Void> {
        @Override
        protected Cursor doInBackground(Void... params) {

            return(doQuery());
        }
    }
    @Override
    public void onDestroy() {
        if (task != null) {
            task.cancel(false);
        }
        ((CursorAdapter) getListAdapter()).getCursor().close();
        db.close();

        super.onDestroy();
    }
}
