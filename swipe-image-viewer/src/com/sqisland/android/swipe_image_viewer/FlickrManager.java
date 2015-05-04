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

	// FlickrManger initialize Cache object
	public static Cache cache;
	private static final String FLICKRAPIKEY = "1f4d7f7e2e02b7c06ccf10846cf10c2c";

	public FlickrManager()
	{
		cache=new Cache();
	}

	// API key for Flickr API



	/**
	 * 
	 * @param searchPattern tag for photo search
	 * @param limit number of photo in one request
	 * @return returns string of one photo randomly selected
	 * @throws IOException Throws exception if i/o operation is not valid
	 * @throws JSONException Throws exception if there is any JSON related exception
	 */
	public static String flickrApi(String searchPattern, int limit) throws IOException, JSONException {

		// Flickr REST request url
		URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&text=" + searchPattern + "&api_key=" + FLICKRAPIKEY + "&per_page="+ limit + "&format=json");
		URLConnection connection = url.openConnection();

		//Reads request option from input stream reader
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		// parsing photo JSONObject from JSON file
		int start = builder.toString().indexOf("(") + 1;
		int end = builder.toString().length() - 1;
		String jSONString = builder.toString().substring( start, end);

		JSONObject jSONObject = new JSONObject(jSONString); //whole json object
		JSONObject jSONObjectInner = jSONObject.getJSONObject("photos"); //inner Json object
		JSONArray photoArray = jSONObjectInner.getJSONArray("photo"); // inner array of photos
		JSONObject photo = photoArray.getJSONObject((int) (limit*Math.random())); //get one random photo from array

		return constructFlickrImgUrl(photo);
	}


	/**
	 * 
	 * @param input takes JSONObject of photo as input
	 * @return
	 * @throws JSONException
	 */
	public static String constructFlickrImgUrl(JSONObject input) throws JSONException {
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

	/**
	 * Below is helper function to call flickrApi
	 */
	public static void callFlickr()
	{
		try {
			cache.addBitmapToMemoryCache(getBitmapFromURL(flickrApi(randomizer(5),10)));
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}

	}

	/**
	 * 
	 * @param length size of string
	 * @return random string of length size
	 */
	public static String randomizer(int length){
		char i[] = new char[length];
		for (int j = 0; j < length; j++) {
			i[j] =(char)(5*Math.random()+'a');
		}
		return new String(i);
	}

	/**
	 * 
	 * @param src takes string URL of image
	 * @return Object of Bitmap
	 */
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
