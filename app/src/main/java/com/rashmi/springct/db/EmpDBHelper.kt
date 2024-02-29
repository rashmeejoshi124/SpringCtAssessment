package com.rashmi.springct.db

import com.rashmi.springct.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EmpDBHelper(private val appDB: AppDataBase) : IEmpDBHelper {

    override fun getEmployees(): Flow<List<Employee>> = flow {
        emit(appDB.empDao().getAllEmployees())
    }

    override fun insert(employee: Employee): Flow<Unit> = flow {
        appDB.empDao().addEmployee(employee)
        emit(Unit)
    }
}