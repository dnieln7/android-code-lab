package com.dnieln7.view_binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dnieln7.view_binding.databinding.FragmentWelcomeBinding

class Welcome : Fragment() {
    private var binding: FragmentWelcomeBinding? = null
    private val fragmentBinding get() = binding!!

    private var changed = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        init()

        return fragmentBinding.root
    }

    private fun init() {
        fragmentBinding.changeText.setOnClickListener {
            fragmentBinding.dynamicSubtitle.text = "This text has been changed $changed times"
            fragmentBinding.dynamicBody.text = "Now you can continue"
            fragmentBinding.navigateNext.visibility = View.VISIBLE

            changed += 1
        }

        /*
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right // Enters from right - Next fragment
                exit = R.anim.slide_out_left // Goes out to left - Current fragment
                popEnter = R.anim.slide_in_left // Enters from left - Current (Previous) fragment
                popExit = R.anim.slide_out_right // Goes out to right - Next (New current) fragment
            }
        }
        */

        fragmentBinding.navigateNext.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                WelcomeDirections.actionWelcomeToToActivity(
                    count = changed
                )
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}