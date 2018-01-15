package example.com.labsinfo.common;

import android.app.Activity;

import io.reactivex.disposables.CompositeDisposable;

public class RxBaseActivity extends Activity {

    protected CompositeDisposable subscription;

    @Override
    protected void onResume() {
        super.onResume();
        subscription = new CompositeDisposable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscription.clear();
    }
}
