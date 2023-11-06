package com.example.ecommerceapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.HandphoneRepository
import com.example.ecommerceapp.model.OrderHandphone
import com.example.ecommerceapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HandphoneRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderHandphone>>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    val query: MutableStateFlow<String> = MutableStateFlow("")

    fun searchHandphone(newQuery: String) {
        viewModelScope.launch {
            query.value = newQuery
            repository.searchHandphone(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{orderHandpones ->
                    _uiState.value = UiState.Success(orderHandpones)
                }
        }
    }

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllHandphone()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderHandphones ->
                    _uiState.value = UiState.Success(orderHandphones)
                }
        }
    }
}