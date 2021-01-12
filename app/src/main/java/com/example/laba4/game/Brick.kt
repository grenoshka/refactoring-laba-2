package com.example.laba4.game

import android.graphics.RectF


class Brick(row: Int, column: Int, width: Int, height: Int) {
    val rect: RectF
    var visibility = true
        private set

    fun setInvisible() {
        visibility = false
    }

    init {
        val padding = 1
        rect = RectF(
            (column * width + padding).toFloat(),
            (row * height + padding).toFloat(),
            (column * width + width - padding).toFloat(),
            (row * height + height - padding).toFloat()
        )
    }
}