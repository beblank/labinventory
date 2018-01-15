package example.com.labsinfo.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.labsinfo.R;
import example.com.labsinfo.common.BaseFragment;
import example.com.labsinfo.common.RxBus;
import example.com.labsinfo.common.Utils;
import example.com.labsinfo.database.DBManager;
import example.com.labsinfo.database.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */


public class ToolListFragment extends BaseFragment {

    @BindView(R.id.searchFilter)
    EditText searchFilter;
    @BindView(R.id.tool_list_view)
    ListView listView;

    private DBManager dbManager;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.QUANTITY};

    final int[] to = new int[] { R.id.id, R.id.title, R.id.qty };


    public ToolListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tool_list, container, false);

        bind(this, view);

        dbManager = new DBManager(getActivity());
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        adapter = new SimpleCursorAdapter(getActivity(),
                R.layout.item_list_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        searchFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence,int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbManager.fetchItemsByName(constraint.toString());
            }
        });

        return view;
    }

}
