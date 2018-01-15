package example.com.labsinfo.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.com.labsinfo.R;
import example.com.labsinfo.common.BaseFragment;
import example.com.labsinfo.common.RxBus;
import example.com.labsinfo.common.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    private Unbinder unbinder;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        bind(this, view);

        return view;
    }

    @OnClick(R.id.tools_btn)
    public void showToolsListFragment() {
        RxBus.getInstance().send(Utils.TOOL_LIST);
    }

    @OnClick(R.id.order_btn)
    public void showOrdeerListFragment() {
        RxBus.getInstance().send(Utils.ORDER_LIST);
    }
}
