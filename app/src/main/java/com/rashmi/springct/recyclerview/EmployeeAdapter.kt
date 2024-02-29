package com.rashmi.springct.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rashmi.springct.databinding.ItemEmployeeBinding
import com.rashmi.springct.model.Employee

class EmployeeAdapter : Adapter<EmployeeViewHolder>() {

    private val empList = mutableListOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(empList[position])
    }

    fun setData(employeeList: List<Employee>) {
        empList.clear()
        empList.addAll(employeeList)
        notifyDataSetChanged()
    }
}


class EmployeeViewHolder(val binding: ItemEmployeeBinding) : ViewHolder(binding.root) {

    fun bind(employee: Employee) {
        binding.name.text = "Name: ${employee.name}"
        binding.age.text = "Age: ${employee.age}"
        binding.address.text = "Address: ${employee.address}"
        binding.city.text = "City: ${employee.city}"
    }

}