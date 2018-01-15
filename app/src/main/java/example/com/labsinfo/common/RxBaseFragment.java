package example.com.labsinfo.common;

import android.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

public class RxBaseFragment extends Fragment {

    CompositeDisposable subscription;

    @Override
    public void onResume() {
        super.onResume();
        subscription = new CompositeDisposable();
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.clear();
    }
}
