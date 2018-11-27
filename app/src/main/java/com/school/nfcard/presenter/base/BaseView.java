package com.school.nfcard.presenter.base;


public interface BaseView {

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
