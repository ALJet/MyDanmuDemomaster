package indi.aljet.mydanmudemo_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import indi.aljet.mydanmudemo_master.danmu.DanmuControl;
import indi.aljet.mydanmudemo_master.model.Danmu;
import master.flame.danmaku.controller.IDanmakuView;

public class MainActivity extends AppCompatActivity {

    private IDanmakuView mDanmakuView;
    private DanmuControl mDanmuControl;

    private Button btnAddDanmu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        mDanmuControl = new DanmuControl(this);
        mDanmakuView = findViewById(R.id.danmakuView);
        btnAddDanmu = findViewById(R.id.btnAddDanmu);
        mDanmuControl.setDanmakuView(mDanmakuView);
        btnAddDanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });
    }


    private void setData(){
        List<Danmu> danmus = new ArrayList<>();
        Danmu danmu1 = new Danmu(0, 1, "Like", R.mipmap.ic_default_header, "勉为骑男");
        Danmu danmu2 = new Danmu(0, 2, "Comment", R.mipmap.ic_default_header, "强人锁男");
        Danmu danmu3 = new Danmu(0, 3, "Like", R.mipmap.ic_default_header, "知男而上");
        Danmu danmu4 = new Danmu(0, 1, "Comment", R.mipmap.wat, "左右两男");
        Danmu danmu5 = new Danmu(0, 2, "Like", R.mipmap.wat, "男上佳男");
        Danmu danmu6 = new Danmu(0, 3, "Comment", R.mipmap.wat, "进退两男");
        danmus.add(danmu1);
        danmus.add(danmu2);
        danmus.add(danmu3);
        danmus.add(danmu4);
        danmus.add(danmu5);
        danmus.add(danmu6);
        Collections.shuffle(danmus);
        mDanmuControl.addDanmuList(danmus);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDanmuControl.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mDanmuControl.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDanmuControl.destroy();
    }


}
