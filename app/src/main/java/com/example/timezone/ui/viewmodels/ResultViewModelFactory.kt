package com.example.timezone.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timezone.data.ResultRepository


class ResultViewModelFactory( private
                                val resultRepository: ResultRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return ResultViewModel(resultRepository) as T
    }
}