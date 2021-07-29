package com.lc.robot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class LogsAdapter() : RecyclerView.Adapter<LogsAdapter.LogViewHolder>() {

    private val logs = mutableListOf<String>()

    fun setLogs(logs: List<String>) {
        this.logs.clear()
        this.logs.addAll(logs)
        notifyDataSetChanged()
    }

    fun addLog(log: String) {
        this.logs.add(log)
        Timber.d("checkRobot - addLog: ${logs.lastIndex}, $log")
        notifyItemInserted(logs.lastIndex)
    }

    class LogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val logMsg = view.findViewById<TextView>(R.id.log_msg)
        fun onBind(log: String, position: Int) {
            logMsg.text = log
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        return LogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_log, parent, false))
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.onBind(logs[position], position)
    }

    override fun getItemCount(): Int {
        return logs.size
    }
}