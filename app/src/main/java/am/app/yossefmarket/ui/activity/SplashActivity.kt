package am.app.yossefmarket.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import android.widget.TextView
import am.app.yossefmarket.databinding.ActivitySplashBinding
import am.app.yossefmarket.R
import android.content.Intent

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {

    private val delayTime : Long = 3000L
    private lateinit var binding: ActivitySplashBinding
    private  val handler : Handler = Handler(Looper.getMainLooper())
    private val runnable : Runnable = Runnable(this::finish)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= 30) {
            binding.root.windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            binding.root.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    override fun onStart() {
        super.onStart()
        if (!handler.hasCallbacks(runnable)) {
            handler.postDelayed(runnable, delayTime)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!handler.hasCallbacks(runnable)) {
            handler.postDelayed(runnable, delayTime)
        }
    }

    override fun onPause() {
        super.onPause()
        if (handler.hasCallbacks(runnable)) {
            handler.removeCallbacks(runnable)
        }
    }

    override fun finish() {
        startActivity(Intent(this, AuthActivity::class.java))
        super.finish()
    }

}