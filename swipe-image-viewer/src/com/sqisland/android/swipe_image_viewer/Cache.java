package com.sqisland.android.swipe_image_viewer;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;


@SuppressLint("NewApi")
public class Cache {

	public static int currentImgIndex=1;

	public static LruCache<Integer, Bitmap> mMemoryCache;
	public static int totalImg=0;
	final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	final int cacheSize = (maxMemory/8);

	public Cache()
	{
		mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(Integer key, Bitmap bitmap) {
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return totalImg;
			}
		};

	}





	public static void addBitmapToMemoryCache( Bitmap bitmap) {
		//if (getBitmapFromMemCache(++totalImg) == null) {

		Log.e("In Cache", bitmap.toString());
		mMemoryCache.put(totalImg++, bitmap);
		//}
	}


	public static Bitmap getBitmapFromMemCache(Integer key) {
		return mMemoryCache.get(key);
	}





}
