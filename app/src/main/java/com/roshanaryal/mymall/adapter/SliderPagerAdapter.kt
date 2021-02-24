package com.roshanaryal.mymall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.roshanaryal.mymall.R
import com.roshanaryal.mymall.model.SliderModal

class SliderPagerAdapter(modelMutableLists:MutableList<SliderModal>) : PagerAdapter()
{
    private var modelMutableList:MutableList<SliderModal> = mutableListOf()
    init {
        modelMutableList=modelMutableLists
    }


    override fun getCount(): Int {
        return modelMutableList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view=LayoutInflater.from(container.context).inflate(R.layout.slider_layout,container,false)
        val imageView:ImageView=view.findViewById(R.id.image_slider_view)
        imageView.setImageResource(modelMutableList[position].imageIcon)
        container.addView(view,0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}