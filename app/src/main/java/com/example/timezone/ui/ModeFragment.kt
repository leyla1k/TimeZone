package com.example.timezone.ui

import android.os.Bundle
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timezone.R
import com.example.timezone.databinding.FragmentEntryBinding
import com.example.timezone.databinding.FragmentModeBinding


class ModeFragment : Fragment() {


    private var _binding: FragmentModeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonCasual.setOnClickListener {
            findNavController().navigate(
                ModeFragmentDirections.actionModeFragmentToPlayFragment(false)
            )
        }
        binding.buttonPlayForTime.setOnClickListener {
            findNavController().navigate(
                ModeFragmentDirections.actionModeFragmentToPlayFragment(true)
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }


}