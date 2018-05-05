package com.example.administrator.myapplication.base

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.example.administrator.myapplication.R

import butterknife.ButterKnife
import com.vise.common_base.activity.BaseActivity
import java.util.ArrayList

open class BaseActivity : AppCompatActivity() {
    private var mIsNeedBaseAnim = true
    /** 获取主题色  */
    val colorPrimary: Int
        get() {
            val typedValue = TypedValue()
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            return typedValue.data
        }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        if (mIsNeedBaseAnim) {
            overridePendingTransition(com.vise.common_base.R.anim.push_in_right, com.vise.common_base.R.anim.push_out_left)
        }
    }

    fun setIsNeedBaseAnim(isNeedBaseAnim: Boolean) {
        mIsNeedBaseAnim = isNeedBaseAnim
    }
    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        if (mIsNeedBaseAnim) {
            overridePendingTransition(com.vise.common_base.R.anim.push_in_right, com.vise.common_base.R.anim.push_out_left)
        }
    }
    private var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSystemBarTint()
        initpermissiom();
    }

    private fun initpermissiom() {
        if (isNeedCheck) {
            checkPermissions(*needPermissions)
        }

//return type Unit
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        ButterKnife.bind(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, paramArrayOfInt: IntArray) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                isNeedCheck = false
            }
        }
    }

    private val PERMISSON_REQUESTCODE = 0
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private var isNeedCheck = true

    private fun checkPermissions(vararg permissions: String) {
        val needRequestPermissonList = findDeniedPermissions(permissions as Array<String>)
        if (null != needRequestPermissonList && needRequestPermissonList.size > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toTypedArray(),
                    PERMISSON_REQUESTCODE)
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     */
    private fun findDeniedPermissions(permissions: Array<String>): List<String> {
        val needRequestPermissonList = ArrayList<String>()
        for (perm in permissions) {
            if (ContextCompat.checkSelfPermission(this,
                            perm) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(
                            this, perm)) {
                needRequestPermissonList.add(perm)
            }
        }
        return needRequestPermissonList
    }

    /**
     * 检测是否说有的权限都已经授权
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 需要进行检测的权限数组
     */
    protected var needPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.VIBRATE,
            Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE)
    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        super.setContentView(view, params)
        ButterKnife.bind(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
    }

    /** 设置状态栏颜色  */
    protected fun initSystemBarTint() {
        val window = window
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            return
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = setStatusBarColor()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            //            tintManager.setStatusBarTintEnabled(true);
            //            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }


    /** 子类可以重写改变状态栏颜色  */
    protected fun setStatusBarColor(): Int {
        return colorPrimary
    }

    /** 子类可以重写决定是否使用透明状态栏  */
    protected fun translucentStatusBar(): Boolean {
        return false
    }

    fun showLoading() {
        if (dialog != null && dialog!!.isShowing) return
        dialog = ProgressDialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog!!.setMessage("请求网络中...")
        dialog!!.show()
    }

    fun dismissLoading() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    fun displayImage(url: String, imageView: ImageView) {
        Glide.with(applicationContext)//
                .load(url)//
                .error(R.mipmap.ic_launcher)//
                .into(imageView)
    }

}
