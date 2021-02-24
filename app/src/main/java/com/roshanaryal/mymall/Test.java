package com.roshanaryal.mymall;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.viewpager2.widget.ViewPager2;

public class Test
{
    EditText e;
    public static boolean isUserInForgotPasswordFragment=false;


    void test(){
       // e=new EditText()
       ViewPager2.OnPageChangeCallback onPageChangeCallback= new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        };

//       ViewPager2 viewPager2=new ViewPager2();
//       viewPager2.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View v, MotionEvent event) {
//               int i;
//               return false;
//           }
//       });


}
}
