package com.oss.surftesttask_kotlinversion.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter {
    @TypeConverter
    fun fromResultsList(results: List<Int>): String? {
        if (results == null) return null
        else {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Int>>() {}.type
            return gson.toJson(results, type)
        }
    }

    @TypeConverter
    fun toResultsList(results: String): List<Int>? {
        if (results == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson<List<Int>>(results, type)
    }
}