package com.example.task_qualwebs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.task_qualwebs.R
import com.example.task_qualwebs.ui.fragment.FavoritesFragment
import com.example.task_qualwebs.ui.fragment.MenuFragment
import com.example.task_qualwebs.ui.fragment.RecentFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private var startingPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        replace(MenuFragment())
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.menu -> loadFragment(MenuFragment(),0)
                R.id.recent -> loadFragment(RecentFragment(),1)
                R.id.favorites -> loadFragment(FavoritesFragment(),2   )
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun loadFragment(
        fragment: Fragment?,
        newPosition: Int
    ): Boolean {
        if (fragment != null) {
            if (startingPosition > newPosition) {
               goRight(fragment)
            }
            if (startingPosition < newPosition) {
                goLeft(fragment)
            }
            startingPosition = newPosition
            return true
        }
        return false
    }

    fun replace(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame, fragment)
        ft.commit()
    }

    fun goRight(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left)
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    fun goLeft(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_down,
            R.anim.slide_out_down
        )
    }

}
