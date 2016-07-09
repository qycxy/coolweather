package com.coolweather.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseAreaActivity extends Activity {
	public static final int LEVEL_PROVINCE = 0 ;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;
	
	/*
	 * ProgressDialog 继承自AlertDialog，AlertDialog继承自Dialog,
	 * 实现DialogInterface接口。
	   ProgressDialog的创建方式有两种，一种是new Dialog ,
	       一种是调用Dialog的静态方法Dialog.show()。
	 */
	private ProgressDialog progressDialog;
	private TextView titleText;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;
	private List<String> dataList = new ArrayList<String>();
	
	
	//省列表
	private List<Province> privinceList;
//	市列表
	private List<City> cityList;
//	县列表
	private List<County> countyList;
//	选中的省
	private Province selectedProvince;
//	选中的市
	private City selectedCity;
//  当前选中的级别
	private int currentLevel;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		listView = (ListView) findViewById(R.id.list_view);
		
		
	}

}
