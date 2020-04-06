package lukewithey.example.flickrbrowser

import android.os.Bundle
import android.util.Log


class SearchActivity : BaseActivity() {
    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activivateToolbar(true)
        Log.d(TAG, "onCreate: ends")

    }

}
