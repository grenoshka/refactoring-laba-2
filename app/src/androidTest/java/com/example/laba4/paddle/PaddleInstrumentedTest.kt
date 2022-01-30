package com.example.laba4.paddle

import android.graphics.RectF
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.laba4.game.Paddle
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
class PaddleInstrumentedTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val screenX = context.resources.displayMetrics.widthPixels
    private val screenY = context.resources.displayMetrics.heightPixels

    // ------Структурированное базисное тестирование
    // ------Тестирование основанное на потоках данных (x)

    @Test
    fun Update_PaddleMovingLeftRectLeft500X500Fps50_X493(){
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(500f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 500f)

        paddle.update(50)

        assertEquals(493f, paddle.getPrivateProperty("x"))
    }

    @Test
    fun Update_PaddleMovingRightRectRight750X500_X507() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.RIGHT)
        paddle.setPrivateProperty("rect", RectF(500f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 500f)

        paddle.update(50)

        assertEquals(507f, paddle.getPrivateProperty("x"))
    }


    @Test
    fun Update_PaddleStoppedX500_X500() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.STOPPED)
        paddle.setPrivateProperty("rect", RectF(500f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 500f)

        paddle.update(50)

        assertEquals(500f, paddle.getPrivateProperty("x"))
    }

    // (x)------Структурированное базисное тестирование

    // ------Классы эквивалентности

    @Test
    fun Update_PaddleMovingLeftXMinus100RectLeftMinus100_XMinus100() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(-100f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", -100f)

        paddle.update(50)

        assertEquals(-100f, paddle.getPrivateProperty("x"))
    }


    @Test
    fun Update_PaddleMovingRightX500RectRightMoreScreenX_XMinus500() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.RIGHT)
        paddle.setPrivateProperty("rect", RectF(500f, 1900f, 100000f, 1950f))
        paddle.setPrivateProperty("x", 500f)

        paddle.update(50)

        assertEquals(500f, paddle.getPrivateProperty("x"))
    }

    // (x)------Классы эквивалентности

    // ------Анализ граничных условий (1)

    @Test
    fun Update_PaddleMovingLeftXMinus1RectLeftMinus1_XMinus1() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(-1f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", -1f)

        paddle.update(50)

        assertEquals(-1f, paddle.getPrivateProperty("x"))
    }

    @Test
    fun Update_PaddleMovingLeftX0RectLeft0_X0() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(0f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 0f)

        paddle.update(50)

        assertEquals(0f, paddle.getPrivateProperty("x"))
    }

    @Test
    fun Update_PaddleMovingLeftX1RectLeft1_X0() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(1f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 1f)

        paddle.update(50)

        assertEquals(0f, paddle.getPrivateProperty("x"))
    }

    // (x)------Анализ граничных условий (1)

    // ------Анализ граничных условий (2)

    @Test
    fun Update_PaddleMovingRightXScreenXMinus1RectRightScreenXMinus1_XScreenX() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.RIGHT)
        paddle.rect.right = screenX-1f
        paddle.setPrivateProperty("x", screenX - 1f)

        paddle.update(50)

        assertEquals(screenX, paddle.getPrivateProperty("x"))
    }

    @Test
    fun Update_PaddleMovingRightXScreenXRectRightScreenX_XScreenX() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.RIGHT)
        paddle.rect.right = screenX.toFloat()
        paddle.setPrivateProperty("x", screenX.toFloat())

        paddle.update(50)

        assertEquals(screenX.toFloat(), paddle.getPrivateProperty("x"))
    }

    @Test
    fun Update_PaddleMovingRightXScreenXPlus1RectRightScreenX_XScreenX() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.RIGHT)
        paddle.rect.right = screenX.toFloat() + 1f
        paddle.setPrivateProperty("x", screenX.toFloat() + 1f)

        paddle.update(50)

        assertEquals(screenX.toFloat(), paddle.getPrivateProperty("x"))
    }

    // (x)------Анализ граничных условий (2)


    @Test
    fun Update_PaddleMovingRightX0RectLeft0_X0() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(0f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 0f)

        paddle.update(50)

        assertEquals(0f, paddle.getPrivateProperty("x"))
    }

    @Test
    fun Update_PaddleMovingRightX1RectLeft1_X0() {
        val paddle = arrangePaddle()
        paddle.setPrivateProperty("paddleMoving", paddle.LEFT)
        paddle.setPrivateProperty("rect", RectF(1f, 1900f, 750f, 1950f))
        paddle.setPrivateProperty("x", 1f)

        paddle.update(50)

        assertEquals(0f, paddle.getPrivateProperty("x"))
    }

    // (x)------Тестирование основанное на потоках данных (x)

    // ------ Классы хороших данных (state)
    @Test
    fun SetMovementState_StateLeft_PaddleMovingLeft() {
        val paddle = arrangePaddle()

        paddle.setMovementState(paddle.LEFT)

        assertEquals(paddle.LEFT, paddle.getPrivateProperty("paddleMoving"))
    }

    // (x)------ Классы хороших данных (state)

    // ------ Классы плохих данных (state) + угадывание ошибок
    @Test(expected = RuntimeException::class)
    fun SetMovementState_StateMinus100_Exception() {
        val paddle = arrangePaddle()

        paddle.setMovementState(-100)
    }

    @Test(expected = RuntimeException::class)
    fun SetMovementState_State100_Exception() {
        val paddle = arrangePaddle()

        paddle.setMovementState(100)
    }
    // (x)------ Классы плохих данных (state)

    // Ну и еще немного плохих данных (fps, screenX & screenY)
    @Test(expected = RuntimeException::class)
    fun Update_FpsMinus100_Exception() {
        val paddle = arrangePaddle()

        paddle.update(-100)
    }


    private fun arrangePaddle(): Paddle = Paddle(screenX, screenY)

    fun <T : Any> T.getPrivateProperty(variableName: String): Any? {
        return javaClass.getDeclaredField(variableName).let { field ->
            field.isAccessible = true
            return@let field.get(this)
        }
    }

    fun <T : Any> T.setPrivateProperty(variableName: String, data: Any) {
        javaClass.getDeclaredField(variableName).let { field ->
            field.isAccessible = true
            field.set(this, data)
            return@let field.get(this)
        }
    }
}