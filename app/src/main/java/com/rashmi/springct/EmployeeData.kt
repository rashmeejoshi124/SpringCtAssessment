package com.rashmi.springct

import com.rashmi.springct.model.Employee

object EmployeeData {

    private val employeeList = mutableListOf<Employee>()

    fun addEmployee(emp: Employee) {
        employeeList.add(emp)
    }

    fun getList(): List<Employee> = employeeList

    fun clearAll() {
        employeeList.clear()
    }
}