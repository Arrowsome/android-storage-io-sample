package xyz.devura.androidstorageio.ui.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.devura.androidstorageio.repository.FileRepository

class ImageViewModel : ViewModel() {

    val toastViewModel = MutableLiveData<String>()

    fun onSaveImageFile(source: Int, filename: String) = viewModelScope.launch {
        val isFileSaved = withContext(Dispatchers.IO) {
            FileRepository.saveImageFile(source, filename)
        }
        toastViewModel.postValue(if (isFileSaved) "Image file saved!" else "Failed to save image file!")

    }

}