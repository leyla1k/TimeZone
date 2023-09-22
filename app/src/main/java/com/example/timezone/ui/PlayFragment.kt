package com.example.timezone.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timezone.Game
import com.example.timezone.databinding.FragmentPlayBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Date


class PlayFragment : Fragment() {

    private var _binding: FragmentPlayBinding? = null
    lateinit var city: String
    var time: Long=0
    private val binding get() = _binding!!
    private val playMode by lazy { navArgs<PlayFragmentArgs>().value.typeOfGame }
    private val game by lazy { Game(requireActivity(), binding) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)


        game.generateClocks()
        game.generateCities()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        game.putImagesAndTextViews()
        if (playMode) {
            startDownCountingTimer()
        } else {
            Toast.makeText(requireActivity(), "Запомнив, тапните по экрану", Toast.LENGTH_SHORT)
                .show()
            startCountingTimer()
        }
        city = game.generateQuestion()


        super.onViewCreated(view, savedInstanceState)
    }

    private fun startCountingTimer() {
        val startTime = Date()
        binding.constraintlayout.setOnClickListener {
           // val f: NumberFormat = DecimalFormat("00")
             time = Date().time - startTime.time
            //val min = time / 60000 % 60
           // val sec = time / 1000 % 60

            findNavController().navigate(
                PlayFragmentDirections.actionPlayFragmentToAnswerFragment(
                    time.toInt(), city
                )
            )
            // Toast.makeText(requireActivity(),  f.format(min) + ":" + f.format(sec), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startDownCountingTimer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                binding.tvTimer.text = f.format(min) + ":" + f.format(sec)

            }

            override fun onFinish() {
                binding.tvTimer.text = "00:00"
                findNavController().navigate(
                    PlayFragmentDirections.actionPlayFragmentToAnswerFragment(
                        time.toInt(), city
                    )
                )
            }
        }.start()
    }

}