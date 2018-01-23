package com.lc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by LiangCheng on 2017/11/29.
 */

public class MainActivity extends Fragment {
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.layout_main, container, false);
        ((RootActivity) getActivity()).setCurretMenuIndex(0);
        introAnimate();
        onClick();
        return root;
    }

    private void introAnimate() {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                root.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                TransitionHelper.startIntroAnim(root, showShadowListener);
                hideShadow();
            }
        });
    }

    private void onClick() {
        root.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionHelper.startRevertFromMenu(root, showShadowListener);
            }
        });
        root.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionHelper.startExitAnim(root);
            }
        });
        root.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionHelper.animateToMenuState(root, showShadowListener);
            }
        });
        root.findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.setIntroAnimate(true);
                ((RootActivity) getActivity()).goToFragment(mainActivity);
            }
        });
    }

    private boolean introAnimate;

    public void setIntroAnimate(boolean introAnimate) {
        this.introAnimate = introAnimate;
    }

    private void showShadow() {
        View actionbarShadow = root.findViewById(R.id.actionbar_shadow);
        actionbarShadow.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(actionbarShadow, View.ALPHA, 0, 0.8f).setDuration(400).start();
    }

    private void hideShadow() {
        View actionbarShadow = root.findViewById(R.id.actionbar_shadow);
        actionbarShadow.setVisibility(View.GONE);
    }

    AnimatorListenerAdapter showShadowListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            showShadow();
        }
    };
}
