package com.example.laba4.breakoutview

import com.example.laba4.game.IGameViewModel

class MockGameViewModel : IGameViewModel {
    var updatePointsMethodCalled = 0
    override fun updatePoints(newPoints: Int) {
        updatePointsMethodCalled++
    }
}