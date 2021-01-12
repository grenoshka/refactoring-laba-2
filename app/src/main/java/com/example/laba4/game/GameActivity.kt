package com.example.laba4.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.*
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.laba4.R
import java.io.IOException


class GameActivity : AppCompatActivity() {
    private lateinit var breakoutView:BreakoutView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_game)
        breakoutView = BreakoutView(this)
        setContentView(breakoutView)
    }
    internal class BreakoutView(context: Context) :
        SurfaceView(context),
        Runnable {
        // This is our thread
        var gameThread: Thread? = null

        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        var ourHolder: SurfaceHolder

        // A boolean which we will set and unset
        // when the game is running- or not.
        @Volatile
        var playing = false

        // Game is paused at the start
        var paused = true

        // A Canvas and a Paint object
        lateinit var canvas: Canvas
        var paint: Paint

        // This variable tracks the game frame rate
        var fps: Long = 0

        // This is used to help calculate the fps
        private var timeThisFrame: Long = 0

        // The size of the screen in pixels
        var screenX: Int
        var screenY: Int

        // The players paddle
        var paddle: Paddle

        // A ball
        var ball: Ball

        // Up to 200 bricks
        var bricks = arrayOfNulls<Brick>(200)
        var numBricks = 0

        // The score
        var score = 0

        // Lives
        var lives = 3
        fun createBricksAndRestart() {

            // Put the ball back to the start
            ball.reset(screenX, screenY)
            val brickWidth = screenX / 8
            val brickHeight = screenY / 20

            // Build a wall of bricks
            numBricks = 0
            for (column in 0..7) {
                for (row in 0..6) {
                    bricks[numBricks] = Brick(row, column, brickWidth, brickHeight)
                    numBricks++
                }
            }
            // if game over reset scores and lives
            if (lives == 0) {
                score = 0
                lives = 3
            }
        }

        override fun run() {
            while (playing) {
                // Capture the current time in milliseconds in startFrameTime
                val startFrameTime = System.currentTimeMillis()
                // Update the frame
                if (!paused) {
                    update()
                }
                // Draw the frame
                draw()
                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame
                }
            }
        }

        // Everything that needs to be updated goes in here
        // Movement, collision detection etc.
        fun update() {

            // Move the paddle if required
            paddle.update(fps)
            ball.update(fps)

            // Check for ball colliding with a brick
            for (i in 0 until numBricks) {
                if (bricks[i]!!.visibility) {
                    if (RectF.intersects(bricks[i]!!.rect, ball.rect)) {
                        bricks[i]!!.setInvisible()
                        ball.reverseYVelocity()
                        score = score + 10
                    }
                }
            }
            // Check for ball colliding with paddle
            if (RectF.intersects(paddle.rect, ball.rect)) {
                ball.setRandomXVelocity()
                ball.reverseYVelocity()
                ball.clearObstacleY(paddle.rect.top - 2)
            }
            // Bounce the ball back when it hits the bottom of screen
            if (ball.rect.bottom > screenY) {
                ball.reverseYVelocity()
                ball.clearObstacleY(screenY - 2.toFloat())

                // Lose a life
                lives--
                if (lives == 0) {
                    paused = true
                    createBricksAndRestart()
                }
            }

            // Bounce the ball back when it hits the top of screen
            if (ball.rect.top < 0) {
                ball.reverseYVelocity()
                ball.clearObstacleY(12f)
            }

            // If the ball hits left wall bounce
            if (ball.rect.left < 0) {
                ball.reverseXVelocity()
                ball.clearObstacleX(2f)
            }

            // If the ball hits right wall bounce
            if (ball.rect.right > screenX - 10) {
                ball.reverseXVelocity()
                ball.clearObstacleX(screenX - 22.toFloat())
            }

            // Pause if cleared screen
            if (score == numBricks * 10) {
                paused = true
                createBricksAndRestart()
            }
        }

        // Draw the newly updated scene
        @SuppressLint("UseCompatLoadingForDrawables")
        fun draw() {

            // Make sure our drawing surface is valid or we crash
            if (ourHolder.surface.isValid) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas()

                // Draw the background color
                //canvas.drawColor(Color.argb(255, 26, 128, 182))
                val drawable = resources.getDrawable(R.drawable.game_background,null)
                drawable.setBounds(left,top,right,bottom)
                drawable.draw(canvas)
                // Choose the brush color for drawing
                paint.color = Color.argb(255, 255, 255, 255)
                val paintPaddle = Paint()
                paintPaddle.color=Color.argb(255,174,205,232)

                // Draw the paddle
                canvas.drawRect(paddle.rect, paintPaddle)

                // Draw the ball
                canvas.drawRect(ball.rect, paint)

                // Change the brush color for drawing
                paint.color = Color.argb(255, 140, 100, 56)

                // Draw the bricks if visible
                for (i in 0 until numBricks) {
                    if (bricks[i]!!.visibility) {
                        canvas.drawRect(bricks[i]!!.rect, paint)
                    }
                }

                // Choose the brush color for drawing
                paint.color = Color.argb(255, 255, 255, 255)

                // Draw the score
                paint.textSize = 40f
                canvas.drawText("Score: $score   Lives: $lives", 10f, 50f, paint)

                // Has the player cleared the screen?
                if (score == numBricks * 10) {
                    paint.textSize = 90f
                    canvas.drawText("YOU HAVE WON!", 10f, screenY / 2.toFloat(), paint)
                }

                // Has the player lost?
                if (lives <= 0) {
                    paint.textSize = 90f
                    canvas.drawText("YOU HAVE LOST!", 10f, screenY / 2.toFloat(), paint)
                }

                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas)
            }
        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        fun pause() {
            playing = false
            try {
                gameThread!!.join()
            } catch (e: InterruptedException) {
                Log.e("Error:", "joining thread")
            }
        }

        // If SimpleGameEngine Activity is started then
        // start our thread.
        fun resume() {
            playing = true
            gameThread = Thread(this)
            gameThread!!.start()
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
            when (motionEvent.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    paused = false
                    if (motionEvent.x > screenX / 2) {
                        paddle.setMovementState(paddle.RIGHT)
                    } else {
                        paddle.setMovementState(paddle.LEFT)
                    }
                }
                MotionEvent.ACTION_UP -> paddle.setMovementState(paddle.STOPPED)
            }
            return true
        }

        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        init {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.

            // Initialize ourHolder and paint objects
            ourHolder = holder
            paint = Paint()

            // Get a Display object to access screen details
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            // Load the resolution into a Point object
            screenX = displayMetrics.widthPixels
            screenY = displayMetrics.heightPixels
            paddle = Paddle(screenX, screenY)

            // Create a ball
            ball = Ball(screenX, screenY)

            createBricksAndRestart()
        }
    }
    // This is the end of our BreakoutView inner class

    // This is the end of our BreakoutView inner class
    // This method executes when the player starts the game
    override fun onResume() {
        super.onResume()

        // Tell the gameView resume method to execute
        breakoutView.resume()
    }

    // This method executes when the player quits the game
    override fun onPause() {
        super.onPause()

        // Tell the gameView pause method to execute
        breakoutView.pause()
    }
}

