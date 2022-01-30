package com.example.laba4.breakoutview.update

import android.graphics.RectF
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.laba4.breakoutview.*
import com.example.laba4.game.GameActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.IndexOutOfBoundsException

@RunWith(AndroidJUnit4::class)
class BreakoutViewUpdateInstrumentedTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    /*
    Структурное базисное тестирование
    (ну и неполное тестирование)
     */

    @Test
    fun Update_OneBrickVisibleIntersectsWithBall_BrickBecomesInvisible() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        val bricks = arrangeArrayOfTwoVisibleBricks()
        val initialScore = breakoutView.score
        val initialBallYVelocity = ball.yVelocity
        bricks[1]?.setInvisible()
        breakoutView.bricks = bricks
        breakoutView.ball = ball

        breakoutView.update()

        assertEquals(false, breakoutView.bricks[0]?.visibility)
        assertEquals(initialScore + 20, breakoutView.score)
        assertEquals(-initialBallYVelocity, ball.yVelocity)
    }

    @Test
    fun Update_BricksVisibleNotIntersectWithBall_BricksStayVisible() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall(rect = RectF(600f, 600f, 650f, 650f))
        val bricks = arrangeArrayOfTwoVisibleBricks()
        breakoutView.bricks = bricks
        breakoutView.ball = ball

        breakoutView.update()

        assertEquals(true, breakoutView.bricks[0]?.visibility)
        assertEquals(true, breakoutView.bricks[1]?.visibility)
    }

    @Test
    fun Update_BricksVisibleNotIntersectWithBall_BallYVelocityStaysSame() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall(rect = RectF(600f, 600f, 650f, 650f))
        val initialBallYVelocity = ball.yVelocity
        val bricks = arrangeArrayOfTwoVisibleBricks()
        breakoutView.bricks = bricks
        breakoutView.ball = ball

        breakoutView.update()

        assertEquals(initialBallYVelocity, ball.yVelocity)
    }

    @Test
    fun Update_BricksVisibleNotIntersectWithBall_ScoreStaysSame() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall(rect = RectF(600f, 600f, 650f, 650f))
        val bricks = arrangeArrayOfTwoVisibleBricks()
        val initialScore = breakoutView.score
        breakoutView.bricks = bricks
        breakoutView.ball = ball

        breakoutView.update()

        assertEquals(initialScore, breakoutView.score)
    }

    @Test
    fun Update_AllBricksAreInvisible_BricksStayInvisible() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall(rect = RectF(600f, 600f, 650f, 650f))
        val bricks = arrangeArrayOfTwoVisibleBricks()
        bricks[0]?.setInvisible()
        bricks[1]?.setInvisible()
        breakoutView.bricks = bricks
        breakoutView.ball = ball

        breakoutView.update()

        assertEquals(false, breakoutView.bricks[0]?.visibility)
        assertEquals(false, breakoutView.bricks[1]?.visibility)
    }


    @Test
    fun Update_PaddleIntersectsWithBallFps40_BallYVelocityReversedBallMovedTo2AbovePaddle() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.fps = 40
        val paddle = arrangeDefaultPaddle(breakoutView.screenX, breakoutView.screenY)
        val ball = arrangeBallIntersectingWithPaddle(paddle.rect.top, paddle.rect.left)
        val initialBallYVelocity = ball.yVelocity
        breakoutView.paddle = paddle
        breakoutView.ball = ball

        breakoutView.update()

        val exceptedBallRect = RectF(420f, 1926f, 470f, 1976f)
        assertEquals(exceptedBallRect, ball.rect)
        assertEquals(-initialBallYVelocity, ball.yVelocity)
    }

    // Разделение на классы эквивалентности
    // прим. рассматриваем все случаи когда мяч выходит за границу экрана (или находится на ней),
    // а потом 1 тест на то что он в пределах экрана


    @Test
    fun Update_BallRectBottomMoreThanScreenY_LifeLostBallYVelocityReversedBallMoved2AboveScreenY() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        val screenX = breakoutView.screenX.toFloat()
        val screenY = breakoutView.screenY.toFloat()
        ball.rect = RectF(screenX, screenY, screenX + ball.ballWidth, screenY + ball.ballHeight)
        breakoutView.ball = ball
        val initialLives = breakoutView.lives
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(1078f, 1976f, 1128f, 2026f)
        assertEquals(exceptedBallRect, ball.rect)
        assertEquals(-initialBallYVelocity, ball.yVelocity)
        assertEquals(initialLives - 1, breakoutView.lives)
    }

    @Test
    fun Update_BallRectBottomMoreThanScreenYLivesEquals1_GamePausedAndLostViewModelUpdatePointsCalledOnce() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        val screenX = breakoutView.screenX.toFloat()
        val screenY = breakoutView.screenY.toFloat()
        ball.rect = RectF(screenX, screenY, screenX + ball.ballWidth, screenY + ball.ballHeight)
        breakoutView.ball = ball
        breakoutView.lives = 1
        val mockGameViewModel = arrangeMockGameViewModel()
        breakoutView.gameViewModel = mockGameViewModel

        breakoutView.update()

        assertEquals(true, breakoutView.paused)
        assertEquals(true, breakoutView.lost)
        assertEquals(1, mockGameViewModel.updatePointsMethodCalled)
    }

    //Классы хороших данных

    @Test
    fun Update_BallRectInMiddleOfScreenOnlyFirstBrickVisible_BallIsUpdated() {
        val breakoutView = arrangeBreakoutView()
        val screenX = breakoutView.screenX.toFloat()
        val screenY = breakoutView.screenY.toFloat()
        val ball = arrangeDefaultBall()
        ball.rect = RectF(
            screenX / 2,
            screenY / 2,
            screenX / 2 + ball.ballWidth,
            screenY / 2 + ball.ballHeight
        )
        val initialBall = ball.clone()
        breakoutView.ball = ball
        breakoutView.bricks.map { it?.setInvisible() }
        breakoutView.bricks[0] = makeFirstVisibleBrick()

        breakoutView.update()

        val expectedUpdatedBallRect = getExpectedRectOfUpdatedBall(
            initialBall.rect,
            initialBall.xVelocity,
            initialBall.yVelocity,
            initialBall.ballWidth,
            initialBall.ballHeight,
            breakoutView.fps
        )
        assertEquals(expectedUpdatedBallRect, ball.rect)
    }

    // тестирование основанное на потоках данных-----------------------------------------------------------------
    // прим. yVelocity может либо меняться дважды, либо меняться единожды в данном методе,
    // рассматирваем все возможные случаи когда она меняется единожды и дважды

    @Test
    fun Update_BallRectTopLessThan0BallIntersectsWithBricks_BallYVelocitySameBallMovedTo2Below0() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        ball.rect = RectF(-5f, -5f, -5f + ball.ballWidth, -5f + ball.ballHeight)
        breakoutView.ball = ball
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(52f, 2f, 102f, 52f)
        assertEquals(exceptedBallRect, ball.rect)
        assertEquals(initialBallYVelocity, ball.yVelocity)
    }

    @Test
    fun Update_BallRectTopLess0AndBallNotIntersectWithBricks_BallYVelocityReversedBallMovedTo2Below0() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        ball.rect = RectF(-5f, -5f, -5f + ball.ballWidth, -5f + ball.ballHeight)
        breakoutView.ball = ball
        breakoutView.bricks[0]?.setInvisible()
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(52f, 2f, 102f, 52f)
        assertEquals(exceptedBallRect, ball.rect)
        assertEquals(-initialBallYVelocity, ball.yVelocity)
    }

    @Test
    fun Update_BallRectLeftLess0BallIntersectsWithBricks_BallYVelocitySameBallMovedTo2FromLeft() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        ball.rect = RectF(-5f, 5f, -5f + ball.ballWidth, 5f + ball.ballHeight)
        breakoutView.ball = ball
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(52f,2f, 102f, 52f)
        assertEquals(exceptedBallRect, breakoutView.ball.rect)
        assertEquals(initialBallYVelocity, breakoutView.ball.yVelocity)
    }

    @Test
    fun Update_BallRectLeftLess0BallNotIntersectWithBricks_BallYVelocityRevesedBallMovedTo2FromLeft() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        ball.rect = RectF(-5f, 5f, -5f + ball.ballWidth, 5f + ball.ballHeight)
        breakoutView.ball = ball
        breakoutView.bricks[0]?.setInvisible()
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(52f,2f, 102f, 52f)
        assertEquals(exceptedBallRect, breakoutView.ball.rect)
        assertEquals(-initialBallYVelocity, breakoutView.ball.yVelocity)
    }

    @Test
    fun Update_BallRectRightMoreScreenXBallIntersectsWithBrickFps40_BallYVelocitySameBallMovedTo2FromRight() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.fps = 40
        val ball = arrangeDefaultBall()
        val screenX = breakoutView.screenX.toFloat()
        ball.rect = RectF(screenX, 100f, screenX + ball.ballWidth, 100f + ball.ballHeight)
        breakoutView.ball = ball
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(1078f, 90f, 1128f, 140f)
        assertEquals(exceptedBallRect, breakoutView.ball.rect)
        assertEquals(initialBallYVelocity, breakoutView.ball.yVelocity)
    }

    @Test
    fun Update_BallRectRightMoreScreenXAndBallNotIntersectWithBricks_BallYVelocityRevesedBallMovedTo2FromRight() {
        val breakoutView = arrangeBreakoutView()
        val ball = arrangeDefaultBall()
        val screenX = breakoutView.screenX.toFloat()
        ball.rect = RectF(screenX, 5f, screenX + ball.ballWidth, 5f + ball.ballHeight)
        breakoutView.ball = ball
        breakoutView.bricks.map { it?.setInvisible() }
        val initialBallYVelocity = ball.yVelocity

        breakoutView.update()

        val exceptedBallRect = RectF(1078f, 2f, 1128f, 52f)
        assertEquals(exceptedBallRect, breakoutView.ball.rect)
        assertEquals(-initialBallYVelocity, breakoutView.ball.yVelocity)
    }
    // тестирование основанное на потоках данных-----------------------------------------------------------------

    //анализ граничных условий -----------------------------------------------------------------------------------

    @Test
    fun Update_ScoreEqualsBricksSizeOnlyFirstBrickVisible_ViewModelIsCalledOnce() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.bricks.map { it?.setInvisible() }
        breakoutView.bricks[0] = makeFirstVisibleBrick()
        breakoutView.score = breakoutView.bricks.size * 20
        val mockGameViewModel = arrangeMockGameViewModel()
        breakoutView.gameViewModel = mockGameViewModel

        breakoutView.update()

        assertEquals(1, mockGameViewModel.updatePointsMethodCalled)
    }

    @Test
    fun Update_ScoreEqualsBricksSizeMinusOneOnlyFirstBrickVisible_ViewModelIsNotCalled() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.bricks.map { it?.setInvisible() }
        breakoutView.bricks[0] = makeFirstVisibleBrick()
        breakoutView.score = breakoutView.bricks.size * 20 - 1
        val mockGameViewModel = arrangeMockGameViewModel()
        breakoutView.gameViewModel = mockGameViewModel

        breakoutView.update()

        assertEquals(0, mockGameViewModel.updatePointsMethodCalled)
    }

    @Test
    fun Update_ScoreEqualsBricksSizePlusOneOnlyFirstBrickVisible_ViewModelCalledOnce() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.bricks.map { it?.setInvisible() }
        breakoutView.bricks[0] = makeFirstVisibleBrick()
        breakoutView.score = breakoutView.bricks.size * 20 + 1
        val mockGameViewModel = arrangeMockGameViewModel()
        breakoutView.gameViewModel = mockGameViewModel

        breakoutView.update()

        assertEquals(1, mockGameViewModel.updatePointsMethodCalled)
    }

    //анализ граничных условий -----------------------------------------------------------------------------------

    @Test
    fun Update_Score20AndOnlyFirstBrickVisible_ViewModelIsNotCalled() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.bricks.map { it?.setInvisible() }
        breakoutView.bricks[0] = makeFirstVisibleBrick()
        breakoutView.score = 20
        val mockGameViewModel = arrangeMockGameViewModel()
        breakoutView.gameViewModel = mockGameViewModel

        breakoutView.update()

        assertEquals(0, mockGameViewModel.updatePointsMethodCalled)
    }

    //Класс плохих данных
    //угадывание ошибок
    @Test(expected = IndexOutOfBoundsException::class)
    fun Update_BricksArrayIsEmpty_IndexOutOfBoundsException(){
        val breakoutView = arrangeBreakoutView()
        breakoutView.bricks = emptyArray()

        breakoutView.update()
    }

    @Test
    fun Update_PausedFalseScoreEqualsBrickSizePlus100_GameIsPausedViewModelCalledOnce() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.paused = false
        breakoutView.score = breakoutView.bricks.size + 100
        val mockGameViewModel = arrangeMockGameViewModel()
        breakoutView.gameViewModel = mockGameViewModel

        breakoutView.update()

        assertEquals(breakoutView.paused, true)
        assertEquals(mockGameViewModel.updatePointsMethodCalled, 1)
    }

    private fun arrangeBreakoutView(): GameActivity.BreakoutView {
        val breakoutView = GameActivity.BreakoutView(context)
        breakoutView.fps = 60
        breakoutView.gameViewModel = FakeGameViewModel()
        return breakoutView
    }
}