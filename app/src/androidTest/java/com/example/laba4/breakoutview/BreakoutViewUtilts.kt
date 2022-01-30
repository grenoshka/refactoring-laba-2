package com.example.laba4.breakoutview

import android.content.Context
import android.graphics.RectF
import com.example.laba4.game.Ball
import com.example.laba4.game.Brick
import com.example.laba4.game.GameActivity
import com.example.laba4.game.Paddle


fun arrangeDefaultBall(
    dummyScreenX: Int = 1000,
    dummyScreenY: Int = 1000,
    rect: RectF = RectF(100f, 100f, 150f, 150f)
): Ball {
    val ball = Ball(dummyScreenX, dummyScreenY)
    ball.rect = rect
    return ball
}

fun arrangeBallIntersectingWithPaddle(
    paddleTop: Float, paddleLeft: Float
): Ball {
    val ball = Ball(0, 0)
    val top = paddleTop
    val left = paddleLeft
    val right = left + ball.ballWidth
    val bottom = top + ball.ballHeight
    ball.rect = RectF(left, top, right, bottom)
    return ball
}

fun arrangeArrayOfTwoVisibleBricks(width: Int = 100, height: Int = 100): Array<Brick?> {
    val firstBrick = Brick(1, 1, width, height)
    val secondBrick = Brick(1, 2, width, height)
    return listOf(firstBrick, secondBrick).toTypedArray()
}

fun arrangeDefaultPaddle(screenX: Int, screenY: Int): Paddle {
    return Paddle(screenX, screenY)
}

fun arrangeMockGameViewModel(): MockGameViewModel {
    return MockGameViewModel()
}

fun getExpectedRectWithClearedXObstacle(
    expectedLeft: Float,
    initialRectF: RectF,
    ballWidth: Float
): RectF {
    return RectF(
        expectedLeft,
        initialRectF.top,
        expectedLeft + ballWidth,
        initialRectF.bottom)
}

fun getExpectedRectWithClearedYObstacle(
    expectedBottom: Float,
    initialRectF: RectF,
    ballHeight: Float
): RectF {
    return RectF(
        initialRectF.left,
        expectedBottom - ballHeight,
        initialRectF.right,
        expectedBottom
    )
}

fun getExpectedRectOfUpdatedBall(
    initialRectF: RectF,
    xVelocity: Float,
    yVelocity: Float,
    ballWidth: Float,
    ballHeight: Float,
    fps: Long
): RectF {
    val left = initialRectF.left + xVelocity / fps
    val top = initialRectF.top + yVelocity / fps
    val right = left + ballWidth
    val bottom = top + ballHeight
    return RectF(left, top, right, bottom)
}

fun Ball.clone(): Ball {
    val newBall = Ball(1000,1000)
    newBall.ballHeight = this.ballHeight
    newBall.ballWidth = this.ballWidth
    newBall.rect = RectF(this.rect.left, this.rect.top, this.rect.right, this.rect.bottom)
    newBall.xVelocity = this.xVelocity
    newBall.yVelocity = this.yVelocity
    return newBall
}

fun makeFirstVisibleBrick() : Brick = Brick(1,1,100, 100)