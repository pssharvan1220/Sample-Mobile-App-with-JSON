package com.mobile.se_2017_037

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.mobile.se_2017_037.dataclass.MyData
import org.json.JSONArray
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    lateinit var pDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url="https://jsonplaceholder.typicode.com/posts"
        AsyncTaskHandler().execute(url)
    }

    inner class AsyncTaskHandler: AsyncTask<String, String, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog= ProgressDialog(this@MainActivity)
            pDialog.setMessage("Please Wait")
            pDialog.setCancelable(false)
            pDialog.show()

        }

        override fun doInBackground(vararg url: String?): String {
            // TODO("Not yet implemented")

            val res:String
            val connection= URL(url[0]).openConnection()as HttpsURLConnection
            try{
                connection.connect()
                res=connection.inputStream.use { it.reader().use {reader->reader.readText()} }
            }
            finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if(pDialog.isShowing)
                pDialog.dismiss()
            jsonResult(result)
        }

        private fun jsonResult(jsonString:String?)
        {
            val jsonArray= JSONArray(jsonString)
            val list=ArrayList<MyData>()
            var i=0
            while(i<jsonArray.length())
            {
                val jsonObject=jsonArray.getJSONObject(i)
                list.add(
                    MyData(
                        jsonObject.getInt("UserId"),
                        jsonObject.getInt("Id"),
                        jsonObject.getString("Title"),
                        jsonObject.getString("Body")
                    )
                )
                i++
            }
            val adapter=ListAdapter(this@MainActivity,list)
            findViewById<ListView>(R.id.mylist).adapter = adapter
        }
    }
}