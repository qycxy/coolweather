package com.coolweather.app.util;

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
}
