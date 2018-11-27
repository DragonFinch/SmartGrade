package com.school.nfcard.presenter.base;



public interface Presenter<V> {
    void attachView(V mvpView);

    void detachView();
}
