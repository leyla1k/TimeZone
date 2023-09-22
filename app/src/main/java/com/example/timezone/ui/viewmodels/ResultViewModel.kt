package com.example.timezone.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timezone.data.ResultRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.timezone.data.Result

import com.example.timezone.data.fromResultToResultDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultViewModel(private val projectsRepository: ResultRepository) : ViewModel() {

    private val _resultListFlow = MutableStateFlow<MutableList<Result>>(arrayListOf())
    val resultListFlow: StateFlow<MutableList<Result>> = _resultListFlow.asStateFlow()

    init {
        viewModelScope.launch {
            projectsRepository.getAllResultFlow().collect { uit ->
                _resultListFlow.update {
                    mutableListOf<Result>().apply {
                        addAll(uit)
                    }
                }
            }
        }
    }

    suspend fun insertNewResult(result: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            projectsRepository.insertNewResult(fromResultToResultDataItem(result))
        }
    }


    suspend fun deleteResult(result: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            projectsRepository.deleteResult(fromResultToResultDataItem(result))
        }
    }


}