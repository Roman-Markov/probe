package com.example.mentalmath;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Abstract class to simplify permission system usage. Currently not used.
 */

public abstract class AbstractPermissionActivity extends Activity {
    public abstract String[] getDesiredPermissions();
    public abstract void onReady(Bundle state);
    public abstract void onDenied();
    private final String STATE_IN_PERMISSION = "in permisson";
    private static final int REQUEST_PERMISSIONS = 137;
    private boolean isInPermission = false;
    private Bundle state = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        state = savedInstanceState;
        if (state != null) {
            isInPermission = state.getBoolean(STATE_IN_PERMISSION);
        }

        if (hasAllPermission(getDesiredPermissions())) {
            onReady(state);
        } else if (!isInPermission) {
            isInPermission = true;
            ActivityCompat.requestPermissions(this, getDesiredPermissions(),
                    REQUEST_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int code, String[] permissions,
                                          int[] grants) {
        isInPermission = false;
        if (code == REQUEST_PERMISSIONS) {
            if (hasAllPermission(getDesiredPermissions())){
                onReady(state);
            } else {
                onDenied();
            }
        }
    }

    public boolean hasPermission(String permissionName) {
        return (ContextCompat.checkSelfPermission(this, permissionName)
        == PackageManager.PERMISSION_GRANTED);
    }

    public boolean hasAllPermission (String[] permissions) {
        for (String perm: permissions) {
            if (!hasPermission(perm)) {
                return false;
            }
        }
        return true;
    }

    public String[] netPermissions() {
        ArrayList<String> result = new ArrayList<String>();
        for (String perm: getDesiredPermissions()) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(STATE_IN_PERMISSION, isInPermission);
    }
}
