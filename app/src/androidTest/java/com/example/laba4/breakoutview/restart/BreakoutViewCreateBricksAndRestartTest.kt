package com.example.laba4.breakoutview.restart

import android.hardware.SensorEvent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.laba4.breakoutview.FakeGameViewModel
import com.example.laba4.game.Brick
import com.example.laba4.game.GameActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.IndexOutOfBoundsException

@RunWith(AndroidJUnit4::class)
class BreakoutViewCreateBricksAndRestartTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    // Структурированное базисное тестирование + притянутые за уши потоки данных

    @Test
    fun Restart_Default_Created56Bricks() {
        val breakoutView = arrangeBreakoutView()

        breakoutView.createBricksAndRestart()

        assertEquals(56, breakoutView.bricks.size)
    }

    // Классы хороших данных
    @Test
    fun Restart_Score20Lives0_Score0Lives3() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.score = 20
        breakoutView.lives = 0

        breakoutView.createBricksAndRestart()

        assertEquals(0, breakoutView.score)
        assertEquals(3, breakoutView.lives)
    }

    @Test
    fun Restart_Score20Lives1_Score20Lives1() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.score = 20
        breakoutView.lives = 1

        breakoutView.createBricksAndRestart()

        assertEquals(20, breakoutView.score)
        assertEquals(1, breakoutView.lives)
    }

    // Анализ граничных значений, классы эквивалетности

    @Test
    fun Restart_Score20LivesMinus100_Score0Lives3() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.score = 20
        breakoutView.lives = -100

        breakoutView.createBricksAndRestart()

        assertEquals(0, breakoutView.score)
        assertEquals(3, breakoutView.lives)
    }

    @Test
    fun Restart_Score20LivesMinus1_Score0Lives3() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.score = 20
        breakoutView.lives = -1

        breakoutView.createBricksAndRestart()

        assertEquals(0, breakoutView.score)
        assertEquals(3, breakoutView.lives)
    }

    @Test
    fun Restart_Score20Lives3_Score20Lives3() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.score = 20
        breakoutView.lives = 3

        breakoutView.createBricksAndRestart()

        assertEquals(0, breakoutView.score)
        assertEquals(3, breakoutView.lives)
    }

    //Классы плохих данных

    @Test(expected = RuntimeException::class)
    fun Restart_ScreenXMinu1ScreenYMinus1_Exception() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.screenX = -1
        breakoutView.screenY = -1

        breakoutView.createBricksAndRestart()
    }

    @Test(expected = RuntimeException::class)
    fun Restart_ScreenXMaxValueScreenYMaxValue_Exception() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.screenX = Int.MAX_VALUE
        breakoutView.screenY = Int.MAX_VALUE

        breakoutView.createBricksAndRestart()
    }

    @Test(expected = RuntimeException::class)
    fun Restart_ScoreMinus100_Exception() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.score = -100

        breakoutView.createBricksAndRestart()
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun Restart_BricksNullsSize10_Exception() {
        val breakoutView = arrangeBreakoutView()
        breakoutView.bricks = arrayOfNulls(10)

        breakoutView.createBricksAndRestart()
    }

    @Test
    fun Restart_IDK_BallIsReset(){
        val breakoutView = arrangeBreakoutView()
        val mockBall = MockBall()
        breakoutView.ball = mockBall

        breakoutView.createBricksAndRestart()

        assertEquals(1, mockBall.resetCalled)
    }

    @Test
    fun Restart_IDK_PaddleIsReset(){
        val breakoutView = arrangeBreakoutView()
        val mockPaddle = MockPaddle(breakoutView.screenX, breakoutView.screenY)
        breakoutView.paddle = mockPaddle

        breakoutView.createBricksAndRestart()

        assertEquals(1, mockPaddle.resetCalled)
    }

    private fun arrangeBreakoutView(): GameActivity.BreakoutView {
        val breakoutView = GameActivity.BreakoutView(context)
        breakoutView.fps = 60
        breakoutView.gameViewModel = FakeGameViewModel()
        return breakoutView
    }
}