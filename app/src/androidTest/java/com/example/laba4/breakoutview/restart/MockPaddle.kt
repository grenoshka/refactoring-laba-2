package com.example.laba4.breakoutview.restart

import android.graphics.RectF
import com.example.laba4.game.IPaddle


class MockPaddle(override val screenX: Int, override val screenY: Int) : IPaddle {
    override val rect: RectF

    override val length = 250f
    override val height = 50f

    override var x: Float = 0.0f

    override val y: Float

    override val paddleSpeed: Float

    // Which ways can the paddle move
    override val STOPPED = 0
    override val LEFT = 1
    override val RIGHT = 2

    // Is the paddle moving and in which direction
    override var paddleMoving = STOPPED

    var resetCalled = 0
    var setMovementStateCalled = 0
    var update = 0

    override fun reset() {
        resetCalled++
    }

    override fun setMovementState(state: Int) {
        setMovementStateCalled++
    }

    override fun update(fps: Long) {
        update++
    }

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