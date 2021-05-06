package com.example.dogsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.example.dogsapp.adapter.DogsAdapter
import com.example.dogsapp.model.DogsApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val imageList = ArrayList<DogsApi>()

    private lateinit var rvDogs: RecyclerView
    private lateinit var etDogsName:EditText
    private lateinit var btnSearch:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDogs = findViewById(R.id.rvDogs)
        etDogsName = findViewById(R.id.etDogsName)
        btnSearch = findViewById(R.id.btnSearch)

        //Setting up Staggered Layput manager

        rvDogs.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        btnSearch.setOnClickListener {
            //Get text from EditText
            var name = etDogsName.text.toString()

            //Call search Dog function
            searchDogs(name)
        }
    }

    private fun searchDogs(name: String) {
        imageList.clear()

        AndroidNetworking.initialize(this)
        //Call the URL to get the image list in json format
        //Pass name parameter in url (Concate string)
        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    //Iterate each item in the array to fetch each item
                    for (i in 0 until image.length()){
                        val list = image.get(i)
                        imageList.add(
                            DogsApi(list.toString())
                        )
                    }

                    rvDogs.adapter = DogsAdapter(this@MainActivity, imageList)

                }

                override fun onError(anError: ANError?) {

                }
            })
    }
}