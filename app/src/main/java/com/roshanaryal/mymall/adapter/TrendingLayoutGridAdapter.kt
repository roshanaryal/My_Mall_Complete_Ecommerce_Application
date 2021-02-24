package com.roshanaryal.mymall.adapter

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.roshanaryal.mymall.R
import com.roshanaryal.mymall.model.HorizontalScrollProductModel

class TrendingLayoutGridAdapter (List:MutableList<HorizontalScrollProductModel>,c: Context): BaseAdapter() {

     var mutableList:MutableList<HorizontalScrollProductModel>
     var context:Context
    init {
        mutableList=List
        context=c
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View
        if (convertView==null)
        {

               view= LayoutInflater.from(context).inflate(R.layout.horizontal_item_scroll_layout,parent,false)
                 view.elevation= 0F
                view.setBackgroundColor(Color.parseColor("#ffffff"))
                view.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                var imageView:ImageView=view.findViewById(R.id.hs_product_image)
                var title:TextView=view.findViewById(R.id.hs_product_title)
                val description:TextView=view.findViewById(R.id.hs_product_description)
                var  price:TextView=view.findViewById(R.id.hs_product_price)



                var h=mutableList[position]
                imageView.setImageResource(h.productImage)
                title.setText(h.productTitle)
                description.setText(h.productDescription)
                price.setText(h.productPrice)



        }
        else
        {
            view=convertView

        }

        return view
    }
}