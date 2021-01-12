package com.example.laba4.game

import android.graphics.RectF
import java.util.*


class Ball(screenX: Int, screenY: Int) {
    var rect: RectF
    var xVelocity: Float
    var yVelocity: Float
    var ballWidth = 10f
    var ballHeight = 10f

    fun update(fps: Long) {
        rect.left = rect.left + xVelocity / fps
        rect.top = rect.top + yVelocity / fps
        rect.right = rect.left + ballWidth
        rect.bottom = rect.top - ballHeight
    }

    fun reverseYVelocity() {
        yVelocity = -yVelocity
    }

    fun reverseXVelocity() {
        xVelocity = -xVelocity
    }

    fun setRandomXVelocity() {
        val generator = Random()
        val answer = generator.nextInt(2)
        if (answer == 0) {
            reverseXVelocity()
        }
    }

    fun clearObstacleY(y: Float) {
        rect.bottom = y
        rect.top = y - ballHeight
    }

    fun clearObstacleX(x: Float) {
        rect.left = x
        rect.right = x + ballWidth
    }

    fun reset(x: Int, y: Int) {
        rect.left = x / 2.toFloat()
        rect.top = y - 20.toFloat()
        rect.right = x / 2 + ballWidth
        rect.bottom = y - 20 - ballHeight
    }

    init {

        // Start the ball travelling straight up at 100 pixels per second
        xVelocity = 200f
        yVelocity = -400f

        // Place the ball in the centre of the screen at the bottom
        // Make it a 10 pixel x 10 pixel square
        rect = RectF()
    }
}