
package com.coolweather.app.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
//  �����ʹ�����������ص�ʡ������
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB 
			coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0 ){
				for(String p : allProvinces){
					/*
					 * java.lang.string.split 
						split ���� 
						��һ���ַ����ָ�Ϊ���ַ�����Ȼ�󽫽����Ϊ�ַ������鷵�ء� 
						stringObj.split([separator��[limit]]) 
						���� 
						stringObj 
						��ѡ�Ҫ���ֽ�� String ��������֡��ö��󲻻ᱻ split �����޸ġ� 
						separator 
						��ѡ��ַ����� ������ʽ��������ʶ�˷ָ��ַ���ʱʹ�õ���һ�����Ƕ���ַ���������Ը�ѡ����ذ��������ַ����ĵ�һԪ�����顣 
						limit 
						��ѡ���ֵ�������Ʒ��������е�Ԫ�ظ�����
						
						˵�� 
						split �����Ľ����һ���ַ������飬�� stingObj ��ÿ������ separator ��λ�ö�Ҫ���зֽ�
						
						1������á�.����Ϊ�ָ��Ļ�������������д����String.split("\\."),����������ȷ�ķָ�����������String.split(".");
						2������á�|����Ϊ�ָ��Ļ�������������д����String.split("\\|"),����������ȷ�ķָ�����������String.split("|");
						��.���͡�|������ת���ַ�������ü�"\\";
						3�������һ���ַ������ж���ָ����������á�|����Ϊ���ַ������磺��a=1 and b =2 or c=3��,���������ָ�������������String.split("and|or");
					 */
					String [] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// save in table Province
					coolWeatherDB.saveProvince(province);
					
				}
				return true;
			}
		}
		return false;
	}
	
//	�����ʹ�����������ص��м�����
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB 
			coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0 ){
				for(String p : allProvinces){
					//����á�|����Ϊ�ָ��Ļ�������������д����String.split("\\|"),����������ȷ�ķָ�����������String.split("|");
					//��.���͡�|������ת���ַ�������ü�"\\";
					//split �����Ľ����һ���ַ������飬�� stingObj ��ÿ������ separator ��λ�ö�Ҫ���зֽ�
					String [] array = p.split("\\|"); 
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					// save in table Province
					coolWeatherDB.saveCity(city);
					
				}
				return true;
			}
		}
		return false;
	}
	
//	�����ʹ�����������ص��ؼ����ݾ�
	
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB 
			coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0 ){
				for(String p : allProvinces){
					//����á�|����Ϊ�ָ��Ļ�������������д����String.split("\\|"),����������ȷ�ķָ�����������String.split("|");
					//��.���͡�|������ת���ַ�������ü�"\\";
					//split �����Ľ����һ���ַ������飬�� stingObj ��ÿ������ separator ��λ�ö�Ҫ���зֽ�
					String [] array = p.split("\\|"); 
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					// save in table Province
					coolWeatherDB.saveCounty(county);
					
				}
				return true;
			}
		}
		return false;
	}
	
	
	//�������������ص�JSON���ݣ����������������ݴ洢�����ء�
	public static void handleWeatherRespons(Context context,String response){
		try{
			JSONObject jsonObject = new JSONObject(response);
			JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
			String cityName = weatherInfo.getString("city");
			String wetaherCode = weatherInfo.getString("cityid");
			String temp1 = weatherInfo.getString("temp1");
			String temp2 = weatherInfo.getString("temp2");
			String weatherDesp = weatherInfo.getString("weather");
			String publishTime = weatherInfo.getString("ptime");
			saveWeatherInfo(context,cityName,wetaherCode,temp1,temp2,weatherDesp,publishTime);
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	
	//������������������Ϣ�洢��SharedPreferences�ļ��С�
	public static void saveWeatherInfo(Context context,String cityName,String wetaherCode,String temp1,
			String temp2,String weatherDesp,String publishTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��M��d��",Locale.CHINA);
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putString("city_name", cityName);
		editor.putString("wetaher_Code", wetaherCode);
		editor.putString("temp1", temp1);
		editor.putString("temp2", temp2);
		editor.putString("weather_desp", weatherDesp);
		editor.putString("publish_time", publishTime);
		editor.putString("current_date", sdf.format(new Date()));
		editor.commit();
	}
}
