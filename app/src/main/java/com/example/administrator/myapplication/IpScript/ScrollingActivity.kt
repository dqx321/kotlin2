package com.example.administrator.myapplication.IpScript

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_scrolling.*
import com.app.hubert.guide.model.HighLight
import com.app.hubert.guide.model.GuidePage
import android.widget.TextView
import com.app.hubert.guide.listener.OnLayoutInflatedListener
import android.R.attr.button
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.app.hubert.guide.listener.OnPageChangedListener
import com.app.hubert.guide.core.Controller
import com.app.hubert.guide.listener.OnGuideChangedListener
import com.app.hubert.guide.NewbieGuide
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


class ScrollingActivity : BaseActivity() {
    internal var TAG = IpScriptActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }



    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menuSearch -> {
                startActivity(Intent(this, IpScript1Activity::class.java))

                return true
            }
            R.id.menuHot -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
