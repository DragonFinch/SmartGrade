package com.school.nfcard.presenter.http;

import rx.Subscriber;


public abstract class CallBack<T> extends Subscriber<T> {

    public abstract void successful(T body);

    public abstract void beginStart();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onStart() {
        super.onStart();
        beginStart();
    }


    @Override
    public void onNext(T body) {
        successful(body);
    }
}
