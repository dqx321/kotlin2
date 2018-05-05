package com.example.administrator.myapplication.IpScript

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.reView.GlideImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initview()
        initData()
        setbanner()
        showCustomSheet()
    }

    private fun  showCustomSheet() {
//return type Unit
    }


    private fun setbanner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initview() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
