package com.example.chatbotapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotapp.adapter.ChatAdapter
import com.example.chatbotapp.api.Retrofitapi
import com.example.chatbotapp.modals.ChatModal
import com.example.chatbotapp.modals.MsgModal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.internal.notify
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var recView :RecyclerView
    lateinit var editText:EditText
    lateinit var fab:ImageButton
    private val BOT_KEY = "bot"
    private val USER_KEY = "user"
    private lateinit var list : ArrayList<ChatModal>
    private lateinit var adapter : ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = findViewById(R.id.idRVChats)
        editText = findViewById(R.id.idEdtMessage)
        fab = findViewById(R.id.idIBSend)
        list = ArrayList()
        adapter = ChatAdapter(list,this)
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = adapter
        fab.setOnClickListener {
            if(!editText.text.isNotEmpty()){
                Toast.makeText(this, "enter message", Toast.LENGTH_SHORT).show()
            }
            val message = editText.text.toString()
            getResponse(message)
            editText.setText("")
        }
    }

    private fun getResponse( message:String){
        val chat = ChatModal(message,USER_KEY)
        list.add(chat)
        val url="http://api.brainshop.ai/get?bid=167025&key=R6vi55Yt1h6F9bau&uid=[uid]&msg=$message"
        val BASE_URL = "http://api.brainshop.ai/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create<Retrofitapi>(Retrofitapi::class.java)
        val response = api.getMessage(url)
        if(response.isSuccessful){
            val message :MsgModal= response.body()!!
            list.add(ChatModal(message.cnt,BOT_KEY))
            adapter.notifyDataSetChanged()
        }else{
            list.add(ChatModal("Please revert your question",BOT_KEY))
            adapter.notifyDataSetChanged()
        }


    }
}