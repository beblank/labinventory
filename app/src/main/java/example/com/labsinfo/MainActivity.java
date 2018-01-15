package example.com.labsinfo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import example.com.labsinfo.common.RxBaseActivity;
import example.com.labsinfo.common.RxBus;
import example.com.labsinfo.common.Utils;
import example.com.labsinfo.fragments.AddItemFragment;
import example.com.labsinfo.fragments.MainFragment;
import example.com.labsinfo.fragments.OrderListFragment;
import example.com.labsinfo.fragments.ToolListFragment;
import io.reactivex.functions.Consumer;

public class MainActivity extends RxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(getFragmentManager(), new MainFragment(), R.id.activity_base_content, Utils.MAIN_FRAGMENT, false);

    }

    private void changeFragment(FragmentManager fm, Fragment f, int contentID, String tag, Boolean cleanStack) {
        FragmentTransaction ft = fm.beginTransaction();
        if (cleanStack) {clearBackStack(fm);}
        ft.replace(contentID, f, tag)
                .addToBackStack(null)
                .commit();
    }

    private void clearBackStack(FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fm.getBackStackEntryAt(0);
            fm.popBackStack(first.getId(), android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        manageSubscription();
    }

    private void manageSubscription() {
        subscription.add(RxBus.getInstance().toObservable().subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                if (o == Utils.TOOL_LIST) {
                    changeFragment(getFragmentManager(), new ToolListFragment(), R.id.activity_base_content, Utils.TOOL_LIST, false);
                } else if (o == Utils.ORDER_LIST) {
                    changeFragment(getFragmentManager(), new OrderListFragment(), R.id.activity_base_content, Utils.ORDER_LIST, false);
                }
            }
        }));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getFragmentManager().findFragmentByTag(Utils.MAIN_FRAGMENT);
        if (fragment.isVisible()){
        } else {
            super.onBackPressed();
        }
    }
}
