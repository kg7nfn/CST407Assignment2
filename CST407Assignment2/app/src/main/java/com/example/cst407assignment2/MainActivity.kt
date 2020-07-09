package com.example.cst407assignment2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

const val DICEINCREMNT = 1
const val DICEMIN = 1
const val DICEMAX = 50

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var numberOfDice = 1
    val sides = arrayOf(2,4,6,8,10,20)
    var numSides = 2
    val dice = Dice()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,sides)

        numDiceTextView.text = numberOfDice.toString()

        diceUp.setOnClickListener(this)
        diceDown.setOnClickListener(this)
        diceRoll.setOnClickListener(this)

        diceSpinner.adapter = arrayAdapter

        diceSpinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                numSides = sides[pos]
            }
        }
    }

    override fun onClick(button: View?) {
        when (button?.id) {
            R.id.diceUp -> incremntDice()
            R.id.diceDown -> decremntDice()
            R.id.diceRoll -> roll()
        }
    }

    private fun roll() {
        var t = Toast.makeText(this, "Rolling", Toast.LENGTH_SHORT)
        t.show()
        var diceReturned = mutableListOf<Int>()
        for ( x in 1..numberOfDice)
            diceReturned.add(dice.rollDice(numSides))
        diceResult.text = diceReturned.toString().replace("[", "").replace("]", "")
        diceReturned.sum()
        diceTotalText.text = diceReturned.sum().toString()
    }

    private fun decremntDice() {
        if(numberOfDice != DICEMIN)
            numberOfDice -= DICEINCREMNT;
        else {
            var t = Toast.makeText(this, "Minimum number of dice reached", Toast.LENGTH_SHORT)
            t.show()
        }
        numDiceTextView.text = numberOfDice.toString()
    }

    private fun incremntDice() {
        if(numberOfDice != DICEMAX)
            numberOfDice += DICEINCREMNT;
        else {
            var t = Toast.makeText(this, "Maximum number of dice reached", Toast.LENGTH_SHORT)
            t.show()
        }
        numDiceTextView.text = numberOfDice.toString()
    }

}