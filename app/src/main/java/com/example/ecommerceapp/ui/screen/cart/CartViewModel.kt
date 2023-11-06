package com.example.ecommerceapp.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.HandphoneRepository
import com.example.ecommerceapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: HandphoneRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getAddedOrderHandphone() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderHandphone()
                .collect { orderHandphone ->
                    val totalPrice = orderHandphone.sumOf { it.handphone.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderHandphone, totalPrice))
                }
        }
    }

    fun updateOrderReward(handphoneId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderHandphone(handphoneId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderHandphone()
                    }
                }
        }
    }
}