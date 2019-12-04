package id.infiniteuny.academichandbook.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.R.layout
import id.infiniteuny.academichandbook.ui.main.home.HomeFragment
import id.infiniteuny.academichandbook.ui.main.pedoman.PedomanFragment
import id.infiniteuny.academichandbook.ui.main.schedule.ScheduleFragment
import kotlinx.android.synthetic.main.activity_main.nav_main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        supportActionBar?.hide()
        if (null==savedInstanceState){
            changeFragment(HomeFragment.getInstance())
        }
        nav_main.setOnNavigationItemSelectedListener(navListener)

    }

    private val navListener= BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.home->{
                changeFragment(HomeFragment.getInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.schedule->{
                changeFragment(ScheduleFragment.getInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.panduan->{
                changeFragment(PedomanFragment.getInstance())
                return@OnNavigationItemSelectedListener true
            }
        }


        false
    }

    private fun changeFragment(fr:Fragment){
        val transact=supportFragmentManager.beginTransaction()
        transact.replace(R.id.frame_main,fr)
        transact.commit()
    }
}
