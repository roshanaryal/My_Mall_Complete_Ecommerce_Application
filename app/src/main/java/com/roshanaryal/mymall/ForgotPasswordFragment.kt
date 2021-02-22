package com.roshanaryal.mymall

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.transition.TransitionManager
import com.google.firebase.auth.FirebaseAuth
import com.roshanaryal.mymall.RegisterActivity.Companion.isUserInResetPaswordFragment


class ForgotPasswordFragment : Fragment() {


    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var linearLayout: LinearLayout
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var textviewEmailSent:TextView
    private lateinit var textViewBack:TextView

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        editText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkInput()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


        button.setOnClickListener {
            TransitionManager.beginDelayedTransition(linearLayout)
            textviewEmailSent.isVisible=false
            TransitionManager.beginDelayedTransition(linearLayout)
            button.isEnabled=false
            button.setTextColor(Color.argb(50,255,255,255))
            progressBar.isVisible=true
            imageView.isVisible=true

            firebaseAuth.sendPasswordResetEmail(editText.text.toString()).addOnCompleteListener {
                if (it.isSuccessful)
                {
                    TransitionManager.beginDelayedTransition(linearLayout)
                    imageView.setImageResource(R.drawable.ic_baseline_mark_email_read_24)
                   // imageView.imageTintList= ColorStateList()
                    imageView.setColorFilter(resources.getColor(R.color.successGreen))
                    textviewEmailSent.isVisible=true
                    textviewEmailSent.setTextColor(resources.getColor(R.color.successGreen))
                    Toast.makeText(context,"Email sent succesfuly ,please check inbox",Toast.LENGTH_LONG).show()

               // textviewEmailSent.setTransitionVisibility(View.VISIBLE)
                }
                else
                {
                    button.isEnabled=true
                    button.setTextColor(Color.rgb(255,255,255))

                    TransitionManager.beginDelayedTransition(linearLayout)
                    imageView.setImageResource(R.drawable.ic_baseline_email_24)
                   // imageView.setImageResource(R.drawable.ic_baseline_mark_email_read_24)
                    // imageView.imageTintList= ColorStateList()
                    textviewEmailSent.isVisible=true
                    textviewEmailSent.setText(it.exception.toString())
                    textviewEmailSent.setTextColor(resources.getColor(R.color.btnRed))

                    Toast.makeText(context,it.exception.toString(),Toast.LENGTH_SHORT).show()

                }

                progressBar.isVisible=false

            }

        }

        textViewBack.setOnClickListener{
            setFragment(SignInFragment())
            isUserInResetPaswordFragment =false
        }
    }

    private fun setFragment(fragment: Fragment) {

        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right)
        fragmentTransaction.replace(R.id.register_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun checkInput() {

        val drawable=resources.getDrawable(R.drawable.ic_baseline_error_24)
        drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)
        drawable.setTint(Color.parseColor("#ff0000"))
        if (editText.text.isEmpty())
        {
            editText.setError("Enter email",drawable)

            button.isEnabled=false
            button.setTextColor(Color.argb(50,255,255,255))
        }
        else if ( !android.util.Patterns.EMAIL_ADDRESS.matcher(editText.text.toString()).matches())
        {
            button.isEnabled=false
            button.setTextColor(Color.argb(50,255,255,255))
            editText.setError("Wrong email adress",drawable)

        }
        else
        {
            button.isEnabled=true
            button.setTextColor(Color.rgb(255,255,255))
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var view:View= inflater.inflate(R.layout.fragment_forgot_password, container, false)

        editText=view.findViewById(R.id.forgotPasswordEmail)
        button=view.findViewById(R.id.resetPasswordBtn)
        linearLayout=view.findViewById(R.id.forgot_passord_email_icon_container)
        imageView=view.findViewById(R.id.emailIcon)
        textviewEmailSent=view.findViewById(R.id.emailSentTextView)
        textViewBack=view.findViewById(R.id.gobackTextView)
        progressBar=view.findViewById(R.id.progressBarForgotPassword)
        firebaseAuth= FirebaseAuth.getInstance()

        return view
    }


}