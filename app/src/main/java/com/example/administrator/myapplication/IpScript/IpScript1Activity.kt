package com.example.administrator.myapplication.IpScript

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.DataSetObserver
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.AbsListView
import android.widget.ImageButton
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import com.app.hubert.guide.NewbieGuide
import com.app.hubert.guide.core.Controller
import com.app.hubert.guide.listener.OnGuideChangedListener
import com.app.hubert.guide.listener.OnLayoutInflatedListener
import com.app.hubert.guide.model.GuidePage
import com.app.hubert.guide.model.HighLight

import com.coderpig.wechathelper.ControlActivity
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.bean.MainBean
import com.example.administrator.myapplication.http.JsonCallback
import com.example.administrator.myapplication.reView.GlideImageLoader
import com.example.administrator.myapplication.reView.sheetDialog.BottomSheetDialogListView
import com.example.administrator.myapplication.reView.sheetDialog.SpringBackBottomSheetDialog
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.skateboard.zxinglib.CaptureActivity
import com.vise.common_utils.log.LogUtils
import com.youth.banner.Banner
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.toplayout.*

import java.util.ArrayList

class IpScript1Activity : BaseActivity(), View.OnClickListener {


    val TAG = IpScript1Activity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
        setbanner()
//        showCustomSheet()
//showLaber()
//        for (i in 1..10){
//            list.add(i,""+i)
//        }

//        main_list.setAdapter(adap)

    }

//    private val list = mutableListOf<String>();
//    val adap = Adap(this, list, R.layout.toplayout)

    fun showLaber() {
        val enterAnimation = AlphaAnimation(0f, 1f)
        enterAnimation.duration = 600
        enterAnimation.fillAfter = true
        val exitAnimation = AlphaAnimation(1f, 0f)
        exitAnimation.duration = 600
        exitAnimation.fillAfter = true
        NewbieGuide.with(this)
                .setLabel("page")//设置引导层标示区分不同引导层，必传！否则报错
                .setOnGuideChangedListener(object : OnGuideChangedListener {
                    override fun onShowed(controller: Controller) {
                        Log.e(TAG, "NewbieGuide onShowed: ")
                        //引导层显示
                    }

                    override fun onRemoved(controller: Controller) {
                        Log.e(TAG, "NewbieGuide  onRemoved: ")
                        //引导层消失（多页切换不会触发）
                    }
                })
                .setOnPageChangedListener { page ->
                    Log.e(TAG, "NewbieGuide  onPageChanged: $page")
                    //引导页切换，page为当前页位置，从0开始
                }
                .alwaysShow(true)//是否每次都显示引导层，默认false，只显示一次
                .addGuidePage(
                        GuidePage.newInstance()
                                .addHighLight(iamge_btn, HighLight.Shape.RECTANGLE, 20)
                                .setLayoutRes(R.layout.layout, R.id.banner)//引导页布局，点击跳转下一页或者消失引导层的控件id
                                .setEverywhereCancelable(false)//是否点击任意地方跳转下一页或者消失引导层，默认true
                                .setBackgroundColor(resources.getColor(R.color.colorbg))//设置背景色，建议使用有透明度的颜色
                                .setEnterAnimation(enterAnimation)//进入动画
                                .setExitAnimation(exitAnimation)//退出动画
                ).addGuidePage(
                        GuidePage.newInstance()
                                .addHighLight(banner, HighLight.Shape.RECTANGLE, 20)
                                .setLayoutRes(R.layout.layout)//引导页布局，点击跳转下一页或者消失引导层的控件id
                                .setEverywhereCancelable(false)//是否点击任意地方跳转下一页或者消失引导层，默认true
                                .setBackgroundColor(resources.getColor(R.color.colorbg))//设置背景色，建议使用有透明度的颜色
                                .setEnterAnimation(enterAnimation)//进入动画
                                .setExitAnimation(exitAnimation)//退出动画
                )
                .show()//显示引导层(至少需要一页引导页才能显示)
    }

    private fun initView() {
        iamge_btn.setOnClickListener(this)
        right_btn.setOnClickListener(this)
        left_btn.setOnClickListener(this)
    }

    private fun initData() {
        OkGo.get<MainBean>("http://apis.baidu.com/apistore/dhc/getalltemplate")
                .headers("apikey", "cb8e79166644dacb432708f29c17e77e")//
                .params("user", "cb8e79166644dacb432708f29c17e77e")//
                .execute(object : JsonCallback<MainBean>(MainBean::class.java) {
                    override fun onSuccess(response: Response<MainBean>) {

                    }

                    override fun onError(response: Response<MainBean>) {
                        super.onError(response)
                    }
                })

    }

    private fun showCustomSheet() {
        val c = SpringBackBottomSheetDialog(this)
        val v = LayoutInflater.from(this).inflate(R.layout.list, null, false)
        val l = v.findViewById<BottomSheetDialogListView>(R.id.listview)
        initListView(l)
        c.setContentView(v)
        l.bindBottomSheetDialog(v)
        c.addSpringBackDisLimit(-1)
        c.show()
    }

    private fun initListView(l: BottomSheetDialogListView) {
        val datas = ArrayList<String>()

        for (i in 0..39) {
            datas.add(i.toString())
        }

        l.adapter = object : ListAdapter {
            override fun areAllItemsEnabled(): Boolean {
                return false
            }

            override fun isEnabled(position: Int): Boolean {
                return false
            }

            override fun registerDataSetObserver(observer: DataSetObserver) {

            }

            override fun unregisterDataSetObserver(observer: DataSetObserver) {

            }

            override fun getCount(): Int {
                return datas.size
            }

            override fun getItem(position: Int): Any {
                return datas[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun hasStableIds(): Boolean {
                return false
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView = convertView
                if (convertView == null) {
                    convertView = TextView(parent.context)
                    convertView.layoutParams = AbsListView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            40 * 3 // 40dp
                    )
                }
                val t = convertView as TextView?
                t!!.setTextColor(Color.BLACK)
                t.gravity = Gravity.CENTER
                t.text = datas[position]
                t.textSize = 17f
                t.setOnClickListener { Toast.makeText(parent.context, "" + position, Toast.LENGTH_LONG).show() }
                t.setOnTouchListener { v, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        l.setCoordinatorDisallow()
                    }
                    false
                }
                return t
            }

            override fun getItemViewType(position: Int): Int {
                return 0
            }

            override fun getViewTypeCount(): Int {
                return 1
            }

            override fun isEmpty(): Boolean {
                return false
            }
        }
    }

    var listimage = listOf<String>()
    private fun setbanner() {
//        glideImageLoader = GlideImageLoader()
        val imageloader: GlideImageLoader? = null
        banner.setImageLoader(imageloader)
        banner.setImages(listimage)
        //设置动画效果
        banner.setBannerAnimation(Transformer.Accordion)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        banner.start()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iamge_btn -> startActivity(Intent(this@IpScript1Activity, ControlActivity::class.java))
            R.id.right_btn -> startActivityForResult(Intent(this, CaptureActivity::class.java), 100)
            R.id.left_btn -> LogUtils.e("点击了")
            else -> {
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> if (data != null && resultCode == Activity.RESULT_OK) {
                var str = data.getStringExtra(CaptureActivity.KEY_DATA)
                LogUtils.e("=" + str)
            }
            else -> {

            }

        }
    }
}


