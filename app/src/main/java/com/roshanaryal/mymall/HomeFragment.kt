package com.roshanaryal.mymall

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.roshanaryal.mymall.adapter.CategoryAdapter
import com.roshanaryal.mymall.adapter.HorizontalProductScrollAdapter
import com.roshanaryal.mymall.adapter.SliderPagerAdapter
import com.roshanaryal.mymall.model.CategoryModal
import com.roshanaryal.mymall.model.HorizontalScrollProductModel
import com.roshanaryal.mymall.model.SliderModal
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.horizontal_scroll_layout.*
import java.util.*


class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private  lateinit var list: MutableList<CategoryModal>
    private lateinit var categoryAdapter:CategoryAdapter


    //sliderpage
    private lateinit var viewPager2: ViewPager
    private lateinit var sliderIconList: MutableList<SliderModal>
    private lateinit var sliderPagerAdapter: SliderPagerAdapter
    private var currentItem:Int=2
    private lateinit var timer: Timer
    private final val DELAYTIME : Long=3000
    private final val PERIODTIME : Long=3000
    //sliderpage end

    //stripead
    private lateinit var stripeImageView: ImageView
    private lateinit var stripeContainerConstraintLayout: ConstraintLayout
    //stripe ad end

    //horizontal scrollview start
    private lateinit var mutableListofHorizontalProduct: MutableList<HorizontalScrollProductModel>
    private lateinit var horizontalProductScrollAdapter: HorizontalProductScrollAdapter
    private lateinit var horizontalRecyclerView: RecyclerView
    //horizotalscrollview end

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

        //Sliderpage
        viewPager2=view.findViewById(R.id.main_slider_viewpager)
        sliderIconList= mutableListOf()

        sliderIconList.add(SliderModal(R.drawable.banner4))
        sliderIconList.add(SliderModal(R.drawable.profile_placeholder))

        sliderIconList.add(SliderModal(R.drawable.slider_image_1))
        sliderIconList.add(SliderModal(R.drawable.banner1))
        sliderIconList.add(SliderModal(R.drawable.banner2))
        sliderIconList.add(SliderModal(R.drawable.banner3))
        sliderIconList.add(SliderModal(R.drawable.banner4))
        sliderIconList.add(SliderModal(R.drawable.profile_placeholder))

        sliderIconList.add(SliderModal(R.drawable.slider_image_1))
        sliderIconList.add(SliderModal(R.drawable.banner1))

        sliderPagerAdapter=SliderPagerAdapter(sliderIconList)

        viewPager2.clipToPadding=false
        viewPager2.adapter=sliderPagerAdapter

        // e=new EditText()
        val onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // TODO("Not yet implemented")
            }

            override fun onPageSelected(position: Int) {
                currentItem=position
            }

            override fun onPageScrollStateChanged(state: Int) {

                if (state== ViewPager2.SCROLL_STATE_IDLE)
                {
                    pageLooper()
                }
            }
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                currentItem=position
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//
//                super.onPageScrollStateChanged(state)
//                if (state==ViewPager2.SCROLL_STATE_IDLE)
//                {
//                    pageLooper()
//                }
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//            }
        }
        viewPager2.addOnPageChangeListener(onPageChangeListener)
        startBanenrSlideShow()

        viewPager2.setOnTouchListener { v, event ->
            pageLooper()
            stopBanenrSlideShow()
            if (event.action== MotionEvent.ACTION_UP)
            {
                startBanenrSlideShow()
            }


            false
        }

        //sliderpage end



        //stripead
        stripeImageView=view.findViewById(R.id.stripe_ad_imageview)
        stripeContainerConstraintLayout=view.findViewById(R.id.stripe_ad_container)
        stripeImageView.setImageResource(R.drawable.stripe_ad)
        stripeContainerConstraintLayout.setBackgroundColor(Color.parseColor("#000000"))
        //stripe ad end

        //horizontal scrollview start
       horizontalRecyclerView=view.findViewById(R.id.horizontal_scroll_recyclerview)
        var layoutManagerHS= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        horizontalRecyclerView.layoutManager=layoutManagerHS
        mutableListofHorizontalProduct= mutableListOf()
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))
        mutableListofHorizontalProduct.add(HorizontalScrollProductModel(R.drawable.phone_image,"Redmi 5S","SD 25 gb ran","From Rs 25000"))

        horizontalProductScrollAdapter= HorizontalProductScrollAdapter(mutableListofHorizontalProduct)
        horizontalRecyclerView.adapter=horizontalProductScrollAdapter
        //horizotalscrollview end

        return view
    }

    //Sliderpage start
    private fun pageLooper(){
        if (currentItem==sliderIconList.size-2)
        {
            currentItem=2
            viewPager2.setCurrentItem(currentItem, false)
        }
        if (currentItem==1)
        {
            currentItem=sliderIconList.size-3
            viewPager2.setCurrentItem(currentItem, false)

        }

    }
    private fun startBanenrSlideShow()
    {
        var handler= Handler()
        var runnable= object :Runnable{
            override fun run() {
                if (currentItem>=sliderIconList.size)
                {
                    currentItem=1
                }
                viewPager2.setCurrentItem(currentItem++, true)
            }
        }
        timer= Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, DELAYTIME, PERIODTIME)
    }
    private fun stopBanenrSlideShow()
    {
        timer.cancel()
    }

    //Sliderpage end

}