package com.roshanaryal.mymall.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.roshanaryal.mymall.R
import com.roshanaryal.mymall.model.HomePageModel
import com.roshanaryal.mymall.model.SliderModal
import java.util.*

class HomePageAdapter (list:MutableList<HomePageModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        val BANNER_SLIDER_TYPE=0
        val STRIPE_AD_BANNER=1

    }

    var homePageModalList:MutableList<HomePageModel>
    init {
        homePageModalList=list
    }









    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType)
        {
            BANNER_SLIDER_TYPE->{
                var view:View=LayoutInflater.from(parent.context).inflate(R.layout.sliding_ad_layout,parent,false)
                return BannerSliderViewHoolder(view)
            }
            STRIPE_AD_BANNER->{
                var view:View=LayoutInflater.from(parent.context).inflate(R.layout.stripe_ad_layout,parent,false)
                return StripeAdsBannerViewHolder(view)
            }

        }
        return null!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(homePageModalList[position].type)
        {
            BANNER_SLIDER_TYPE->{
                var mutableList=homePageModalList[position].sliderModalList
                (holder as BannerSliderViewHoolder).setSliderBannerViewPager(mutableList)
            }
            STRIPE_AD_BANNER->{
              //  var mutableList=homePageModalList[position].sliderModalList
                (holder as StripeAdsBannerViewHolder).setStripeImage(homePageModalList[position].resources)
                holder.setStripeBackground(homePageModalList[position].backgroundColor)
            }
        }
    }

    override fun getItemCount(): Int {
       return homePageModalList.size
    }


    override fun getItemViewType(position: Int): Int {
        when(homePageModalList[position].type)
        {
            0->return BANNER_SLIDER_TYPE
            1->return STRIPE_AD_BANNER

        }
        return -1
    }

    class StripeAdsBannerViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        //stripead
        private lateinit var stripeImageView: ImageView
        private lateinit var stripeContainerConstraintLayout: ConstraintLayout
        //stripe ad end
        init {

            //stripead
            stripeImageView=view.findViewById(R.id.stripe_ad_imageview)
            stripeContainerConstraintLayout=view.findViewById(R.id.stripe_ad_container)
            stripeImageView.setImageResource(R.drawable.stripe_ad)
            stripeContainerConstraintLayout.setBackgroundColor(Color.parseColor("#000000"))
            //stripe ad end
        }

        public fun setStripeImage(icon:Int)
        {
            stripeImageView.setImageResource(icon)
        }
        fun setStripeBackground(color:String)
        {
            stripeContainerConstraintLayout.setBackgroundColor(Color.parseColor(color))
        }

    }

    class BannerSliderViewHoolder(view: View) : RecyclerView.ViewHolder(view)
    {
        //sliderpage
        private lateinit var viewPager2: ViewPager
        private lateinit var sliderIconList: MutableList<SliderModal>
        private lateinit var sliderPagerAdapter: SliderPagerAdapter
        private var currentItem:Int=2
        private lateinit var timer: Timer
        private final val DELAYTIME : Long=3000
        private final val PERIODTIME : Long=3000
        //sliderpage end
        init
        {
            //Sliderpage
            viewPager2=view.findViewById(R.id.main_slider_viewpager)



        }

        public fun setSliderBannerViewPager(sliderIconLists:MutableList<SliderModal>)

        {sliderIconList=sliderIconLists
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

    }



}