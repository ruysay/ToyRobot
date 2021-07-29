package com.lc.robot.modules

import timber.log.Timber

class CommandProcessor(private val table: Table = Table()) {

    private var robot: Robot? = null
    fun place(facing: Direction, position: Position) {
        Timber.d("checkRobot placing robot $facing, $position")
        this.robot = Robot(facing, position)
    }

    fun right() {
        Timber.d("checkRobot right")
        this.robot = this.robot?.right()
    }

    fun left() {
        Timber.d("checkRobot left")
        this.robot = this.robot?.left()
    }

    fun moveRobot() {
        Timber.d("checkRobot move")
        this.robot = this.robot?.move(table)
    }

    fun check(): Robot? {
        return this.robot
    }
}