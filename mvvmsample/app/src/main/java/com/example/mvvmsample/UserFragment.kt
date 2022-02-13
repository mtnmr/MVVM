package com.example.mvvmsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.example.mvvmsample.databinding.FragmentUserBinding
import com.example.mvvmsample.viewmodel.UserViewModel


class UserFragment : Fragment() {

    private lateinit var binding :FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel:UserViewModel by activityViewModels()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.user_list)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.selectUserSpinner.apply{
            setAdapter(adapter)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    val spinner = parent as Spinner
                    val str = spinner.selectedItem.toString()
                    viewModel.onItemSelected(str)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }

    }

}