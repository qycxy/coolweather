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
	 * ProgressDialog �̳���AlertDialog��AlertDialog�̳���Dialog,
	 * ʵ��DialogInterface�ӿڡ�
	   ProgressDialog�Ĵ�����ʽ�����֣�һ����new Dialog ,
	       һ���ǵ���Dialog�ľ�̬����Dialog.show()��
	 */
	private ProgressDialog progressDialog;
	private TextView titleText;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;
	private List<String> dataList = new ArrayList<String>();
	
	
	//ʡ�б�
	private List<Province> privinceList;
//	���б�
	private List<City> cityList;
//	���б�
	private List<County> countyList;
//	ѡ�е�ʡ
	private Province selectedProvince;
//	ѡ�е���
	private City selectedCity;
//  ��ǰѡ�еļ���
	private int currentLevel;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		listView = (ListView) findViewById(R.id.list_view);
		
		
	}

}
