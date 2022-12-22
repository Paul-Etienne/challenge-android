package francois.pauletienne.challengeandroid.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import francois.pauletienne.challengeandroid.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun displayLoading(isLoading: Boolean) {
        val loader = findViewById<ProgressBar>(R.id.progressBar)

        if (isLoading) {
            loader.visibility = View.VISIBLE
        } else {
            loader.visibility = View.GONE
        }
    }

}