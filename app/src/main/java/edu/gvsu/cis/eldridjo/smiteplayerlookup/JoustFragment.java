package edu.gvsu.cis.eldridjo.smiteplayerlookup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***********************************************
 * Created by Cameron Sprowls on 4/13/2016.
 ***********************************************/

public class JoustFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.joust_fragment, container, false);
    }
}