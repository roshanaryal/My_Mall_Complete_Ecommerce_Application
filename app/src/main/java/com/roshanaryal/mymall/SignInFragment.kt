package com.roshanaryal.mymall

import android.content.Intent
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
import com.roshanaryal.mymall.RegisterActivity.Companion.isUserInResetPaswordFragment

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {
    private lateinit var email:TextView
    private lateinit var password:TextView
    private lateinit var drawable: Drawable
    private var dontHaveAnAccount: TextView? = null
    private lateinit var signInBtn:Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var closeButton: ImageButton
    private lateinit var forgotPassword:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        dontHaveAnAccount = view.findViewById(R.id.tv_already_have_an_account)
        email=view.findViewById(R.id.signInEmail)
        password=view.findViewById(R.id.signInPassword)
        signInBtn=view.findViewById(R.id.signInBtn)
        closeButton=view.findViewById(R.id.signInCloseBtn)
        progressBar=view.findViewById(R.id.signInProgressBar)
        forgotPassword=view.findViewById(R.id.signInForgotPassword)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dontHaveAnAccount!!.setOnClickListener {
            setFragment(SignUpFragment())
        }
        closeButton.setOnClickListener {
            mainIntent()
        }
        forgotPassword.setOnClickListener{
            setFragment(ForgotPasswordFragment())
            isUserInResetPaswordFragment=true
        }
            drawable=resources.getDrawable(R.drawable.ic_baseline_error_24)
            drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)

            firebaseAuth= FirebaseAuth.getInstance()

            signInBtn.setOnClickListener {
                Log.d("TAG", "onViewCreated:clicked ")
                if (checkEmpty())
                {
                    progressBar.isVisible=true
                  firebaseAuth.signInWithEmailAndPassword(email.text.toString(),password.text.toString())
                          .addOnCompleteListener {
                              if(it.isSuccessful)
                              {
                                mainIntent()
                              }
                              else
                              {
                                  Toast.makeText(context,it.exception.toString(),Toast.LENGTH_SHORT).show()
                                  progressBar.isVisible=false
                              }
                          }

            }



            // Intent(getActivity(),MainActivity.class)
        }
    }

    private fun mainIntent() {

        var intent=Intent(activity,MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun checkEmpty():Boolean{
        if (email.text.toString().isEmpty())
        {

            Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show();
            email.setError("Enter email",drawable)
            return false
        }
       else if (password.text.toString().isEmpty())
        {
            password.setError("Enterr password",drawable)
            Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
           return false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
            {
                Toast.makeText(context, "Wrong email", Toast.LENGTH_SHORT).show();
                email.setError("wrong email",drawable)
                return false
            }
        else
        {
            return true
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left)
        fragmentTransaction.replace(R.id.register_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun setFragmentWithStack(fragment: Fragment) {
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left)
        fragmentTransaction.add(R.id.register_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}