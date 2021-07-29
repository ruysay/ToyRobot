package com.lc.robot

data class Table(val width: Int = 4, val height: Int = 4) {

    fun withinBoundaries(position: Position) = position.x in 0..width && position.y in 0..height

}