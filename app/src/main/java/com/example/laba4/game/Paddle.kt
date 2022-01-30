package com.example.laba4.game

import android.graphics.RectF


interface IPaddle {
    val screenX: Int
    val screenY: Int

    // This is a getter method to make the rectangle that
    // defines our paddle available in BreakoutView class
    // RectF is an object that holds four coordinates - just what we need
    val rect: RectF

    // How long and high our paddle will be
    val length: Float
    val height: Float

    // X is the far left of the rectangle which forms our paddle
    var x: Float

    // Y is the top coordinate
    val y: Float

    // This will hold the pixels per second speed that the paddle will move
    val paddleSpeed: Float

    // Which ways can the paddle move
    val STOPPED: Int
    val LEFT: Int
    val RIGHT: Int

    // Is the paddle moving and in which direction
    var paddleMoving: Int
    fun reset()

    // This method will be used to change/set if the paddle is going left, right or nowhere
    fun setMovementState(state: Int)

    // This update method will be called from update in BreakoutView
    // It determines if the paddle needs to move and changes the coordinates
    // contained in rect if necessary
    fun update(fps: Long)
}

class Paddle(override val screenX: Int, override val screenY: Int) : IPaddle {
    // This is a getter method to make the rectangle that
    // defines our paddle available in BreakoutView class
    // RectF is an object that holds four coordinates - just what we need
    override val rect: RectF

    // How long and high our paddle will be
    override val length = 250f
    override val height = 50f

    // X is the far left of the rectangle which forms our paddle
    override var x: Float = 0.0f

    // Y is the top coordinate
    override val y: Float

    // This will hold the pixels per second speed that the paddle will move
    override val paddleSpeed: Float

    // Which ways can the paddle move
    override val STOPPED = 0
    override val LEFT = 1
    override val RIGHT = 2

    // Is the paddle moving and in which direction
    override var paddleMoving = STOPPED

    override fun reset(){
        x = screenX / 2.toFloat() - length/2
        rect.left = x
        rect.top = y
        rect.right  = x + length
        rect.bottom = y + height
    }

    // This method will be used to change/set if the paddle is going left, right or nowhere
    override fun setMovementState(state: Int) {
        paddleMoving = state
    }

    // This update method will be called from update in BreakoutView
    // It determines if the paddle needs to move and changes the coordinates
    // contained in rect if necessary
    override fun update(fps: Long) {
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