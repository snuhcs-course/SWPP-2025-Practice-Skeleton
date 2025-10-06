package com.example.snapdo.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.snapdo.data.model.Todo
import com.example.snapdo.data.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "Fetching todos from repository...")
                // TODO - 2: Replace mocking with actual fetching

                // Mock data (Replace this with actual fetching)
                val result = listOf(
                    Todo(
                        id = 1,
                        title = "SWPP Scheduling",
                        description = "Summarize the team's progress and next steps",
                        target_evidence = "Photo of schedule sheet",
                        is_done = false,
                        verdict = null
                    )
                )
                Log.d("MainViewModel", "Fetched ${result.size} todos")
                _todos.value = result
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error: ${e.message}", e)
            }
        }
    }

    fun refreshTodos() {
        fetchTodos() // just call again
    }

    fun updateVerdict(taskId: Int, verdict: String) {
        val updatedList = _todos.value?.map { todo ->
            if (todo.id == taskId) todo.copy(verdict = verdict)
            else todo
        }
        _todos.postValue(updatedList)
    }

}

class MainViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
