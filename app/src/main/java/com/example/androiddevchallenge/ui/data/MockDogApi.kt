package com.example.androiddevchallenge.ui.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileInputStream
import java.io.InputStreamReader

class MockDogApi {

    fun mockDogList(context: Context): List<DogBean> {
        val inputStream = context.assets.open("dogs.dat")
        val gson = Gson()
        return gson.fromJson(InputStreamReader(inputStream), Array<DogBean>::class.java).toMutableList()
    }
}