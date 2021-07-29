package com.lc.robot

import com.lc.robot.direction.DirectionEnum

class Robot(val facing: DirectionEnum, val position: Position = Position()) {
    fun move(table: Table): Robot? {
        val next = when (facing) {
            DirectionEnum.NORTH -> Robot(facing, position.north())
            DirectionEnum.EAST -> Robot(facing, position.east())
            DirectionEnum.WEST -> Robot(facing, position.west())
            DirectionEnum.SOUTH -> Robot(facing, position.south())
        }

        return if (table.withinBoundaries(next.position)) next else this
    }

    fun right(): Robot = when (facing) {
        DirectionEnum.NORTH -> Robot(DirectionEnum.EAST, position)
        DirectionEnum.EAST -> Robot(DirectionEnum.SOUTH, position)
        DirectionEnum.SOUTH -> Robot(DirectionEnum.WEST, position)
        DirectionEnum.WEST -> Robot(DirectionEnum.NORTH, position)
    }

    fun left(): Robot = when (facing) {
        DirectionEnum.NORTH -> Robot(DirectionEnum.EAST, position)
        DirectionEnum.EAST -> Robot(DirectionEnum.NORTH, position)
        DirectionEnum.SOUTH -> Robot(DirectionEnum.WEST, position)
        DirectionEnum.WEST -> Robot(DirectionEnum.SOUTH, position)
    }
}