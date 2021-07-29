package com.lc.robot

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lc.robot.controller.CommandProcessor
import com.lc.robot.databinding.ActivityMainBinding
import com.lc.robot.direction.DirectionEnum
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var command = ""
    private val placeRegex = Regex("(PLACE)[ \\t\\n\\x0b\\r\\f]([0-9]),([0-9]),[ \\t\\n\\x0b\\r\\f](NORTH|EAST|SOUTH|WEST)")
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
        when(view?.id) {
            R.id.place_btn -> {
                if(binding.placeCommand.text.isBlank()) {
                    command = binding.placeCommand.hint as String
                } else {
                    //TODO check
                    command = binding.placeCommand.editableText.toString()
                    if(command.matches(placeRegex)) {
                        binding.placeCommand.editableText.toString()
                    } else {
                        Timber.d("checkRobot - error placing: $command")
                        adapter.addLog("Error: incorrect command or wrong format")
                        return@OnClickListener
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
        when(command) {
            "MOVE" -> processor.moveRobot()
            "RIGHT" -> processor.right()
            "LEFT" -> processor.left()
            else -> {
                Timber.d("checkRobot placing - 0: $command")
                adapter.setLogs(listOf())
                val placeCommands = command.substring(command.indexOf(" ")+1).split(",")
                Timber.d("checkRobot placing - 1: $placeCommands")

                val x = placeCommands[0].trim().toInt()
                val y = placeCommands[1].trim().toInt()
                if(x > 4 || y > 4) {
                    adapter.addLog("Error: Out of range")
                    return@OnClickListener
                }
                processor.place(DirectionEnum.valueOf(placeCommands[2].trim()), Position(placeCommands[0].toInt(), placeCommands[1].toInt()))
            }
        }
        processor.check()?.let {
            Timber.d("checkRobot position: ${it.position}, ${it.facing}")
            adapter.addLog("${it.position.x} , ${it.position.y}")
        }
        binding.logRecyclerView.scrollTo(0,20*adapter.itemCount)
    }
}