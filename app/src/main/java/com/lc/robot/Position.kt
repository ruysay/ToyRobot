package com.lc.robot

data class Position(val x: Int = 0, val y: Int = 0) {
    fun north() = Position(x, y + 1)
    fun east() = Position(x + 1, y)
    fun south() = Position(x, y - 1)
    fun west() = Position(x - 1, y)

    fun getCoordinate(): Pair<Int, Int> {
        return Pair(x , y)
    }
}