package com.example.memoapp

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.File

class DataManager {

    // 書き込み処理
    public fun saveFile(applicationContext: Context,file: String, str: String) {
        applicationContext.openFileOutput(file, Context.MODE_APPEND).use {
            it.write(str.toByteArray())
        }
    }

    // 読み出し処理
    public fun readFile(applicationContext: Context,file: String): BufferedReader {
        val readFile = File(applicationContext.filesDir, file)

        //  ファイルが存在するか確認
        if(!readFile.exists()){
            readFile.writeText("")
        }

        return readFile.bufferedReader()
    }

    //  消去処理
    public  fun deleteFile(applicationContext: Context,file: String)
    {
        val readFile = File(applicationContext.filesDir, file)
        readFile.delete()
    }
}