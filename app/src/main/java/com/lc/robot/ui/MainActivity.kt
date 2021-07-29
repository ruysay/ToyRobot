package com.lc.robot.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lc.robot.R
import com.lc.robot.modules.CommandProcessor
import com.lc.robot.databinding.ActivityMainBinding
import com.lc.robot.modules.Direction
import com.lc.robot.modules.Position
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var command: String? = null

    //Format of PLACE command: [PLACE][space][0-9],[0-9],[space][NORTH|EAST|SOUTH|WEST]
    private val placeRegex =
        Regex("(PLACE)[ ]([0-9]),([0-9]),[ ](NORTH|EAST|SOUTH|WEST)")
    private val processor = CommandProcessor()

    private val adapter by lazy {
        LogsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.placeBtn.setOnClickListener(clickListener)
        binding.leftBtn.setOnClickListener(clickListener)
        binding.rightBtn.setOnClickListener(clickListener)
        binding.moveBtn.setOnClickListener(clickListener)
        binding.logRecyclerView.adapter = adapter
        binding.logRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view?.id) {
            R.id.place_btn -> {
                if (binding.placeCommand.text.isBlank()) {
                    command = binding.placeCommand.hint as String
                } else {
                    command = binding.placeCommand.editableText.toString().uppercase()
                    if (command?.matches(placeRegex) == true) {
                        binding.placeCommand.editableText.toString()
                    } else {
                        adapter.addLog("Error: incorrect command or wrong format")
                        command = null
                    }
                }
            }
            R.id.move_btn -> {
                command = "MOVE"
            }
            R.id.left_btn -> {
                command = "LEFT"
            }
            R.id.right_btn -> {
                command = "RIGHT"
            }
        }
        handleCommand(command)
    }

    private fun handleCommand(command: String?) {
        if (!command.isNullOrEmpty()) {
            when (command) {
                "MOVE" -> processor.moveRobot()
                "RIGHT" -> processor.right()
                "LEFT" -> processor.left()
                else -> {
                    //clear and set logs
                    adapter.setLogs(listOf())
                    val placeCommands = command.substring(command.indexOf(" ") + 1).split(",")
                    val x = placeCommands[0].trim().toInt()
                    val y = placeCommands[1].trim().toInt()
                    //check if coordinate is in range
                    if (x > 4 || y > 4) {
                        adapter.addLog("Error: Out of range")
                        return
                    }
                    processor.place(
                        Direction.valueOf(placeCommands[2].trim()),
                        Position(placeCommands[0].toInt(), placeCommands[1].toInt())
                    )
                }
            }
            //get current robot position and update log
            processor.check()?.let {
                Timber.d("checkRobot: ${it.position}, ${it.facing}")
                adapter.addLog("${it.position.x} , ${it.position.y}")
            }
            //scroll down to the bottom of the log
            val position = if (adapter.itemCount < 0) 0 else adapter.itemCount - 1
            binding.logRecyclerView.scrollToPosition(position)
        }
    }
}