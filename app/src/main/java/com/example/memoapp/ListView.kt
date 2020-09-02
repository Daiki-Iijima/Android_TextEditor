package com.example.memoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewpractice.Data

class ListView : AppCompatActivity() {

    lateinit var mAdapter: CustomAdapter
    lateinit var mDataList: ArrayList<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //  ボタンイベント
        val createMemoBtn = findViewById<Button>(R.id.NewCreateBtn);
        createMemoBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("KEY", t.text);//第一引数key、第二引数渡したい値
            startActivity(intent)
        }

        // データの作成
        //  データ配列の
        mDataList = ArrayList<Data>();

//        val dog = Data("イヌ", { test("イヌ") })
//        val cat = Data("猫", { test("猫") })
//        val elephant = Data("ぞう", { test("ぞう") })
//        val horse = Data("うま", { test("うま") })
//        val lion = Data("ライオン", { test("ライオン") })
//        mDataList = arrayListOf(dog, cat, elephant, horse, lion)

        mDataList.add(Data("イヌ", { test("イヌ") }))

        // RecyclerViewの取得
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(this)

        // CustomAdapterの生成と設定
        mAdapter = CustomAdapter(mDataList)
        recyclerView.adapter = mAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    fun test(str: String)
    {
        Log.d("aa",str)
    }
}