package com.repository

import com.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    //getAllLives recebendo o retrofitService (INTERFACE)
    fun getAllLives() = retrofitService.getAllLives()

}