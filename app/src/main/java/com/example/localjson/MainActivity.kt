package com.example.localjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    private lateinit var Rc: RecyclerView
    private lateinit var Fullimage: ImageView
    private lateinit var ET: EditText
    private lateinit var b: Button

    private lateinit var photos: ArrayList<photoClass>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photos = ArrayList()
        Rc = findViewById(R.id.rv)
        Fullimage = findViewById(R.id.fullImage)

        b = findViewById(R.id.b)
        Rc.adapter = adapterph(this, photos)
        Rc.layoutManager = LinearLayoutManager(this)
        b.setOnClickListener {
            displayData()
        }
        Fullimage.setOnClickListener {
            closeImg()
        }
    }

    fun displayData() {

        b.isVisible = false
        var jsonfile: InputStream = assets.open("data.json")

        var json = jsonfile.bufferedReader().use { it.readText() }
        var jsonobj = JSONArray(json)
        for (i in 0 until jsonobj.length()) {
            var obj = jsonobj.getJSONObject(i)
            photos.add(photoClass(obj.getString("url")))
        }
        Rc.adapter?.notifyDataSetChanged()
    }

    fun fullImage(url: String) {
        Glide.with(this).load(url).into(Fullimage)
        Fullimage.isVisible = true
        Rc.isVisible = false
        b.isVisible = false

    }

    fun closeImg() {
        Fullimage.isVisible = false
        Rc.isVisible = true


    }
}

