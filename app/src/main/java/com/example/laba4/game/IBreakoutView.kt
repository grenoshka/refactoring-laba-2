package com.example.laba4.game

import android.annotation.SuppressLint
import android.graphics.Paint

interface IBreakoutView {
    var paint: Paint

    // This variable tracks the game frame rate
    var fps: Long

    // This is used to help calculate the fps
    var timeThisFrame: Long

    // The players paddle
    var paddle: IPaddle

    // A ball
    var ball: IBall

    // Up to 200 bricks
    var bricks: Array<Brick?>

    // The score
    var score: Int

    // Lives
    var lives: Int

    fun createBricksAndRestart()
    fun run()

    // Everything that needs to be updated goes in here
// Movement, collision detection etc.
    fun update()

    // Draw the newly updated scene
    @SuppressLint("UseCompatLoadingForDrawables")
    fun draw()

    // If SimpleGameEngine Activity is paused/stopped
// shutdown our thread.
    fun pause()

    // If SimpleGameEngine Activity is started then
// start our thread.
    fun resume()
}