package com.lc;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


public class RootActivity extends Activity {
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_root);
        makeAppFullscreen();
        if (savedInstanceState == null) {
            currentFragment = new MainActivity();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, currentFragment).commit();
        }
    }

    public void setCurretMenuIndex(int curretMenuIndex) {
        this.curretMenuIndex = curretMenuIndex;
    }

    int curretMenuIndex = 0;

    private void makeAppFullscreen() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void goToFragment(final Fragment newFragment) {
        getFragmentManager().beginTransaction().add(R.id.fragment_container, newFragment).commit();
        final Fragment removeFragment = currentFragment;
        currentFragment = newFragment;
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().remove(removeFragment).commit();
            }
        }, 2000);
    }
}
