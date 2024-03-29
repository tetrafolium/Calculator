package com.android2.calculator3;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android2.calculator3.BaseModule.Mode;

import java.util.List;

public abstract class CalculatorPageAdapter extends PagerAdapter {
    @Override
    public void startUpdate(View container) {
    }

    public abstract View getViewAt(int position);

    @Override
    public Object instantiateItem(View container, int position) {
        View v = getViewAt(position);
        ((ViewGroup) container).addView(v);

        return v;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewGroup) container).removeView((View) object);
    }

    @Override
    public void finishUpdate(View container) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    protected void applyBannedResourcesByPage(Logic logic, View page, Mode baseMode) {
        // Enable
        for (Mode key : logic.getBaseModule().mBannedResources.keySet()) {
            if (baseMode.compareTo(key) != 0) {
                List<Integer> resources = logic.getBaseModule().mBannedResources.get(key);
                for (Integer resource : resources) {
                    final int resId = resource.intValue();
                    View v = page.findViewById(resId);
                    if (v != null) v.setEnabled(true);
                }
            }
        }
        // Disable
        List<Integer> resources = logic.getBaseModule().mBannedResources.get(baseMode);
        for (Integer resource : resources) {
            final int resId = resource.intValue();
            View v = page.findViewById(resId);
            if (v != null) v.setEnabled(false);
        }
    }

    public abstract Iterable<View> getViewIterator();

    public abstract List<Page> getPages();
}
