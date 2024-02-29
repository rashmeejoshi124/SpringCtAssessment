package com.rashmi.springct.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rashmi.springct.model.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun empDao(): EmployeeDao

}