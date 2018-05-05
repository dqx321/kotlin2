package com.example.administrator.myapplication.fragment

import android.annotation.SuppressLint
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.util.ArrayMap
import android.view.View

import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.bean.ClassBean
import com.example.administrator.myapplication.util.ExecutorUtil
import com.vise.common_utils.log.LogUtils
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.bottom_layout.*
import kotlin.collections.ArrayList
import java.io.InputStream
import java.lang.reflect.Array.getLength
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.SAXParserFactory
import java.lang.reflect.Array.getLength


class Main2Activity : AppCompatActivity(), OnChangeMainActivityDatas1 {
    //fragment传递数据给Main
    override fun ChangeMainActivityDatas1(arrayMap: ArrayMap<String, String>?) {//return type kotlin.Unit
    }

    override fun ChangeDatas1(data: String?) {//return type kotlin.Unit
        LogUtils.e("MainFragment=ChangeDatas1")

    }

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    val list = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initFragment()
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, list)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        first_layout.setOnClickListener(View.OnClickListener {
            container.setCurrentItem(0)

        })
        second_layout.setOnClickListener(View.OnClickListener {
            container.setCurrentItem(1)

        })
        third_layout.setOnClickListener(View.OnClickListener {
            container.setCurrentItem(2)

        })
        fourth_layout.setOnClickListener(View.OnClickListener {
            container.setCurrentItem(3)

        })
        container.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {//return type kotlin.Unit

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {//return type kotlin.Unit

            }

            override fun onPageSelected(position: Int) {//return type kotlin.Unit

            }


        })
        getData()
    }

    @SuppressLint("HandlerLeak")
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> fragment1!!.update(listdata)
                else -> ""

            }


        }
    }

    var listdata = listOf<ClassBean>()
    private fun getData() {
//return type Unit
        ExecutorUtil.execute {

            listdata = dom2xml(getResources().getAssets().open("layout2.xml"))
            LogUtils.e("MainFragment"+listdata.toString())
            handler.obtainMessage(1)

        }

    }

    @Throws(Exception::class)
    fun dom2xml(`is`: InputStream): List<ClassBean> {
        //一系列的初始化
        val list = arrayListOf<ClassBean>()
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        //获得Document对象
        val document = builder.parse(`is`)
        //获得student的List
        val studentList = document.getElementsByTagName("student")
        //遍历student标签
        for (i in 0 until studentList.length) {
            //获得student标签
            val node_student = studentList.item(i)
            //获得student标签里面的标签
            val childNodes = node_student.childNodes
            //新建student对象
            val student = ClassBean()
            //遍历student标签里面的标签
            for (j in 0 until childNodes.length) {
                //获得name和nickName标签
                val childNode = childNodes.item(j)
                //判断是name还是nickName
                if ("name" == childNode.nodeName) {
                    val name = childNode.textContent
                    student.setName(name)
                    //获取name的属性
                    val nnm = childNode.attributes
                    //获取sex属性，由于只有一个属性，所以取0
                    val n = nnm.item(0)
                    student.setSex(n.textContent)
                } else if ("nickName" == childNode.nodeName) {
                    val nickName = childNode.textContent
                    student.setNickname(nickName)
                }
            }
            //加到List中
            list.add(student)
        }
        return list
    }

    var fragment1: MainFragment? = null
    var fragment2: Kotfragment? = null
    var fragment3: MessageFragment? = null
    var fragment4: SettingFragment? = null
    //宿主activity中的getTitles()方法
    fun getTitles(): String {
        return "hello"
    }

    private fun initFragment() {
//return type Unit

        fragment1 = MainFragment()
//        val bundle = Bundle()
//        bundle.putString("data", values)//这里的values就是我们要传的值
//        fragment1!!.setArguments(bundle)
        fragment3 = MessageFragment()
        fragment2 = Kotfragment()
        fragment4 = SettingFragment()
        list.add(fragment1!!)
        list.add(fragment2!!)
        list.add(fragment3!!)
        list.add(fragment4!!)

    }

    fun setColor() {


    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager, list: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return list.get(position)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return list.size
        }
    }

}
