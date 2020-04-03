package lukewithey.example.flickrbrowser

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete, GetFlickrJsonData.OnDataAvailable {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android,oreo", "en-us", true)
        val getRawData = GetRawData(this)
        getRawData.execute(url)

        Log.d(TAG, "OnCreate ends")
    }

    private fun createUri(baseURL: String, searchCriteria: String, lang: String, matchAll: Boolean): String {
        Log.d(TAG, ".createURi starts")

        return Uri.parse(baseURL).
        buildUpon().
        appendQueryParameter("tags", searchCriteria).
        appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY").
        appendQueryParameter("lang", lang).
        appendQueryParameter("format", "json").
        appendQueryParameter("nojsoncallback", "1").
        build().toString()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG,"onCreateOptionsMenu called" )
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG,"onOptionsItemSelected called")
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data:String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete called")

            val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
        } else {
            // download failed
            Log.d(TAG, "onDownloadComplete failed with status $status. Error message is $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, ".onDataAvailable called, data is $data")

        Log.d(TAG, ".onDataAvailable ends")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError called with ${exception.message}")
    }

    //    companion object {
//        private const val TAG = "MainActivity"
//    }
}
