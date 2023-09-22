package com.example.timezone

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.timezone.databinding.FragmentPlayBinding
import kotlin.random.Random

class Game(val context: Context, val binding:FragmentPlayBinding) {
    val cities = listOf<String>(
        "Москва",
        "Осло",
        "Париж",
        "Прага",
        "Рейкьявик",
        "Рига",
        "Рим",
        "София",
        "Стокгольм",
        "Таллин",
        "Хельсинки"
    )
    private val clocks = listOf<Int>(
        R.drawable.time_one,
        R.drawable.time_two,
        R.drawable.time_three,
        R.drawable.time_four,
        R.drawable.time_five,
        R.drawable.time_six,
        R.drawable.time_seven,
        R.drawable.time_eight
    )

    private val listOfClocks = arrayListOf<Int>()
    private val listOfCities = arrayListOf<Int>()




    fun generateCities() {
        var numberOfCity = Random.nextInt(0, 11)
        for (i in 0..5) {
            while (listOfCities.contains(numberOfCity)) {
                numberOfCity = Random.nextInt(0, 11)
            }
            listOfCities.add(numberOfCity)
        }
    }

    fun generateClocks() {
        var numberOfClock = Random.nextInt(0, 8)
        for (i in 0..5) {
            while (listOfClocks.contains(numberOfClock)) {
                numberOfClock = Random.nextInt(0, 8)
            }
            listOfClocks.add(numberOfClock)
        }
    }

    fun putImagesAndTextViews() {
        binding.ivFirstClock.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                clocks[listOfClocks[0]]
            )
        )
        binding.tvFirstCity.text = cities[listOfCities[0]]

        binding.ivSecondClock.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                clocks[listOfClocks[1]]
            )
        )
        binding.tvSecondCity.text = cities[listOfCities[1]]

        binding.ivThirdClock.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                clocks[listOfClocks[2]]
            )
        )
        binding.tvThirdCity.text = cities[listOfCities[2]]

        binding.ivFourthClock.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                clocks[listOfClocks[3]]
            )
        )
        binding.tvFourthCity.text = cities[listOfCities[3]]

        binding.ivFifthClock.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                clocks[listOfClocks[4]]
            )
        )
        binding.tvFifthCity.text = cities[listOfCities[4]]

        binding.ivSixthClock.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                clocks[listOfClocks[5]]
            )
        )
        binding.tvSixthCity.text = cities[listOfCities[5]]
    }

    fun generateQuestion(): String {
        val question = Random.nextInt(0, 6)
        return cities[listOfCities[question]]
    }

}











