package com.roshanaryal.mymall

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class SignUpFragment : Fragment() {
    private var alreadyHaveAccount: TextView? = null
    private lateinit var  email:TextView;
    private lateinit var  name:TextView;

    private lateinit var  password:TextView;
    private val emailPattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

    private lateinit var  passwordConfirm:TextView;
    private lateinit var firebaseAuth: FirebaseAuth;

    private lateinit var signUpBtn:Button;
    private lateinit var progressbar:ProgressBar
    private lateinit var drawable:Drawable;
    private lateinit var firestore: FirebaseFirestore

    private lateinit var closeButton: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        alreadyHaveAccount = view.findViewById(R.id.tv_already_have_an_account)
        email=view.findViewById(R.id.signUpEmail);
        name=view.findViewById(R.id.signUpFullName);
        closeButton=view.findViewById(R.id.signUpCloseBtn)

        password=view.findViewById(R.id.signUpPassword);
        progressbar=view.findViewById(R.id.signupProgressBar)

        passwordConfirm=view.findViewById(R.id.signUpConfirmPassword);

        signUpBtn=view.findViewById(R.id.signUpBtn);

        firebaseAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance()

        drawable=resources.getDrawable(R.drawable.ic_baseline_error_24)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        signUpBtn.setOnClickListener {
            if (checkEmpty())
            {
              if (confirmPassword())
              {
                  if (checkEmail()) {
                      signUp()
                  }
              }
            }
        }


        return view
    }



    private fun checkEmail():Boolean

    {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
        {


            return true
        }
        else
        {
            email.setError("error", drawable)
            Toast.makeText(context, "Incorrect email", Toast.LENGTH_SHORT).show();
            return false
        }
    }
    private fun signUp()
    {
        progressbar.isVisible=true
        firebaseAuth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
            if (it.isSuccessful)
            {
               // progressbar.isVisible=false
                val userHAshmap: HashMap<Any, String> = HashMap()
               userHAshmap.put("fullname",name.text.toString())
                firestore.collection("USERS")
                        .add(userHAshmap)
                        .addOnCompleteListener {
                            if (it.isSuccessful)
                            {
                                mainIntent()
                            }
                            else
                            {
                                progressbar.isVisible=false
                                Log.d("TAG", it.exception.toString())
                                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }


            }
            else
            {
                progressbar.isVisible=false
                Log.d("TAG", it.exception.toString())
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private fun confirmPassword():Boolean
    {
        if (password.text.toString().equals(passwordConfirm.text.toString()))
        {
            signUpBtn.setTextColor(Color.rgb(255, 255, 255))
          //  signUpBtn.isEnabled=false;
            return true

        }
        else
        {
            Toast.makeText(context, "Password doesnot match", Toast.LENGTH_SHORT).show();
            signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
          //  signUpBtn.isEnabled=true;
            return false;
        }
    }

    private  fun checkEmpty():Boolean
    {
        if (name.text.isEmpty())
        {
           Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
            signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
           // signUpBtn.isEnabled=false;
            return false;
        }else if (email.text.isEmpty())
        {
            Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show();
            signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
          //  signUpBtn.isEnabled=false;
            return false;


        }
        else if (password.text.isEmpty())
        {
            Toast.makeText(context, "Enter pasword", Toast.LENGTH_SHORT).show();
            signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
           // signUpBtn.isEnabled=false;
            return false;

        }
        else if (passwordConfirm.text.isEmpty())
        {
            Toast.makeText(context, "confirm password", Toast.LENGTH_SHORT).show();
            signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
          //  signUpBtn.isEnabled=false;
            return false;

        }
        else
        {
            signUpBtn.setTextColor(Color.rgb(255, 255, 255))
           // signUpBtn.isEnabled=true;
            return true
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alreadyHaveAccount!!.setOnClickListener { setFragment(SignInFragment()) }
        closeButton.setOnClickListener {
            mainIntent()
        }
    }

    private fun setFragment(fragment: Fragment) {

        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right)
        fragmentTransaction.replace(R.id.register_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun mainIntent() {

        var intent=Intent(activity,MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}