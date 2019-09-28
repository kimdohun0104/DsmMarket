package com.dsm.dsmmarketandroid.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.dsm.dsmmarketandroid.R;

import java.util.Objects;

public class ToolbarAlphaScrollBehavior extends CoordinatorLayout.Behavior<androidx.appcompat.widget.Toolbar> {
    private static final String TAG = "ToolbarAlphaBehavior";
    private int offset = 0;
    private Context context;

    public ToolbarAlphaScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        int startOffset = 0;
        int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.product_detail_header) - child.getHeight();
        offset += dyConsumed;
        if (offset <= startOffset) {
            child.getBackground().setAlpha(0);
        } else if (offset < endOffset) {
            float precent = (float) (offset - startOffset) / endOffset;
            int alpha = Math.round(precent * 255);
            child.getBackground().setAlpha(alpha);
        } else if (offset >= endOffset) {
            child.getBackground().setAlpha(255);
        }

        MenuItem menuItem = child.getMenu().getItem(0);

        if (offset > 400) {
            child.setNavigationIcon(R.drawable.ic_back_black);
            child.setTitleTextColor(context.getResources().getColor(R.color.black));
            child.setOverflowIcon(context.getDrawable(R.drawable.ic_menu_black));
            if (!Objects.equals(menuItem.getIcon().getConstantState(), Objects.requireNonNull(context.getDrawable(R.drawable.ic_heart_full_red)).getConstantState()))
                menuItem.setIcon(R.drawable.ic_heart_empty_black);
        } else {
            child.setNavigationIcon(R.drawable.ic_back_white);
            child.setTitleTextColor(context.getResources().getColor(R.color.white));
            child.setOverflowIcon(context.getDrawable(R.drawable.ic_menu));
            if (!Objects.equals(menuItem.getIcon().getConstantState(), Objects.requireNonNull(context.getDrawable(R.drawable.ic_heart_full_red)).getConstantState()))
                menuItem.setIcon(R.drawable.ic_heart_white);
        }
    }
}