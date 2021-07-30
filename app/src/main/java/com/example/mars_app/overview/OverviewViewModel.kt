package com.example.mars_app.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mars_app.network.MarsApiService
import com.example.mars_app.network.MarsProperty
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

enum class MarsApiStatus{LOADING,ERROR,DONE}
class OverviewViewModel : ViewModel() {

    private val _status: MutableLiveData<MarsApiStatus> = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus> 
        get() = _status
    private val  _properties= MutableLiveData<ArrayList<MarsProperty>>()
    val properties: MutableLiveData<ArrayList<MarsProperty>>
        get() =_properties

    private val _navigateToSelectedProperty= MutableLiveData<MarsProperty?>()
     val navigateToSelectedProperty: MutableLiveData<MarsProperty?>
     get() = _navigateToSelectedProperty

    init {
        getMarsRealEstateProperties()
    }

    // using the retrofit with coroutine in viewModel scope
    private var viewModelJob: Job = Job()
    private fun getMarsRealEstateProperties() {
        viewModelJob = viewModelScope.launch{
            var getPropertiesResponse: Response<ArrayList<MarsProperty>> =
                MarsApiService.MarsApi.retrofitService.getPropertiesAsync()
            try {
                _status.value=MarsApiStatus.LOADING
                var listResult: ArrayList<MarsProperty>? = getPropertiesResponse.body()
                _status.value=MarsApiStatus.DONE
                _properties.value = listResult!!
            } catch (t: Throwable) {
              _status.value= MarsApiStatus.ERROR
                _properties.value= ArrayList()    // set the marsProperties list as a empty list when error occur at response the data!!
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(marsProperty: MarsProperty){
        _navigateToSelectedProperty.value=marsProperty
    }
    fun displayPropertyDetailsCompleted(marsProperty: MarsProperty){
        _navigateToSelectedProperty.value=null
    }

}




