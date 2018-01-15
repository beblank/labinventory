package example.com.labsinfo.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.labsinfo.R;
import example.com.labsinfo.common.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolListFragment extends BaseFragment {


    public ToolListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tool_list, container, false);

        bind(this, view);

        return view;
    }

}
