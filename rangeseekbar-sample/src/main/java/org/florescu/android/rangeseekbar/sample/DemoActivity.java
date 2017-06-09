/*
Copyright 2015 Alex Florescu
Copyright 2014 Yahoo Inc.
Modifications copyright 2016 Agile Sports Technologies, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Agile Sports Technologies, Inc. Modifications:
- Add samples of seek bars with icon to right of left thumb and seek
  bars with different selected rectangle transparency
*/

package org.florescu.android.rangeseekbar.sample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class DemoActivity extends Activity {

    private static final int SEEK_BAR_NEW_DEFAULT_HEIGHT = 60;

    private int mInitialDefaultHeight;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Setup the new range seek bar
        RangeSeekBar<Integer> rangeSeekBar = new RangeSeekBar<Integer>(this);
        // Set the range
        rangeSeekBar.setRangeValues(15, 90);
        rangeSeekBar.setSelectedMinValue(20);
        rangeSeekBar.setSelectedMaxValue(88);

        // Add to layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.seekbar_placeholder);
        layout.addView(rangeSeekBar);

        // Seek bar for which we will set text color in code
        RangeSeekBar rangeSeekBarTextColorWithCode = (RangeSeekBar) findViewById(R.id.rangeSeekBarTextColorWithCode);
        rangeSeekBarTextColorWithCode.setTextAboveThumbsColorResource(android.R.color.holo_blue_bright);

        // Seek bar for which we will set icon on bar and its color in code
        RangeSeekBar rangeSeekBarIconOnBarWithCode = (RangeSeekBar) findViewById(R.id.rangeSeekBarIconOnBarWithCode);
        Drawable iconOnBarDrawable = ContextCompat.getDrawable(this, android.R.drawable.ic_lock_idle_alarm);
        rangeSeekBarIconOnBarWithCode.setIconOnBar(iconOnBarDrawable, Color.WHITE, true);

        RangeSeekBar rangeSeekBarOffsetIcon = (RangeSeekBar) findViewById(R.id.rangeSeekBarOffsetIcon);
        rangeSeekBarOffsetIcon.setIconOnBar(iconOnBarDrawable, Color.WHITE, true);

        // Disabled seek bar with a changeable height
        RangeSeekBar rangeSeekBarChangeHeight = (RangeSeekBar) findViewById(R.id.rangeSeekBarChangeHeight);
        rangeSeekBarChangeHeight.setEnabled(false);
        rangeSeekBarChangeHeight.setShowThumbs(false);
        mInitialDefaultHeight = rangeSeekBarChangeHeight.getDefaultHeight();
    }

    public void toggleSeekBarEnabled(View v) {
        RangeSeekBar rangeSeekBarNoThumbs = (RangeSeekBar) findViewById(R.id.rangeSeekBarNoThumbs);
        rangeSeekBarNoThumbs.setEnabled(!rangeSeekBarNoThumbs.isEnabled());
        rangeSeekBarNoThumbs.setShowThumbs(rangeSeekBarNoThumbs.isEnabled());
    }

    public void changeSeekBarHeight(View v) {
        RangeSeekBar rangeSeekBarChangeableHeight =
                (RangeSeekBar) findViewById(R.id.rangeSeekBarChangeHeight);

        int iconOnBarSide = rangeSeekBarChangeableHeight.getDefaultHeight();
        if (iconOnBarSide == mInitialDefaultHeight) {
            rangeSeekBarChangeableHeight.setDefaultHeight(SEEK_BAR_NEW_DEFAULT_HEIGHT);
        } else {
            rangeSeekBarChangeableHeight.setDefaultHeight(mInitialDefaultHeight);
        }

        rangeSeekBarChangeableHeight.invalidate();
    }
}
