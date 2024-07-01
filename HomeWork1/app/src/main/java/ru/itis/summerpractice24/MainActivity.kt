package ru.itis.summerpractice24

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var numCarsEditText: EditText
    private lateinit var startRaceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numCarsEditText = findViewById(R.id.numCarsEditText)
        startRaceButton = findViewById(R.id.startRaceButton)

        startRaceButton.setOnClickListener {
            val numCars = numCarsEditText.text.toString().toIntOrNull()
            if (numCars == null || numCars < 2) {
                Toast.makeText(this, "Put correct num of cars (min is 2)", Toast.LENGTH_SHORT).show()
            } else {
                startRace(numCars)
            }
        }
    }

    private fun startRace(numCars: Int) {
        val participants = mutableListOf<Car>()
        repeat(numCars) {
            participants.add(getRandomCar())
        }

        var round = 1
        while (participants.size > 1) {
            println("Beginning $round round")
            val winners = mutableListOf<Car>()

            while (participants.size > 1) {
                val car1 = participants.random()
                participants.remove(car1)
                val car2 = participants.random()
                participants.remove(car2)

                doRace(car1, car2)?.let { winners.add(it) }
            }

            if (participants.size == 1) {
                winners.add(participants.removeAt(0))
            }

            participants.addAll(winners)
            round++

            for (winner in winners) {
                println("Winners in round $round : ${winner.name} ${winner.model}")
            }
        }

        println("Winner of racing: ${participants.first().name} ${participants.first().model}")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

open class Car(
    val name: String,
    val model: String,
    val year: Int,
    val color: String
) {
    open fun getInfo() {
        println("Name: $name, Model: $model, Year: $year, Color: $color")
    }
}

class ElectricCar(
    name: String,
    model: String,
    year: Int,
    color: String,
    val batteryPower: Int,
    val dischargePerHour: Int
) : Car(name, model, year, color){
    override fun getInfo(){
        super.getInfo()
        println("Battery Power: $batteryPower, Discharge Per Hour: $dischargePerHour")
    }
}
enum class DriveType{
    FRONT_WHEEL,
    REAR_WHEEL,
    ALL_WHEEL
}
class Crossover(
    name: String,
    model: String,
    year: Int,
    color: String,
    val driveType: DriveType,
    val enginePower: Int
): Car(name, model, year, color) {
    override fun getInfo(){
        super.getInfo()
        println("DriveType: $driveType, Engine Power: $enginePower")
    }
}

class SportCar(
    name: String,
    model: String,
    year: Int,
    color: String,
    val maxSpeed: Int,
    val horsepower: Int
) : Car(name, model, year, color) {
    override fun getInfo(){
        super.getInfo()
        println("Max speed: $maxSpeed, Horsepower: $horsepower")
    }
}

class Limousine(
    name: String,
    model: String,
    year: Int,
    color: String,
    val length: Int,
    val numOfSeats: Int
) :  Car(name, model, year, color) {
    override fun getInfo(){
        super.getInfo()
        println("Length: $length, Num of seats: $numOfSeats")
    }
}


fun getRandomCar(): Car {
    val randomName = listOf("Toyota", "Honda","BMW","Mercedes", "Ford", "Chevrolet").random()
    val randomModel = (1..10).random().toString()
    val randomYear = (1990..2021).random()
    val randomColor = listOf("red", "blue", "black", "white").random()

    val randomCarType = listOf(
        ElectricCar(randomName, randomModel, randomYear, randomColor, Random.nextInt(100, 200), Random.nextInt(20, 50)),
        Crossover(randomName, randomModel, randomYear, randomColor, DriveType.values().random(), Random.nextInt(100, 300)),
        SportCar(randomName, randomModel, randomYear, randomColor, Random.nextInt(200, 400), Random.nextInt(200, 500)),
        Limousine(randomName, randomModel, randomYear, randomColor, Random.nextInt(5000, 7000), Random.nextInt(6, 12))
    ).random()

    return randomCarType
}

fun getSpeedForCar(car: Car): Int {
    val random = Random.Default
    return when (car) {
        is ElectricCar -> random.nextInt(80, 175)
        is Crossover -> random.nextInt(80, 140)
        is SportCar -> random.nextInt(100, 200)
        is Limousine -> random.nextInt(50, 100)
        else -> random.nextInt(70,120)
    }
}

fun doRace(car1: Car, car2: Car): Car? {
    val car1Speed = getSpeedForCar(car1)
    val car2Speed = getSpeedForCar(car2)

    println("Race between ${car1.name} ${car1.model} and ${car2.name} ${car2.model}. ")

    return when {
        car1Speed > car2Speed -> {
            println("Winner: ${car1.name} ${car1.model}")
            car1
        }
        car1Speed < car2Speed -> {
            println("Winner: ${car2.name} ${car2.model}")
            car2
        }
        else -> {
            println("Auto finish together")
            null
        }
    }
}

