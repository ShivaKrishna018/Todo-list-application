package com.kronos.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kronos.Model.Entity
import com.kronos.Repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private var repository: NoteRepository): ViewModel() {

    val myAllNotes: LiveData<List<Entity>> = repository.myAllNotes.asLiveData()

    fun insert(note: Entity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

    fun update(note: Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun delete(note: Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllNotes()
    }

}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(repository) as T
        }else{
            throw IllegalArgumentException("unknown View Model")
        }
    }

}