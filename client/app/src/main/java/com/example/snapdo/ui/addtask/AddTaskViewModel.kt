package com.example.snapdo.ui.addtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snapdo.data.repository.TodoRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _evidence = MutableLiveData<String>()
    val evidence: LiveData<String> = _evidence

    fun generateEvidence(title: String, description: String?) {
        viewModelScope.launch {
            try {
                val result = repository.generateEvidence(title, description)
                _evidence.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class AddTaskViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddTaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
