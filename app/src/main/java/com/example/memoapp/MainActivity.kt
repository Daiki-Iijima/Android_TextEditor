package com.example.memoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.core.text.toHtml
import androidx.core.widget.doAfterTextChanged
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  レイアウト内のWidgetを取得
        val titleText = findViewById<TextView>(R.id.TitleText)
        var contentText = findViewById<TextView>(R.id.mainInputField);
        val serchBtn = findViewById<Button>(R.id.SerchBtn);
        val serchStringText = findViewById<TextView>(R.id.SerchWordText);
        val spaceCountToggle = findViewById<Switch>(R.id.spaceCountToggle);
        val countText = findViewById<TextView>(R.id.CountTextView);
        val flagText = findViewById<TextView>(R.id.FlagText);
        val closeBtn = findViewById<Button>(R.id.CloseBtn);



        //  検索ボタンクリックイベント
        serchBtn.setOnClickListener {
            contentText.setText(Html.fromHtml(Serch(contentText.text.toString(),serchStringText.text.toString())));
        }

        //  閉じるボタン押下時イベント
        closeBtn.setOnClickListener{
            val intent = Intent(this, ListView::class.java)
            intent.putExtra("KEY_TITLE", titleText.text.toString());//第一引数key、第二引数渡したい値
            intent.putExtra("KEY_CONTENT", contentText.text.toString());//第一引数key、第二引数渡したい値

            startActivity(intent)
        }

        //  テキストの変更イベントの監視
        contentText.doAfterTextChanged { text ->

            if(text != null)
                countText.text = StringCount(text,spaceCountToggle.isChecked).toString();
            if (text != null) {
                Log.d("",text.toHtml());
            };

            flagText.text = text?.count().toString();
        }

        spaceCountToggle.setOnCheckedChangeListener { _, isChecked -> // 表示する文字列をスイッチのオンオフで変える

            // オンなら
            if (isChecked) {
                Log.d("","ON")
            } else {
                Log.d("","OFF")
            }
            val toast = Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT)
            toast.show()
        }

        //  データの受け取り
        var tText = intent.getStringExtra("KEY_TITLE") ?: return
        var cText = intent.getStringExtra("KEY_CONTENT") ?: return
        titleText.text = tText
        contentText.text = cText
    }

    //  文字列を検索して、対象の文字列の色を変更した文字列を返す
    fun Serch(targetString: String,serchString: String):String
    {
        var retTargetString = targetString.replace(serchString,
            "<font color=\"#ffaa00\">"+serchString+"</font>");

        return retTargetString;
    }

    //  文字の数をカウントする
    fun StringCount(targetEditable: Editable,spaceFlag: Boolean):Int
    {
        var retInt = 0;

        if(spaceFlag)
        {
            retInt = targetEditable.toString().count();
        }
        else
        {
            val array = targetEditable.toHtml().split(";");
            retInt = array.count()-1;
        }

        return retInt;
    }
}
