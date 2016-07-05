package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
//  解析和处理服务器返回的省级数据
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB 
			coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0 ){
				for(String p : allProvinces){
					/*
					 * java.lang.string.split 
						split 方法 
						将一个字符串分割为子字符串，然后将结果作为字符串数组返回。 
						stringObj.split([separator，[limit]]) 
						参数 
						stringObj 
						必选项。要被分解的 String 对象或文字。该对象不会被 split 方法修改。 
						separator 
						可选项。字符串或 正则表达式对象，它标识了分隔字符串时使用的是一个还是多个字符。如果忽略该选项，返回包含整个字符串的单一元素数组。 
						limit 
						可选项。该值用来限制返回数组中的元素个数。
						
						说明 
						split 方法的结果是一个字符串数组，在 stingObj 中每个出现 separator 的位置都要进行分解
						
						1、如果用“.”作为分隔的话，必须是如下写法：String.split("\\."),这样才能正确的分隔开，不能用String.split(".");
						2、如果用“|”作为分隔的话，必须是如下写法：String.split("\\|"),这样才能正确的分隔开，不能用String.split("|");
						“.”和“|”都是转义字符，必须得加"\\";
						3、如果在一个字符串中有多个分隔符，可以用“|”作为连字符，比如：“a=1 and b =2 or c=3”,把三个都分隔出来，可以用String.split("and|or");
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
	
//	解析和处理服务器返回的市级数据
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB 
			coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0 ){
				for(String p : allProvinces){
					//如果用“|”作为分隔的话，必须是如下写法：String.split("\\|"),这样才能正确的分隔开，不能用String.split("|");
					//“.”和“|”都是转义字符，必须得加"\\";
					//split 方法的结果是一个字符串数组，在 stingObj 中每个出现 separator 的位置都要进行分解
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
	
//	解析和处理服务器返回的县级数据据
	
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB 
			coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0 ){
				for(String p : allProvinces){
					//如果用“|”作为分隔的话，必须是如下写法：String.split("\\|"),这样才能正确的分隔开，不能用String.split("|");
					//“.”和“|”都是转义字符，必须得加"\\";
					//split 方法的结果是一个字符串数组，在 stingObj 中每个出现 separator 的位置都要进行分解
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
