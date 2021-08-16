package com.example.deneme3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deneme3.R
import com.example.deneme3.adapter.DateAdapter
import com.example.deneme3.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.fragment_main_page.*
import retrofit2.http.POST
import java.util.*
import kotlin.collections.ArrayList

class MainPageFragment : Fragment() {
    private lateinit var viewModel : MenuViewModel
    private lateinit var dList : ArrayList<String>

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

        RVdateList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        progressBar.visibility = View.INVISIBLE

            observeLiveData()


    }



    private fun observeLiveData(){
        viewModel.menuFeatures.observe(viewLifecycleOwner, { menus ->
            menus?.let {
                textView22.text = menus?.toString()

                dList = viewModel.dateRecyclerList
                println(viewModel.dateRecyclerList.toString())
                val dateAdapter = DateAdapter(dList)
                RVdateList.adapter = dateAdapter
              /*  dateAdapter.setOnItemClickListener(object : DateAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        Toast.makeText(context, "Clicked on $position", Toast.LENGTH_SHORT).show()
                    }

                })*/



            }
        })

        viewModel.menuError.observe(viewLifecycleOwner, { error ->
            error?.let {
                if(it) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "No error", Toast.LENGTH_SHORT).show()
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