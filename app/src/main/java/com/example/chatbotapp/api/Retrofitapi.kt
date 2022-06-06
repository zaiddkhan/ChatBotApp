package com.example.chatbotapp.api

import com.example.chatbotapp.modals.MsgModal
import retrofit2.Response
import retrofit2.http.GET

interface Retrofitapi {

    @GET("get?bid=167025&key=R6vi55Yt1h6F9bau&uid=[uid]&msg=[msg]")
    fun getMessage(url:String):Response<MsgModal>
}