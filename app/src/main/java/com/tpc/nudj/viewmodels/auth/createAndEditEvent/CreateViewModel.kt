package com.tpc.nudj.viewmodels.auth.createAndEditEvent

import androidx.lifecycle.ViewModel
import com.tpc.nudj.model.enums.FormType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor() : ViewModel() {

    private val _selectedForm = MutableStateFlow(FormType.CLUB)
    val selectedForm = _selectedForm.asStateFlow()

    fun onFormSelected(formType: FormType) {
        _selectedForm.value = formType
    }
}