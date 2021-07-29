package com.lc.robot.controller

import com.lc.robot.Position
import com.lc.robot.Robot
import com.lc.robot.Table
import com.lc.robot.direction.DirectionEnum
import timber.log.Timber

class CommandProcessor(private val table: Table = Table()) {
    private val size = 5

    private var robot: Robot? = null

    private var field = Array(size) {IntArray(size)}

    fun place(facing: DirectionEnum, position: Position) {
        Timber.d("placing robot $facing, $position")
        this.robot = Robot(facing, position)
    }

    fun place(position: Position) {
        Timber.d("placing object at $position")
        this.field[position.x][position.y] = 1
    }

    fun right() {
        Timber.d("turning right")
        this.robot = this.robot?.right()
    }

    fun left() {
        Timber.d("turning left")
        this.robot = this.robot?.left()
    }

    fun moveRobot() {
        Timber.d("move forward")
        this.robot = this.robot?.move(table)
    }

    fun field(): Array<IntArray>  {
        return this.field
    }

    fun check(): Robot? {
        return this.robot
    }
}