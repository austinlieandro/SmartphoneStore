package com.example.ecommerceapp.data

import com.example.ecommerceapp.model.FakeHandphoneDataSource.dummyHandphone
import com.example.ecommerceapp.model.OrderHandphone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HandphoneRepository {

    private val orderHandphones = mutableListOf<OrderHandphone>()

    init {
        if (orderHandphones.isEmpty()){
            dummyHandphone.forEach{
                orderHandphones.add(OrderHandphone(it, 0))
            }
        }
    }

    fun getAllHandphone():Flow<List<OrderHandphone>>{
        return flowOf(orderHandphones)
    }

    fun getOrderHandphoneById(handphoneId: Long): OrderHandphone {
        return orderHandphones.first{
            it.handphone.id == handphoneId
        }
    }

    fun updateOrderHandphone(handphoneId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderHandphones.indexOfFirst { it.handphone.id == handphoneId }
        val result = if(index >= 0){
            val orderHandphone = orderHandphones[index]
            orderHandphones[index] = orderHandphone.copy(handphone = orderHandphone.handphone, count = newCountValue)
            true
        }else{
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderHandphone(): Flow<List<OrderHandphone>>{
        return getAllHandphone().map {  orderHandphones ->
            orderHandphones.filter { orderHanphone ->
                orderHanphone.count != 0
            }
        }
    }

    fun searchHandphone(query: String): Flow<List<OrderHandphone>> {
        return flow {
            emit(orderHandphones.filter { it.handphone.name.contains(query, ignoreCase = true) })
        }
    }

    companion object {
        @Volatile
        private var instance: HandphoneRepository? = null

        fun getInstance(): HandphoneRepository =
            instance ?: synchronized(this) {
                HandphoneRepository().apply {
                    instance = this
                }
            }
    }
}