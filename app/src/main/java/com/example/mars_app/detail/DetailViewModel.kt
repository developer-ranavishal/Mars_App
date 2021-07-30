package com.example.mars_app.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mars_app.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty,application: Application) : AndroidViewModel(application) {

    private var _selectedProperty= MutableLiveData<MarsProperty>()
    val selectedProperty:LiveData<MarsProperty>
    get()=_selectedProperty

    init {
        _selectedProperty.value=marsProperty
    }
}