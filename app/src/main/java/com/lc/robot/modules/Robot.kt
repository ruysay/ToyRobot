package com.lc.robot.modules

class Robot(val facing: Direction, val position: Position = Position()) {
    fun move(table: Table): Robot {
        val next = when (facing) {
            Direction.NORTH -> Robot(facing, position.north())
            Direction.EAST -> Robot(facing, position.east())
            Direction.WEST -> Robot(facing, position.west())
            Direction.SOUTH -> Robot(facing, position.south())
        }
        return if (table.withinBoundaries(next.position)) next else this
    }

    fun left(): Robot = when (facing) {
        Direction.NORTH -> Robot(Direction.EAST, position)
        Direction.EAST -> Robot(Direction.SOUTH, position)
        Direction.SOUTH -> Robot(Direction.WEST, position)
        Direction.WEST -> Robot(Direction.NORTH, position)
    }

    fun right(): Robot = when (facing) {
        Direction.NORTH -> Robot(Direction.WEST, position)
        Direction.EAST -> Robot(Direction.NORTH, position)
        Direction.SOUTH -> Robot(Direction.EAST, position)
        Direction.WEST -> Robot(Direction.SOUTH, position)
    }
}