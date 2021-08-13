package com.example.deneme3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deneme3.R
import com.example.deneme3.adapter.DateAdapter
import com.example.deneme3.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.fragment_main_page.*

class MainPageFragment : Fragment() {
    private lateinit var viewModel : MenuViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        viewModel.getDataFromAPI()

        var dList : ArrayList<String> = ArrayList()
        dList.add("04")
        dList.add("05")
        dList.add("06")
        dList.add("09")
        dList.add("10")
        RVdateList.layoutManager = LinearLayoutManager(context)
    //    val adapterList : ArrayList<String>? = viewModel.dateList.value
        val dateAdapter = DateAdapter(dList)
        println(dList)
        RVdateList.adapter = dateAdapter

        progressBar.visibility = View.INVISIBLE


            observeLiveData()


    }



    private fun observeLiveData(){
        viewModel.menuFeatures.observe(viewLifecycleOwner, { menus ->
            menus?.let {
                textView22.text = menus?.toString()

            }
        })

        viewModel.menuError.observe(viewLifecycleOwner, { error ->
            error?.let {
                if(it) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(context, "No error", Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.menuLoading.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                if(it){
                    progressBar.visibility = View.VISIBLE

                }else{
                    progressBar.visibility = View.GONE
                }
            }
        })

    }
}