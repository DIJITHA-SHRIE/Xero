package com.xero.airlineindustry.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TripsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TripsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Trips  fragment Coming soon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}