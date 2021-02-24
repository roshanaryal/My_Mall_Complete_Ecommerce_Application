package com.roshanaryal.mymall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roshanaryal.mymall.R
import com.roshanaryal.mymall.model.HorizontalScrollProductModel

class HorizontalProductScrollAdapter( List: MutableList<HorizontalScrollProductModel>) : RecyclerView.Adapter<HorizontalProductScrollAdapter.Viewholder>() {
      private var mutableModelList: MutableList<HorizontalScrollProductModel>


    init {
        mutableModelList=List
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.horizontal_item_scroll_layout,parent,false)

        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var m=mutableModelList[position]
        holder.setImage(m.productImage)
        holder.setTitle(m.productTitle)
        holder.setDescription(m.productDescription)
        holder.setPrice(m.productPrice)
    }

    override fun getItemCount(): Int {
       return mutableModelList.size
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var imageView:ImageView
        lateinit var title:TextView
        lateinit var description:TextView
        lateinit var price:TextView

        init {
            imageView=itemView.findViewById(R.id.hs_product_image)
            title=itemView.findViewById(R.id.hs_product_title)
            description=itemView.findViewById(R.id.hs_product_description)
            price=itemView.findViewById(R.id.hs_product_price)
        }

        fun setImage(int: Int)
        {
            imageView.setImageResource(int)
        }

        fun setTitle(text:String)
        {
            title.text=text
        }
        fun setDescription(text:String)
        {
            description.text=text
        }
        fun setPrice(text:String)
        {
            price.text=text
        }

    }
}