package com.rashmi.springct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rashmi.springct.databinding.ActivityMainBinding
import com.rashmi.springct.db.DBBuilder
import com.rashmi.springct.db.EmpDBHelper
import com.rashmi.springct.db.IEmpDBHelper
import com.rashmi.springct.model.Employee
import com.rashmi.springct.recyclerview.EmployeeAdapter
import com.rashmi.springct.viewmodel.EmployeeViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = EmployeeAdapter()
    private val vm by lazy { ViewModelProvider(this)[EmployeeViewModel::class.java] }
    private val dbHelper: IEmpDBHelper by lazy { EmpDBHelper(DBBuilder.getInstance(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setClickListeners()
        fetchEmpDetails()
        supportActionBar?.title = "Employee Details"
    }

    private fun fetchEmpDetails() {
        lifecycleScope.launchWhenStarted {
            vm.fetchEmployeeDetails(dbHelper).collectLatest {
                Log.d(TAG, "fetchEmpDetails: $it")
                setAdapter(it)
            }
        }

    }

    private fun setAdapter(list: List<Employee>) {
        binding.rvEmp.adapter = adapter
        if (list.isNotEmpty()) {
            adapter.setData(list)
            binding.noEmpUi.visibility = View.GONE
            binding.rvEmp.visibility = View.VISIBLE
        } else
            showNoEmployeeUI()
    }

    private fun showNoEmployeeUI() {
        binding.noEmpUi.visibility = View.VISIBLE
        binding.rvEmp.visibility = View.GONE
    }

    private fun setClickListeners() {
        binding.btnAddEmp.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(intent)
            Log.d(TAG, "setClickListeners: add new emp")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        fetchEmpDetails()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}