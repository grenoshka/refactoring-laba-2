package com.example.laba4.game

import android.graphics.RectF
import java.util.*


interface IBall {
    var rect: RectF
    var xVelocity: Float
    var yVelocity: Float
    var ballWidth: Float
    var ballHeight: Float
    fun update(fps: Long)
    fun reverseYVelocity()
    fun reverseXVelocity()
    fun setRandomXVelocity()
    fun clearObstacleY(y: Float)
    fun clearObstacleX(x: Float)
    fun reset(x: Int, y: Int)
}

class Ball(screenX: Int, screenY: Int) : IBall {
    override lateinit var rect: RectF
    override var xVelocity: Float = 0.0f
    override var yVelocity: Float = 0.0f
    override var ballWidth = 50f
    override var ballHeight = 50f

    override fun update(fps: Long) {
        rect.left = rect.left + xVelocity / fps
        rect.top = rect.top + yVelocity / fps
        rect.right = rect.left + ballWidth
        rect.bottom = rect.top + ballHeight
    }

    override fun reverseYVelocity() {
        yVelocity = -yVelocity
    }

    override fun reverseXVelocity() {
        xVelocity = -xVelocity
    }

    override fun setRandomXVelocity() {
        val generator = Random()
        val answer = generator.nextInt(2)
        if (answer == 0) {
            reverseXVelocity()
        }
    }

    override fun clearObstacleY(y: Float) {
        rect.bottom = y
        rect.top = y - ballHeight
    }

    override fun clearObstacleX(x: Float) {
        rect.left = x
        rect.right = x + ballWidth
    }

    override fun reset(x: Int, y: Int) {
        rect.left = x / 2.toFloat()-ballWidth/2
        rect.top = y - 50.toFloat()-ballHeight
        rect.right = x / 2 + ballWidth/2
        rect.bottom = y - 50f
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