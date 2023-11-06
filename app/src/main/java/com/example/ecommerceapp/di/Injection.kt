package com.example.ecommerceapp.di

import com.example.ecommerceapp.data.HandphoneRepository

object Injection {
    fun provideRepository(): HandphoneRepository{
        return HandphoneRepository.getInstance()
    }
}