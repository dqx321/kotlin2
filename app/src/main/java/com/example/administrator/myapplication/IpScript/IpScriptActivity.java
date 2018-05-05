package com.example.administrator.myapplication.IpScript;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coderpig.wechathelper.ControlActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.MainBean;
import com.example.administrator.myapplication.fragment.NomalAdapter;
import com.example.administrator.myapplication.http.JsonCallback;
import com.example.administrator.myapplication.http.Urls;
import com.example.administrator.myapplication.reView.GlideImageLoader;
import com.example.administrator.myapplication.reView.sheetDialog.BottomSheetDialogListView;
import com.example.administrator.myapplication.reView.sheetDialog.SpringBackBottomSheetDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IpScriptActivity extends BaseActivity implements View.OnClickListener {
    Banner banner;
    GlideImageLoader glideImageLoader;
    List listimage = new ArrayList();
    ImageButton iamge_btn;
    RecyclerView main_list;
    String Tag = IpScriptActivity.class.getName();
    List lists = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++) {
            lists.add(i + "");
        }
        initView();
        initData();
        setbanner();
        showCustomSheet();


    }

    private void initView() {
        main_list = (RecyclerView) findViewById(R.id.main_list);
        main_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        iamge_btn = (ImageButton) findViewById(R.id.iamge_btn);
        iamge_btn.setOnClickListener(this);
        NomalAdapter adapter = new NomalAdapter(main_list);

        main_list.setAdapter(adapter);
        adapter.setData(lists);
    }

    private void initData() {
        OkGo.<MainBean>get("http://apis.baidu.com/apistore/dhc/getalltemplate")
                .headers("apikey", "cb8e79166644dacb432708f29c17e77e")//
                .params("user", "cb8e79166644dacb432708f29c17e77e")//
                .execute(new JsonCallback<MainBean>(MainBean.class) {
                    @Override
                    public void onSuccess(Response<MainBean> response) {

                    }

                    @Override
                    public void onError(Response<MainBean> response) {
                        super.onError(response);
                    }
                });

    }

    private void showCustomSheet() {
        SpringBackBottomSheetDialog c = new SpringBackBottomSheetDialog(this);
        View v = LayoutInflater.from(this).inflate(R.layout.list, null, false);
        BottomSheetDialogListView l = v.findViewById(R.id.listview);
        initListView(l);
        c.setContentView(v);

        l.bindBottomSheetDialog(v);
        c.addSpringBackDisLimit(-1);

        c.show();
    }

    private void initListView(final BottomSheetDialogListView l) {
        final List<String> datas = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            datas.add(String.valueOf(i));
        }

        l.setAdapter(
                new ListAdapter() {
                    @Override
                    public boolean areAllItemsEnabled() {
                        return false;
                    }

                    @Override
                    public boolean isEnabled(int position) {
                        return false;
                    }

                    @Override
                    public void registerDataSetObserver(DataSetObserver observer) {

                    }

                    @Override
                    public void unregisterDataSetObserver(DataSetObserver observer) {

                    }

                    @Override
                    public int getCount() {
                        return datas.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return datas.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public boolean hasStableIds() {
                        return false;
                    }

                    @Override
                    public View getView(final int position, View convertView, final ViewGroup parent) {
                        if (convertView == null) {
                            convertView = new TextView(parent.getContext());
                            convertView.setLayoutParams(
                                    new AbsListView.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            40 * 3 // 40dp
                                    )
                            );
                        }
                        TextView t = (TextView) convertView;
                        t.setTextColor(Color.BLACK);
                        t.setGravity(Gravity.CENTER);
                        t.setText(datas.get(position));
                        t.setTextSize(17);
                        t.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(parent.getContext(), "" + position, Toast.LENGTH_LONG).show();
                                    }
                                }
                        );
                        t.setOnTouchListener(
                                new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                            l.setCoordinatorDisallow();
                                        }
                                        return false;
                                    }
                                }
                        );
                        return t;
                    }

                    @Override
                    public int getItemViewType(int position) {
                        return 0;
                    }

                    @Override
                    public int getViewTypeCount() {
                        return 1;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }
                }
        );
    }


    private void setbanner() {
        banner = findViewById(R.id.banner);
        glideImageLoader = new GlideImageLoader();
        banner.setImageLoader(glideImageLoader);
        banner.setImages(listimage);
        //设置动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        banner.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iamge_btn:
                startActivity(new Intent(IpScriptActivity.this, ControlActivity.class));
                break;
            default:
                break;
        }

    }
}
