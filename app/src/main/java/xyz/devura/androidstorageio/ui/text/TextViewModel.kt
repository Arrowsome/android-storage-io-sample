package xyz.devura.androidstorageio.ui.text

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.devura.androidstorageio.repository.FileRepository

class TextViewModel : ViewModel() {

    companion object {
        private val TAG = TextViewModel::class.java.simpleName
    }

    val toastViewModel = MutableLiveData<String>()
    val clearFormLiveData = MutableLiveData<Boolean>()
    val contentLiveData = MutableLiveData<String>()

    fun onSaveTextFile(content: String, fileName: String) = viewModelScope.launch {
        if (content.isBlank()) {
            toastViewModel.postValue("Fill the content input before saving!")
            cancel()
        }
        val isFileSaved: Boolean = withContext(Dispatchers.IO) {
            FileRepository.saveTextFile(content.trim(), fileName)
        }
        clearFormLiveData.postValue(isFileSaved)
        toastViewModel.postValue(if (isFileSaved) "File saved!" else "Failed to save file!")
    }

    fun onReadTextFile(fileName: String) = viewModelScope.launch {
        val text: String? = withContext(Dispatchers.IO) {
            FileRepository.readTextFile(fileName)
        }
        if (text == null) {
            clearFormLiveData.postValue(false)
        } else {
            contentLiveData.postValue(text)
        }
    }

}
