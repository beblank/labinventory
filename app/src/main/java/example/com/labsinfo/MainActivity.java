package example.com.labsinfo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import example.com.labsinfo.common.RxBaseActivity;
import example.com.labsinfo.common.RxBus;
import example.com.labsinfo.common.Utils;
import io.reactivex.functions.Consumer;

public class MainActivity extends RxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(getFragmentManager(), new MainFragment(), R.id.activity_base_content, Utils.MAIN_FRAGMENT);

    }

    private void changeFragment(FragmentManager fm, Fragment f, int contentID, String tag) {
        FragmentTransaction ft = fm.beginTransaction();
        clearBackStack(fm);
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
                    Log.d("dodol", "accept: go to tools list");
                }
            }
        }));
    }
}
