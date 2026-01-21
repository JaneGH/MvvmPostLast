package com.example.mvvmpostlast.data.datasourse


import com.example.mvvmpostlast.data.local.AssetJsonReader
import com.example.mvvmpostlast.data.remote.dto.cmspages.CmsPageDto
import com.google.gson.Gson
import javax.inject.Inject

class CmsLocalDataSource @Inject constructor(
    private val jsonReader: AssetJsonReader,
    private val gson: Gson = Gson()
) {
    fun loadPageFromAssets(fileName: String): CmsPageDto {
        val json = jsonReader.read(fileName)
        return gson.fromJson(json, CmsPageDto::class.java)
    }
}