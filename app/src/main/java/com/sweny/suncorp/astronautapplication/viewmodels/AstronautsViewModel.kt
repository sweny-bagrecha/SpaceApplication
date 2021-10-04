package com.sweny.suncorp.astronautapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.usecase.IAstronautsUseCase
import com.sweny.suncorp.astronautapplication.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AstronautsViewModel @Inject constructor(private val astronautUseCase: IAstronautsUseCase) :
    BaseTripViewModel(), SelectedData{

    private val mAstronautsList = MutableLiveData<List<AstronautData>>()
    val astronautsList: LiveData<List<AstronautData>>
        get() = mAstronautsList

    val eventTripSelected = SingleLiveEvent<String>()

    fun refreshTrips() = processUseCase {
        mAstronautsList.value = astronautUseCase.getAstronauts()
    }

    override fun onSelectData(id: String) {
        eventTripSelected.value = id
    }

}

interface SelectedData {
    fun onSelectData(id: String)
}
