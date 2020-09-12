package com.example.memoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewpractice.Data
import java.io.BufferedReader
import java.io.File

class ListView : AppCompatActivity() {

    lateinit var mAdapter: CustomAdapter
    val fileName = "contents.txt"

    override fun onCreate(savedInstanceState: Bundle?) {

        //region システム初期化
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        //endregion

        //  データ保存、読み込み用のマネージャーの初期化
        val dataManager = DataManager()

        Log.d("", "遷移しました")

        //region ボタンイベント
        val createMemoBtn = findViewById<Button>(R.id.NewCreateBtn);

        //  ボタンクリック時イベント設定
        createMemoBtn.setOnClickListener {
            //  画面遷移
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val dataDeleteBtn = findViewById<Button>(R.id.DataDeleteBtn);

        //  ボタンクリック時イベント設定
        dataDeleteBtn.setOnClickListener {
            dataManager.deleteFile(this, fileName)
            var mDataList = ArrayList<Data>()
            UpdateListView(mDataList)
        }
        //endregion


        //  入力用アクティブティからのデータの受け取り & ヌルチェック
        var titleText = intent.getStringExtra("KEY_TITLE") ?: return
        var contentText = intent.getStringExtra("KEY_CONTENT") ?: return

        Log.d("受信した値", titleText)
        //  改行文字を追加してデータを分割
        titleText += ":"+contentText+"\n"
        //  内部ストレージにデータを保存
        dataManager.saveFile(this, fileName, titleText)

        //  内部ストレージのデータを読み込み
        val readText = dataManager.readFile(this, fileName).readText()
        val dataList = readText.split("\n")

        Log.d("LoadFile", readText)

        var mDataList = ArrayList<Data>();
        for (data in dataList) {
            if (data == "") continue

            //  データをタイトルと中身に分割
            val splitData = data.split(":")
            val title = splitData[0]
            val content = splitData[1]

            mDataList.add(Data(title) {  //  クリック時のイベント
                //  画面遷移
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("KEY_TITLE", title);//第一引数key、第二引数渡したい値
                intent.putExtra("KEY_CONTENT", content);//第一引数key、第二引数渡したい値
                startActivity(intent)
            })
        }

        UpdateListView(mDataList)

    }

    //  レイアウト更新S
    private fun UpdateListView(dataList: ArrayList<Data>)
    {
        // RecyclerViewの取得
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(this)

        // CustomAdapterの生成と設定
        mAdapter = CustomAdapter(dataList)
        recyclerView.adapter = mAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
}