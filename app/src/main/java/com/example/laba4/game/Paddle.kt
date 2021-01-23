package com.example.laba4.game

import android.graphics.RectF


class Paddle(val screenX: Int, val screenY: Int) {
    // This is a getter method to make the rectangle that
    // defines our paddle available in BreakoutView class
    // RectF is an object that holds four coordinates - just what we need
    val rect: RectF

    // How long and high our paddle will be
    val length = 250f
    val height = 50f

    // X is the far left of the rectangle which forms our paddle
    private var x: Float

    // Y is the top coordinate
    private val y: Float

    // This will hold the pixels per second speed that the paddle will move
    private val paddleSpeed: Float

    // Which ways can the paddle move
    val STOPPED = 0
    val LEFT = 1
    val RIGHT = 2

    // Is the paddle moving and in which direction
    private var paddleMoving = STOPPED

    fun reset(){
        x = screenX / 2.toFloat() - length/2
        rect.left = x
        rect.top = y
        rect.right  = x + length
        rect.bottom = y + height
    }

    // This method will be used to change/set if the paddle is going left, right or nowhere
    fun setMovementState(state: Int) {
        paddleMoving = state
    }

    // This update method will be called from update in BreakoutView
    // It determines if the paddle needs to move and changes the coordinates
    // contained in rect if necessary
    fun update(fps: Long) {
        if (paddleMoving == LEFT && rect.left>0f) {
            x = x - paddleSpeed / fps
        }
        if (paddleMoving == RIGHT && rect.right<screenX) {
            x = x + paddleSpeed / fps
        }
        rect.left = x
        rect.right = x + length
    }



    // This the the constructor method
    // When we create an object from this class we will pass
    // in the screen width and height
    init {
        // 130 pixels wide and 20 pixels high

        // Start paddle in roughly the sceen centre
        x = screenX / 2.toFloat() - length/2
        y = screenY -height
        rect = RectF(x, y, x + length, y + height)

        // How fast is the paddle in pixels per second
        paddleSpeed = 350f
    }
}