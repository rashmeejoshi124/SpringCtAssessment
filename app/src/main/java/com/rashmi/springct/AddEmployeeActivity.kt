package com.rashmi.springct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rashmi.springct.databinding.ActivityAddEmployeeBinding
import com.rashmi.springct.databinding.ActivityMainBinding
import com.rashmi.springct.db.DBBuilder
import com.rashmi.springct.db.EmpDBHelper
import com.rashmi.springct.db.IEmpDBHelper
import com.rashmi.springct.model.Employee
import com.rashmi.springct.viewmodel.EmployeeViewModel
import com.rashmi.springct.viewmodel.LoginViewModel
import java.util.UUID

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEmployeeBinding
    private val vm by lazy {  ViewModelProvider(this)[EmployeeViewModel::class.java] }
    private val dbHelper: IEmpDBHelper by lazy { EmpDBHelper(DBBuilder.getInstance(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_employee)
        setClickListeners()
        supportActionBar?.title = "Add Employee"
    }

    private fun setClickListeners() {
        binding.addEmp.setOnClickListener {
            val emp = Employee(
                id = UUID.randomUUID().toString(),
                name = binding.empName.text.toString(),
                age = binding.empAge.text.toString().toIntOrNull() ?: 0,
                address = binding.empAddress.text.toString(),
                city = binding.empCity.text.toString()
            )
            EmployeeData.addEmployee(emp)
            vm.saveEmployeeDetails(emp, dbHelper)
            Toast.makeText(this, "Employee added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}