package example.com.labsinfo.common;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;

public class RxBus {

    private static final RxBus INSTANCE = new RxBus();

    private final Relay<Object> mBus = PublishRelay.create();

    public void send(Object event){
        mBus.accept(event);
    }

    public boolean hasObservable(){
        return mBus.hasObservers();
    }

    public Observable toObservable(){
        return mBus;
    }

    public static RxBus getInstance(){
        return INSTANCE;
    }


}
