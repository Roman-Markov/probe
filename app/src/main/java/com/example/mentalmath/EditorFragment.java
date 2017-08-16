package com.example.mentalmath;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Роман on 08.08.2017.
 */

public class EditorFragment extends Fragment {
    private static final String KEY_FILE = "file";
    private boolean isLoaded = false;
    private LoadTask loadTask = null;

    private EditText editor = null;

    static EditorFragment newInstance(File file) {
        EditorFragment frag = new EditorFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FILE, file);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.editor, container, false);

        editor = (EditText) result.findViewById(R.id.editor);
        return result;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.w(getClass().getSimpleName(), "onViewCreated() isLoaded - " + isLoaded);
        if (!isLoaded) {
            loadTask = new LoadTask();
            Log.w(getClass().getSimpleName(), "before execute loading text form file...");
            loadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    (File) getArguments().getSerializable(KEY_FILE));
        }
    }

    class LoadTask extends AsyncTask<File, Void, String> {
        @Override
        protected String doInBackground(File... files) {
            String result = null;
            File outFile = files[0];
            BufferedReader br = null;
            Log.w(getClass().getSimpleName(), "before begin loading text form file... exists - " + outFile.exists());
            if(outFile.exists()) {
                Log.w(getClass().getSimpleName(), "begin loading text form file...");
                try {
                    br = new BufferedReader(new FileReader(outFile));

                    try {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {
                            sb.append(line);
                            sb.append('\n');
                            line = br.readLine();
                        }
                        result = sb.toString();
                    } finally {
                        br.close();
                    }
                } catch (IOException e) {
                    Log.e(getClass().getSimpleName(),
                            "Exception while reading file", e);
                }
            }

            return result;
        }

        @Override
        public void onPostExecute (String str) {
            editor.setText(str);
            loadTask = null;
            isLoaded = true;
            Log.w(getClass().getSimpleName(), "onPostExecute() isLoaded - " + isLoaded);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        Log.w(getClass().getSimpleName(), "onPause() isLoaded - " + isLoaded);
        if (isLoaded) {
            new SaveThread(editor.getText().toString(),
                    (File) getArguments().getSerializable(KEY_FILE)).start();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (loadTask != null) {
            loadTask.cancel(false);
        }
        super.onDestroy();
    }

    class SaveThread extends Thread {
        private final String text;
        private final File file;

        SaveThread(String text, File file) {
            this.text = text;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                file.getParentFile().mkdirs();

                Log.w(getClass().getSimpleName(), "begin writing text to file...");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                try {
                    bw.write(text);
                    bw.flush();
                    fos.getFD().sync();
                } finally {
                    Log.w(getClass().getSimpleName(), "finish writing text to file..." + file.getAbsolutePath().toString());
                    bw.close();
                }
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception while writing file",
                        e);
            }
        }
    }












}
