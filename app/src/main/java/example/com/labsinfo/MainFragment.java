package example.com.labsinfo;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.com.labsinfo.common.RxBus;
import example.com.labsinfo.common.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private Unbinder unbinder;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.tools_btn)
    public void showToolsListFragment() {
        RxBus.getInstance().send(Utils.TOOL_LIST);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
