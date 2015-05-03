package com.sqisland.android.swipe_image_viewer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class FlickrManager {

	static Cache cache;

	public FlickrManager()
	{
		cache=new Cache();
	}
	private static final String FLICKRAPIKEY = "1f4d7f7e2e02b7c06ccf10846cf10c2c";

	public static String flickrApi(String searchPattern, int limit) throws IOException, JSONException {

		URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&text=" + searchPattern + "&api_key=" + FLICKRAPIKEY + "&per_page="+ limit + "&format=json");
		Log.e("url",url.toString());

		URLConnection connection = url.openConnection();
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		Log.d("not good", builder.toString());
		//no, this is not yet a valid json response :)

		int start = builder.toString().indexOf("(") + 1;
		int end = builder.toString().length() - 1;
		String jSONString = builder.toString().substring( start, end);
		//after cutting off the junk, its ok

		JSONObject jSONObject = new JSONObject(jSONString); //whole json object
		JSONObject jSONObjectInner = jSONObject.getJSONObject("photos"); //inner Json object
		JSONArray photoArray = jSONObjectInner.getJSONArray("photo"); // inner array of photos
		JSONObject photo = photoArray.getJSONObject((int) (limit*Math.random())); //get one random photo from array

		return constructFlickrImgUrl(photo, size._m);
	}

	// source: flickr.com/services/api/misc.urls.html
	enum size {
		_s , _t ,_m
	};

	//helper method, to construct the url from the json object. You can define the size of the image that you want, with the size parameter. Be aware that not all images on flickr are available in all sizes.
	public static String constructFlickrImgUrl(JSONObject input, Enum size) throws JSONException {
		String FARMID = input.getString("farm");
		String SERVERID = input.getString("server");
		String SECRET = input.getString("secret");
		String ID = input.getString("id");

		StringBuilder sb = new StringBuilder();

		sb.append("https://farm");
		sb.append(FARMID);
		sb.append(".static.flickr.com/");
		sb.append(SERVERID);
		sb.append("/");
		sb.append(ID);
		sb.append("_");
		sb.append(SECRET);
		//sb.append(size.toString());
		sb.append(".jpg");

		return sb.toString();
	}

	public static void callFlickr()
	{


		try {
			cache.addBitmapToMemoryCache(getBitmapFromURL(flickrApi(randomizer(5),3)));
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}


	}


	public static String randomizer(int length){
		char i[] = new char[length];
		for (int j = 0; j < length; j++) {
			i[j] =(char)(5*Math.random()+'a');
		}
		return new String(i);
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			Log.d("Bitmap",""+myBitmap);
			return myBitmap;
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}


}
