package com.example.citysganged;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ENCODING = "UTF-8";
    private static final String TAG = "MainActivity";

    private Button mButton;
    private Area mArea;
    private ArrayList<Area> mDatas;
    private ListView mListView, mListView1;
    private MainAdapter mMainAdapter, mMainAdapter2;
    private ArrayList<Area> provinceName;
    private ArrayList<Area> citysName;
    private PopupWindow mCityPopupWindow;
    private String aa, bb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provinceName = new ArrayList<>();
        mDatas = new ArrayList<>();
        citysName = new ArrayList<>();
        mButton = this.findViewById(R.id.button);
        initPopWindowForCitys();
        mButton.setOnClickListener(this);
    }

    private void initPopWindowForCitys() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View layout = inflater.inflate(R.layout.wheelpopupforcity, null, false);
        mListView = layout.findViewById(R.id.list);
        mListView1 = layout.findViewById(R.id.list1);
        final TextView tvProvince = layout.findViewById(R.id.tv_province);
        final TextView tvCitys = layout.findViewById(R.id.tv_city);
        layout.getBackground().setAlpha(130);
        //更新UI
        layout.invalidate();
        mCityPopupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        Button btn_city_canle = layout.findViewById(R.id.pickcitycancle);
        Button pickcityconfirm = layout.findViewById(R.id.pickcityconfirm);
        btn_city_canle.getPaint().setFakeBoldText(true);
        btn_city_canle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCityPopupWindow.dismiss();
                mCityPopupWindow.setFocusable(false);
            }
        });
        pickcityconfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cc = aa + bb;
                mCityPopupWindow.dismiss();
                mCityPopupWindow.setFocusable(false);
                Toast.makeText(MainActivity.this, cc, Toast.LENGTH_LONG).show();
            }
        });
        String str1 = getFromAssets("leibie.json");
        JSONObject js;
        try {
            js = new JSONObject(str1);
            String areaBeans = js.getString("areaBeans");
            JSONArray areaBean = new JSONArray(areaBeans);
            for (int i = 0; i < areaBean.length(); i++) {
                mArea = new Area();
                JSONObject json = (JSONObject) areaBean.get(i);
                mArea.setAreaid(json.optString("areaid"));
                mArea.setName(json.optString("name"));
                mArea.setPinyin(json.optString("pinyin"));
                mArea.setShortpinyin(json.optString("shortpinyin"));
                mArea.setType(json.optString("type"));
                mArea.setParentId(json.optString("parentId"));
                mDatas.add(i, mArea);
            }
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i).getType().equals("s")) {
                    provinceName.add(mDatas.get(i));
                }
            }
            Log.d(TAG, "firstName:" + provinceName.get(0).toString());
            mCityPopupWindow.setFocusable(true);
            mMainAdapter = new MainAdapter(MainActivity.this, provinceName);
            mMainAdapter2 = new MainAdapter(MainActivity.this, citysName);
            mListView.setAdapter(mMainAdapter);
            mListView1.setAdapter(mMainAdapter2);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    citysName.clear();
                    tvCitys.setText("");
                    for (int i = 0; i < mDatas.size(); i++) {
                        String id = provinceName.get(arg2).getAreaid();
                        aa = provinceName.get(arg2).getName();
                        if (mDatas.get(i).getType().equals("c")) {
                            if (mDatas.get(i).getParentId().equals(id)) {
                                citysName.add(mDatas.get(i));
                                mMainAdapter2.notifyDataSetChanged();
                            }
                        }
                    }
                    tvProvince.setText(aa);
                    mMainAdapter2.notifyDataSetChanged();
                }
            });

            mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    bb = citysName.get(arg2).getName();
                    tvCitys.setText(bb);
                    mMainAdapter2.notifyDataSetChanged();
                }
            });

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getFromAssets(String fileName) {
        String result = "";
        try {
            InputStream inputStream = getResources().getAssets().open(fileName);
            // 获取文件的字节数
            int length = inputStream.available();
            // 创建byte数组
            byte[] buffer = new byte[length];
            // 将文件中的数据读到byte数组中
            inputStream.read(buffer);
            result = EncodingUtils.getString(buffer, ENCODING);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            mCityPopupWindow.setAnimationStyle(R.style.PopupAnimation);
            mCityPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
            mCityPopupWindow.update();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
