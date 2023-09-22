package ua.thespiker.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.thespiker.exam.adapter.CustomListAdapter
import ua.thespiker.exam.adapter.RowItem
import ua.thespiker.exam.helper.RetrofitClient
import ua.thespiker.exam.model.Data

class AndroidPage : AppCompatActivity(), AdapterView.OnItemClickListener {

    var data: List<Data?>? = null
    var findText = "";
    fun updateData() {
        rowItems = ArrayList()
        for (i in data!!) {
            if (i == null) {
                continue;
            }
            if(!i.title.contains(findText)&& !i.description.contains(findText)){
                continue;
            }

            if (i.type.equals("android")) {

                var pic = R.drawable.nauka;
                if (i.icon == "pen") {
                    pic = R.drawable.pen;
                }
                if (i.icon == "stonks") {
                    pic = R.drawable.stonks;
                }

                val item = RowItem(pic, i.title, i.description)
                (rowItems as ArrayList<RowItem>).add(item)

            }
        }
        listView = findViewById<View>(R.id.list) as ListView
        val adapter = CustomListAdapter(
            applicationContext,
            R.layout.custom_list_item, rowItems
        )
        listView!!.adapter = adapter
        listView!!.setOnItemClickListener( {  parent: AdapterView<*>?, view: View?, position: Int,
                                              id: Long->
            val toast = Toast.makeText(
                applicationContext,
                "Item " + (position + 1) + ": " + rowItems!![position],
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
            toast.show()
        })
    }

    fun getSuperHeroes() {
        val call = RetrofitClient.getInstance().myApi.data
        call.enqueue(object : Callback<List<Data?>?> {
            override fun onResponse(call: Call<List<Data?>?>, response: Response<List<Data?>?>) {
                data = response.body()
                Log.d("testffds", response.errorBody().toString() + " " + response.code())

                rowItems = ArrayList()

                if (data == null) {
                    Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG)
                        .show()
                    return;
                }
                for (i in data!!) {
                    if (i != null) {
                        if (i.type.equals("android")) {

                            var pic = R.drawable.nauka;
                            if (i.icon == "pen") {
                                pic = R.drawable.pen;
                            }
                            if (i.icon == "stonks") {
                                pic = R.drawable.stonks;
                            }

                            val item = RowItem(pic, i.title, i.description)
                            (rowItems as ArrayList<RowItem>).add(item)
                        }
                    }
                }
//        for (i in titles.indices) {
//            val item = RowItem(images[i], titles[i], descriptions[i])
//            (rowItems as ArrayList<RowItem>).add(item)
//        }
                listView = findViewById<View>(R.id.list) as ListView
                val adapter = CustomListAdapter(
                    applicationContext,
                    R.layout.custom_list_item, rowItems
                )
                listView!!.adapter = adapter
                listView!!.setOnItemClickListener( {  parent: AdapterView<*>?, view: View?, position: Int,
                                                      id: Long->
                    val toast = Toast.makeText(
                        applicationContext,
                        "Item " + (position + 1) + ": " + rowItems!![position],
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                })
                //  superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            override fun onFailure(call: Call<List<Data?>?>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }

    var listView: ListView? = null
    var rowItems: MutableList<RowItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_page)

        val thread = Thread {
            getSuperHeroes();
        }
        thread.start();
        findViewById<EditText>(R.id.editTextText).addTextChangedListener { text ->
            this.findText = text.toString();
            updateData();
        }

    }


    override fun onItemClick(
        parent: AdapterView<*>?, view: View?, position: Int,
        id: Long
    ) {
        val toast = Toast.makeText(
            applicationContext,
            "Item " + (position + 1) + ": " + rowItems!![position],
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }
}