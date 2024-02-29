package com.rashmi.springct.db

import android.content.Context
import androidx.room.Room

object DBBuilder {

    private var INSTANCE: AppDataBase? = null

    fun getInstance(context: Context): AppDataBase {
        if (INSTANCE == null) {
            synchronized(AppDataBase::class) {
                if (INSTANCE == null)
                    INSTANCE = buildDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildDB(context: Context) = Room.databaseBuilder(
        context, AppDataBase::class.java, "my-app-db"
    ).build()
}