package cn.edu.pku.zhangjc.miniweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.zhangjc.app.MyApplication;
import cn.edu.pku.zhangjc.bean.City;

public class SelectCity extends Activity implements View.OnClickListener {
    private ImageView mBackBtn;
    private ListView mList;
    private EditText mSearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        initViews();

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
    }

    public void initViews() {
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        MyApplication myApplication = (MyApplication) getApplication();
        List<City> cityList = myApplication.getCityList();
        final List<String> cityName = new ArrayList<>();
        final List<String> cityCodes = new ArrayList<>();
        for (City city:cityList) {
            cityCodes.add(city.getNumber());
        }
        for (City city:cityList) {
            cityName.add(city.getCity());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this, android.R.layout.simple_list_item_1, cityName);
        ListView listView = (ListView) findViewById(R.id.title_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position 点击的Item位置，从0开始算
                String cityCode = cityCodes.get(position);
                Intent intent=new Intent();
                intent.putExtra("cityCode",cityCode);//传递给下一个Activity的值
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mSearch = findViewById(R.id.search_city);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                Intent i = new Intent();
                i.putExtra("cityCode", "101160101");
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                break;
        }
    }
}
