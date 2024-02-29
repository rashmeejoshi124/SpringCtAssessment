package com.rashmi.springct.db

import com.rashmi.springct.model.Employee
import kotlinx.coroutines.flow.Flow

interface IEmpDBHelper {

    fun getEmployees(): Flow<List<Employee>>

    fun insert(employee: Employee): Flow<Unit>
}