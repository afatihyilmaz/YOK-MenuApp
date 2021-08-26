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
import java.lang.Exception
import java.util.*
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
        try {
            viewModel.getDataFromAPI()

            RVdateList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            progressBar.visibility = View.INVISIBLE

            val pos = viewModel.menuFeatures.value?.let { viewModel.showCurrentDay(it) }
            println("on view" + pos)

            observeLiveData(0, false)

        }catch (e : Exception){
                print(e)
        }


    }




    private fun observeLiveData(position: Int, isClicked: Boolean){
        viewModel.menuFeatures.observe(viewLifecycleOwner, { menus ->
            menus?.let {


                dList = viewModel.dateRecyclerList
                println(viewModel.dateRecyclerList.toString() + " ----------")
                dateAdapter = DateAdapter(dList, this)
                RVdateList.adapter = dateAdapter

                val currentDate = Calendar.getInstance().time.date
                var index: Int = 0
                if (!isClicked) {
                    for (i in menus) {
                        if(currentDate == i.dateDay.toInt()){
                            break
                        }
                        index++
                    }
                }
                else {
                    index = position
                }
                println(menus.toString())


                RVdateList.scrollToPosition(index)

                if (menus[index].foods?.size!! > 5) {
                    cardView21.visibility = View.VISIBLE
                    CV00textView.text = menus[index].foods?.get(0)?.name
                    CV01textView.text = menus[index].foods?.get(1)?.name
                    CV10textView.text = menus[index].foods?.get(2)?.name
                    CV11textView.text = menus[index].foods?.get(3)?.name
                    CV20textView.text = menus[index].foods?.get(4)?.name
                    CV21textView.text = menus[index].foods?.get(5)?.name

                    var url = ""
                    url = menus[index].foods?.get(0)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV00ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(1)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV01ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(2)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV10ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(3)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV11ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(4)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV20ImageView.downloadFromUrl(url,
                            it2
                        )
                    }

                    url = menus[index].foods?.get(5)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV21ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                }else{
                    CV00textView.text = menus[index].foods?.get(0)?.name
                    CV01textView.text = menus[index].foods?.get(1)?.name
                    CV10textView.text = menus[index].foods?.get(2)?.name
                    CV11textView.text = menus[index].foods?.get(3)?.name
                    CV20textView.text = menus[index].foods?.get(4)?.name
                    cardView21.visibility = View.INVISIBLE
                    var url = ""
                    url = menus[index].foods?.get(0)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV00ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(1)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV01ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(2)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV10ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(3)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV11ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                    url = menus[index].foods?.get(4)?.photoUrl!!
                    context?.let { it1 -> PlaceholderProgressBar(it1) }?.let { it2 ->
                        CV20ImageView.downloadFromUrl(url,
                            it2
                        )
                    }
                }



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
            observeLiveData(position, true)
    }
}