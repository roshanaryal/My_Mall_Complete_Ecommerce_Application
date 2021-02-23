package com.roshanaryal.mymall

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{
        private lateinit var firebaseAuth: FirebaseAuth
        private lateinit var frameLayout: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val actionBarDrawerToggle= ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_app_bar_open_drawer_description,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navView.setNavigationItemSelectedListener (this)
      //  actionBarDrawerToggle.
        navView.menu.getItem(0).setChecked(true)

        //firebase
        firebaseAuth= FirebaseAuth.getInstance()

        //framelayout
        frameLayout=main_frame_layout
        setFragment(HomeFragment())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId){
            R.id.action_search-> {}
            R.id.action_notification->{}
            R.id.action_cart->{}

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      val id=item.itemId
        when(id)
        {
            R.id.nav_home-> {
                drawer_layout.closeDrawer(GravityCompat.START)

                return true
            }
            R.id.nav_home->{
                return true
            }
            R.id.nav_home->{
                return true
            }
            R.id.nav_home->{
                return true
            }
            R.id.nav_home->{
                return true
            }
            R.id.nav_home->{
                return true
            }
           R.id.nav_logout-> {
               firebaseAuth.signOut();
               val intent=Intent(this,RegisterActivity::class.java)
               startActivity(intent)
               finish()

           }




        }
        return false
    }

    private fun setFragment(fragment:Fragment)
    {
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        //fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_from_left)
        fragmentTransaction.replace(main_frame_layout.id,fragment)
        fragmentTransaction.commit()

    }


}