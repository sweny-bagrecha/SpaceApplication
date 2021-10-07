package com.sweny.suncorp.astronautapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.usecase.IAstronautsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AstronautDetailsViewModel @Inject constructor(private val astronautUseCase: IAstronautsUseCase) :
    BaseTripViewModel(){

    private val mAstronautsList = MutableLiveData<AstronautData>()
    val astronautDetails: LiveData<AstronautData>
        get() = mAstronautsList

    /**
     * get details of a selected astronaut
     *
     * @param id astronaut id
     */
    fun getAstronautDetails(id: String) = processUseCase {
            mAstronautsList.value = astronautUseCase.getAstronautDetails(id)
    }

}
