package com.example.tictactoe

import  android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoe.R.id

class play : AppCompatActivity() {
//    private late init var x: TextView
//    private late init var y: TextView
    private lateinit var box: TextView
    private lateinit var playArea: RelativeLayout
    private lateinit var btnPlayAgain: AppCompatButton

    private var move = 0
    private var gameOver = false

    private val boxId: Array<IntArray> = arrayOf(intArrayOf(id.box00, id.box01,id.box02), intArrayOf(id.box10,id.box11, id.box12), intArrayOf(id.box20, id.box21, id.box22))

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
//        x = findViewById(id.x)
//        y = findViewById(id.y)
        playArea = findViewById((id.play_area))
        playArea.setOnTouchListener { _, motionEvent ->
            val col = motionEvent.x / (100).toPx()
            val row = motionEvent.y / (100).toPx()
            touched(row.toInt(), col.toInt())

            // just for reference
//            x.text = row.toInt().toString()
//            y.text = col.toInt().toString()
            false
        }
        btnPlayAgain = findViewById(id.btnPlayAgain)
        btnPlayAgain.setOnClickListener {
            startNewGame()
        }


    }

    private fun touched(row: Int, col: Int){
        val boxRId = boxId[row][col]
        box = findViewById(boxRId)
        if(box.text == "" && !gameOver){
            box.text = if (move % 2 == 0) "X" else "O"
            move++
            if(move % 2 == 0){
                findViewById<TextView>(id.x_move_indicator).visibility = View.VISIBLE
                findViewById<TextView>(id.y_move_indicator).visibility = View.INVISIBLE
            }
            else{
                findViewById<TextView>(id.x_move_indicator).visibility = View.INVISIBLE
                findViewById<TextView>(id.y_move_indicator).visibility = View.VISIBLE
            }
            analyse(box.text.toString())
        }
    }
    private fun analyse(check: String){
        // horizontal and vertical check
        for(i in 0..2){
            var horizontal = 0
            var vertical = 0
            for(j in 0..2){
                if(check == findViewById<TextView>(boxId[i][j]).text)
                    horizontal++
                if(check == findViewById<TextView>(boxId[j][i]).text)
                    vertical++
            }
            if(horizontal == 3 || vertical == 3){
                gameOverFunction(check)
                return
            }
        }
        // diagonal check
        var left = 0
        var right = 0
        for(i in 0..2){
            if(check == findViewById<TextView>(boxId[i][i]).text)
                left++
            if(check == findViewById<TextView>(boxId[i][2-i]).text)
                right++
        }
        if(left == 3 || right == 3){
            gameOverFunction(check)
            return
        }
        if(move == 9)
            gameOverFunction("")

    }
    @SuppressLint("SetTextI18n")
    private fun gameOverFunction(winning: String){
        gameOver = true
        if(winning != "")
            //findViewById<TextView>(R.id.EndResult).text = if(winning == "X") "X Won" else "O Won"
            findViewById<TextView>(id.EndResult).text = "$winning Won"
        else
            findViewById<TextView>(id.EndResult).text = "Draw"
        findViewById<LinearLayout>(id.gameEnded).visibility = View.VISIBLE
        findViewById<LinearLayout>(id.logo_indicator).visibility = View.INVISIBLE
    }
    private fun startNewGame(){
        gameOver = false
        findViewById<LinearLayout>(id.gameEnded).visibility = View.INVISIBLE
        findViewById<LinearLayout>(id.logo_indicator).visibility = View.VISIBLE
        for (i in 0..2)
            for(j in 0..2)
                findViewById<TextView>(boxId[i][j]).text = ""
        move = 0
        findViewById<TextView>(id.x_move_indicator).visibility = View.VISIBLE
        findViewById<TextView>(id.y_move_indicator).visibility = View.INVISIBLE
    }


    // converters
    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
    // private fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

}

