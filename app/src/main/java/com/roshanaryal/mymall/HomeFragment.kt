package com.roshanaryal.mymall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roshanaryal.mymall.adapter.CategoryAdapter
import com.roshanaryal.mymall.model.CategoryModal
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private  lateinit var list: MutableList<CategoryModal>
    private lateinit var categoryAdapter:CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView=view.findViewById(R.id.category_recyclerview)

        list= mutableListOf()
        list.add(CategoryModal("link","Home"))
        list.add(CategoryModal("link","Electronics"))
        list.add(CategoryModal("link","Applience"))
        list.add(CategoryModal("link","Furniture"))
        list.add(CategoryModal("link","Stationary"))
        list.add(CategoryModal("link","Books"))
        list.add(CategoryModal("link","Toy"))
        list.add(CategoryModal("link","Sports"))
        list.add(CategoryModal("link","arts"))
        list.add(CategoryModal("link","Fashion"))
        list.add(CategoryModal("link","Shoe"))

        val layoutManager=LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager=layoutManager

        categoryAdapter= CategoryAdapter(list)
        recyclerView.adapter=categoryAdapter


        return view
    }

}