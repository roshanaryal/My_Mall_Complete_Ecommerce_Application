package com.roshanaryal.mymall

import android.os.Bundle
import android.view.KeyEvent
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class RegisterActivity : AppCompatActivity() {
    private var registerFrameLayout: FrameLayout? = null
    companion object{
        var isUserInResetPaswordFragment:Boolean=false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerFrameLayout = findViewById(R.id.register_frame_layout)
        setFragment(SignInFragment())
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {

            if (isUserInResetPaswordFragment)
            {
                setFragmentFromForgot(SignInFragment())
                isUserInResetPaswordFragment=false
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }




    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(registerFrameLayout!!.id, fragment)
        fragmentTransaction.commit()
    }

    private fun setFragmentFromForgot(fragment: Fragment) {

        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right)
        fragmentTransaction.replace(R.id.register_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}