package com.dnieln7.view_binding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.dnieln7.view_binding.databinding.FragmentToActivityBinding

class ToActivity : Fragment() {
    private var binding: FragmentToActivityBinding? = null
    private val fragmentBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentToActivityBinding.inflate(inflater, container, false)

        val safeArgs: ToActivityArgs by navArgs()
        val count = safeArgs.count

        init()

        Toast.makeText(
            requireContext(),
            "You pressed the previous fragment button $count times",
            Toast.LENGTH_LONG
        ).show()

        return fragmentBinding.root
    }

    private fun init() {
        fragmentBinding.changeText.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), ExtraActivity::class.java))
        }

        fragmentBinding.navigateBack.setOnClickListener {
            Navigation.findNavController(fragmentBinding.root).popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}