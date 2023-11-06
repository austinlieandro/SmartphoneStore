package com.example.ecommerceapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.HandphoneRepository
import com.example.ecommerceapp.model.Handphone
import com.example.ecommerceapp.model.OrderHandphone
import com.example.ecommerceapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: HandphoneRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderHandphone>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getHandhponeById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderHandphoneById(rewardId))
        }
    }

    fun addToCart(handphone: Handphone, count: Int) {
        viewModelScope.launch {
            repository.updateOrderHandphone(handphone.id, count)
        }
    }
}