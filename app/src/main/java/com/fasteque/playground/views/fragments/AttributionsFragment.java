package com.fasteque.playground.views.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.fasteque.playground.R;

/**
 * Created by danielealtomare on 27/05/15.
 * Project: Playground
 */
public class AttributionsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.attributions);
    }
}
