package com.example.deneme3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deneme3.R
import com.example.deneme3.adapter.DateAdapter
import com.example.deneme3.model.RecyclerDateModel
import com.example.deneme3.util.PlaceholderProgressBar
import com.example.deneme3.util.downloadFromUrl
import com.example.deneme3.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlin.collections.ArrayList

class MainPageFragment : Fragment(), DateAdapter.OnItemClickListener {
    private lateinit var viewModel : MenuViewModel
    private lateinit var dList : ArrayList<RecyclerDateModel>
    private lateinit var dateAdapter : DateAdapter

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

        observeLiveData(0)
    }



    private fun observeLiveData(position: Int){
        viewModel.menuFeatures.observe(viewLifecycleOwner, { menus ->
            menus?.let {


                dList = viewModel.dateRecyclerList
                println(viewModel.dateRecyclerList.toString() + " ----------")
                dateAdapter = DateAdapter(dList, this, menus)
                RVdateList.adapter = dateAdapter

                println(menus.toString())



                CV00textView.text = menus[position].foods?.get(0)?.name
                CV01textView.text = menus[position].foods?.get(1)?.name
                CV10textView.text = menus[position].foods?.get(2)?.name
                CV11textView.text = menus[position].foods?.get(3)?.name
                CV20textView.text = menus[position].foods?.get(4)?.name

                if (menus[position].foods?.size!! > 5) {
                    CV21textView.text = menus[position].foods?.get(5)?.name
                }

                var url = ""
                url = menus[0].foods?.get(0)?.photoUrl!!
                context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                    CV00ImageView.downloadFromUrl(url,
                        it2
                    )
                }
                url = menus[0].foods?.get(1)?.photoUrl!!
                context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                    CV01ImageView.downloadFromUrl(url,
                        it2
                    )
                }
                url = menus[0].foods?.get(2)?.photoUrl!!
                context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                    CV10ImageView.downloadFromUrl(url,
                        it2
                    )
                }
                url = menus[0].foods?.get(3)?.photoUrl!!
                context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                    CV11ImageView.downloadFromUrl(url,
                        it2
                    )
                }
                 url = menus[0].foods?.get(4)?.photoUrl!!
                 context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                     CV20ImageView.downloadFromUrl(url,
                         it2
                     )
                 }
                 url = menus[0].foods?.get(5)?.photoUrl!!
                 context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                     CV21ImageView.downloadFromUrl(url,
                         it2
                     )
                 }


                /*   dateAdapter.setOnItemClickListener(object : DateAdapter.onItemClickListener{
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

    override fun onItemClick(position: Int) {
            observeLiveData(position)
    }
}