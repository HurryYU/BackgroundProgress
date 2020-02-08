package com.hurryyu.bgprogress;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hurryyu.bgprogress.adapter.CommonViewGroupAdapter;
import com.hurryyu.bgprogress.adapter.ConstraintLayoutAdapter;
import com.hurryyu.bgprogress.adapter.CustomViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DataBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(useCustomViewAdapter());
//        rv.setAdapter(useConstraintLayoutAdapter());
//        rv.setAdapter(useCommonViewGroupAdapter());
        initData();
    }

    private ConstraintLayoutAdapter useConstraintLayoutAdapter() {
        return new ConstraintLayoutAdapter(this, dataList);
    }

    private CommonViewGroupAdapter useCommonViewGroupAdapter() {
        return new CommonViewGroupAdapter(this, dataList);
    }

    private CustomViewAdapter useCustomViewAdapter() {
        return new CustomViewAdapter(this, dataList);
    }

    private void initData() {
        dataList.add(new DataBean("0.1665", "3000.000", 0.3));
        dataList.add(new DataBean("0.1664", "2759.815", 0.27));
        dataList.add(new DataBean("0.1663", "4000.000", 0.4));
        dataList.add(new DataBean("0.1659", "2000.000", 0.2));
        dataList.add(new DataBean("0.1658", "2000.000", 0.2));
        dataList.add(new DataBean("0.1657", "11757.610", 1));
    }
}
