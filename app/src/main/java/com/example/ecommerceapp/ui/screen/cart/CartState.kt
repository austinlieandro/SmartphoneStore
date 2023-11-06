package com.example.ecommerceapp.ui.screen.cart

import com.example.ecommerceapp.model.OrderHandphone

data class CartState(
    val orderHandphone: List<OrderHandphone>,
    val price: Int
)