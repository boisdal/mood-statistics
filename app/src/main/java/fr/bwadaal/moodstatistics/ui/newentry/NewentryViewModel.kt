package fr.bwadaal.moodstatistics.ui.newentry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewentryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is newentry Fragment"
    }
    val text: LiveData<String> = _text
}