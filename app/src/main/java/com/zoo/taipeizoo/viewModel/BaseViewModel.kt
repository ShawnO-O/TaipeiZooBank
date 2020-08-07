package com.zoo.taipeizoo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel:ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    var isLoading = MutableLiveData<Boolean>()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}