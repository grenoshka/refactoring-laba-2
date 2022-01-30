package com.example.laba4.breakoutview.restart

import android.graphics.RectF
import com.example.laba4.game.IBall

class MockBall() : IBall {
    override lateinit var rect: RectF
    override var xVelocity: Float = 0.0f
    override var yVelocity: Float = 0.0f
    override var ballWidth = 50f
    override var ballHeight = 50f

    var updateCalled = 0
    var reverseYVelocityCalled = 0
    var reverseXVelocityCalled = 0
    var setRandomXVelocityCalled = 0
    var clearObstacleYCalled = 0
    var clearObstacleXCalled = 0
    var resetCalled = 0

    override fun update(fps: Long) {
        updateCalled++
    }

    override fun reverseYVelocity() {
        reverseYVelocityCalled++
    }

    override fun reverseXVelocity() {
        reverseXVelocityCalled++
    }

    override fun setRandomXVelocity() {
        setRandomXVelocityCalled++
    }

    override fun clearObstacleY(y: Float) {
        clearObstacleYCalled++
    }

    override fun clearObstacleX(x: Float) {
        clearObstacleXCalled++
    }

    override fun reset(x: Int, y: Int) {
        resetCalled++
    }
}