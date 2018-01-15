package example.com.labsinfo.common;

import android.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class BaseFragment extends Fragment {

    Unbinder unbinder;


    protected void bind(Object target, View view){
        unbinder = ButterKnife.bind(target, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
