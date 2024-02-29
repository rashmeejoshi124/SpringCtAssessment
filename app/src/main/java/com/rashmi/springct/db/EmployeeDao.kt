package com.rashmi.springct.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rashmi.springct.model.Employee

@Dao
interface EmployeeDao {


    @Query("SELECT * FROM employee")
    fun getAllEmployees(): List<Employee>

    @Insert
    fun addEmployee(employee: Employee)
}
