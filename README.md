


# Toy robot app

The app is a simple toy robot app in kotlin that takes commands like PLACE, MOVE, LEFT and RIGHT for a virtual Robot and positions it on a plane of 5x5 units. Commands are not case-sensitive. hich process 

# Commands

- MOVE will make the robot go forward one unit.
- LEFT or RIGHT will rotate the robot left or right, respectively.
- PLACE X,Y, DIRECTION will place the robot on the plane where X's and Y's allowed range is 0-4 (5 units total, zero indexed)
- Directions allowed are "NORTH", "SOUTH, "EAST" or "WEST".

                                                      
   
# Command Format
Below is the format check of "PLACE" command.

    "(PLACE)[ ]([0-9]),([0-9]),[ ](NORTH|EAST|SOUTH|WEST)"

    


# Example Commands and Outputs
	Command: PLACE 0,0, SOUTH
	Output: 0,0
	Command: MOVE
	Output: 0,1
	Command: LEFT
	Output: 0,1
	Command: MOVE
	Output: 1,1
	Command: LEFT
	Output: 1,1
	Command: MOVE
	Output: 1,0
	Command: BLAH
	Output: error
	Command: test
	Output: error
	Command: PLACE 0,4, SOUTH //There is a space between "," after "y" and [DIRECTION]
	Output: 0,4
	Command: MOVE
	Output: 0,4
	Command: PLACE, 20, 49, SOUTH
	Output: error


