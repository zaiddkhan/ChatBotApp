package com.example.chatbotapp.adapter

import android.content.Context
import android.text.Layout
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotapp.R
import com.example.chatbotapp.modals.ChatModal

class ChatAdapter(val list:ArrayList<ChatModal>,val context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val userTv = itemView.findViewById<TextView>(R.id.idTVUser)
        fun bind(chat:ChatModal){
            userTv.text = chat.message
        }
    }
    class BotViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val botTv = itemView.findViewById<TextView>(R.id.idTVUser)
        fun bind(chat:ChatModal){
            botTv.text = chat.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
             0 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.user_message_layout,parent,false)
                return UserViewHolder(view)
            }
             1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.bot_message_layout,parent,false)
                return BotViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_message_layout, parent, false)
                return UserViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val modal = list.get(position)
        when(modal.sender){
             "user" -> {
                 (holder as UserViewHolder).bind(modal)
            }
            "bot" -> {
                (holder as BotViewHolder).bind(modal)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        when(list[position].sender){
            "user" ->
                return  0

            "bot" ->
                return 1

            else ->
                return -1
        }
    }

    override fun getItemCount(): Int {
      return list.size;
    }
}