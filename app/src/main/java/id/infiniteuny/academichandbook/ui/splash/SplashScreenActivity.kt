package id.infiniteuny.academichandbook.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.animation.AnimationUtils
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.iv_splash

class SplashScreenActivity : AppCompatActivity() {

    private var handler: Handler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        val animUtils= AnimationUtils.loadAnimation(this,R.anim.bounce_view)
        iv_splash.startAnimation(animUtils)
        handler= Handler()
        handler?.postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)

    }

    override fun onDestroy() {
        super.onDestroy()
        handler=null
    }

}
