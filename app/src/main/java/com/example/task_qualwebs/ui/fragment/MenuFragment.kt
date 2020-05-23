package com.example.task_qualwebs.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.example.task_qualwebs.R
import kotlinx.android.synthetic.main.fragment_menu.view.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    lateinit var mView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_menu, container, false)


        goRight(BreakFastFragment())

        mView.breakfast.setOnClickListener {
            goLeft(BreakFastFragment())

            mView.lunch.setBackground(ContextCompat.getDrawable(context!!, R.drawable.right_dark_background))
            mView.breakfast.setBackground(ContextCompat.getDrawable(context!!, R.drawable.left_light_background))
            mView.lunch_txt.setTextColor(getResources().getColor(R.color.white))
            mView.lunch_time_txt.setTextColor(getResources().getColor(R.color.white))
            mView.breakfast_txt.setTextColor(getResources().getColor(R.color.pink))
            mView.breakfast_time_txt.setTextColor(getResources().getColor(R.color.pink))
        }

        mView.lunch.setOnClickListener {
            goRight(LunchFragment())


            mView.lunch.setBackground(ContextCompat.getDrawable(context!!, R.drawable.right_light_background))
            mView.breakfast.setBackground(ContextCompat.getDrawable(context!!, R.drawable.left_dark_background))
            mView.lunch_txt.setTextColor(getResources().getColor(R.color.pink));
            mView.lunch_time_txt.setTextColor(getResources().getColor(R.color.pink));
            mView.breakfast_txt.setTextColor(getResources().getColor(R.color.white));
            mView.breakfast_time_txt.setTextColor(getResources().getColor(R.color.white));
        }


        return mView
    }

    fun goRight(fragment : Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left)
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    fun goLeft(fragment : Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }


}
