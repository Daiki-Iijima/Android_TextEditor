package com.example.memoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewpractice.Data

class CustomAdapter(private  val dataList: ArrayList<Data>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //  Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val btn: Button = view.findViewById(R.id.AddDataBtn);
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layer_item,viewGroup,false);
        return ViewHolder(view);
    }

    //  Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataList[position];

        viewHolder.btn.text = data.name;
        viewHolder.btn.setOnClickListener{
            data.func.invoke();
        }

    }

    //  表示数を返す
    override fun getItemCount() = dataList.size
}