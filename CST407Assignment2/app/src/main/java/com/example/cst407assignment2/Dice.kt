package com.example.cst407assignment2

class Dice {
    fun rollDice(side: Int): Int {
        return side.random()
    }
}
fun Int.random(): Int {
    return (1..this).random()
}