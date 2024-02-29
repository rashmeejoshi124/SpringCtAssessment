package com.rashmi.springct.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rashmi.springct.db.IEmpDBHelper
import com.rashmi.springct.model.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class EmployeeViewModel() : ViewModel() {

    fun fetchEmployeeDetails(dbHelper: IEmpDBHelper): Flow<List<Employee>> {
        return dbHelper.getEmployees().flowOn(Dispatchers.IO).catch { e ->
            Log.e(TAG, "fetchEmployeeDetails: $e")
        }

    }

    fun saveEmployeeDetails(emp: Employee, dbHelper: IEmpDBHelper) {
        CoroutineScope(Dispatchers.IO).launch {
            dbHelper.insert(emp).flowOn(Dispatchers.IO).catch { e ->
                Log.e(TAG, "saveEmployeeDetails: $e")
            }.collect {
                Log.d(TAG, "saveEmployeeDetails: success")
            }
        }
    }

    companion object {
        const val TAG = "EmployeeViewModel"
    }

}