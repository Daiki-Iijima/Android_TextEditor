package com.example.memoapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothClass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.text.Editable
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.core.text.toHtml
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  レイアウト内のWidgetを取得
        var t = findViewById<TextView>(R.id.mainInputField);
        val serchBtn = findViewById<Button>(R.id.SerchBtn);
        val serchStringText = findViewById<TextView>(R.id.SerchWordText);
        val spaceCountToggle = findViewById<Switch>(R.id.spaceCountToggle);
        val countText = findViewById<TextView>(R.id.CountTextView);
        val flagText = findViewById<TextView>(R.id.FlagText);

        //  検索ボタンクリックイベント
        serchBtn.setOnClickListener {
            t.setText(Html.fromHtml(Serch(t.text.toString(),serchStringText.text.toString())));
        }

        //  テキストの変更イベントの監視
        t.doAfterTextChanged { text ->

            if(text != null)
                countText.text = StringCount(text,spaceCountToggle.isChecked).toString();
            if (text != null) {
                Log.d("",text.toHtml());
            };

            flagText.text = text?.count().toString();
        }
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
