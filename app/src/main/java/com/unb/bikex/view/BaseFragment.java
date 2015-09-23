package com.unb.bikex.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.unb.bikex.BaseActivity;

/**
 * Created by Charles on 9/23/2015.
 */
public abstract class BaseFragment extends Fragment {

    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);
        initPresenterView();
        ((BaseActivity) getActivity()).inject(this);
    }

    public abstract void initPresenterView();
}
