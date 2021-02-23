package com.roshanaryal.mymall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roshanaryal.mymall.R
import com.roshanaryal.mymall.model.CategoryModal

class CategoryAdapter(_list: MutableList<CategoryModal>): RecyclerView.Adapter<CategoryAdapter.Viewholder>() {


    private var list : MutableList<CategoryModal> = mutableListOf()
    private lateinit var context:Context

    init {
        list=_list
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context=parent.context
       var view=LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        return Viewholder(view)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val icon=list[position].categoryIconLink
        val name=list[position].categoryName
        holder.setCategoryName(name)
    }

    override fun getItemCount(): Int {
        return list.size
    }


  class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private lateinit var categoryImage: ImageView
        private lateinit var categoryText: TextView

        init {
            categoryImage=itemView.findViewById(R.id.category_icon)
            categoryText=itemView.findViewById(R.id.category_name)
        }
         fun setCategoryIcon()
        {

        }
         fun setCategoryName(name:String)
        {
                categoryText.text=name
        }

    }
}