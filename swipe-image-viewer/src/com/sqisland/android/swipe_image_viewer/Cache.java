package com.sqisland.android.swipe_image_viewer;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;


/**
 * 
 * @author snehitgajjar
 *
 */
public class Cache {

	// Object LruCache which maps Bitmap with integer index
	private static LruCache<Integer, Bitmap> mMemoryCache;

	// keeps number of record for total image
	public static int totalImg=0;

	// finds maximum available memory
	private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

	// create cache from available size
	private final int cacheSize = (maxMemory/8);

	// constructor of Cache class which initialize LruCache object
	public Cache()
	{
		mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(Integer key, Bitmap bitmap) {
				return totalImg;
			}
		};

	}

	/**
	 * 
	 * @param bitmap Accepts Bitmap Object and store it to cache with index
	 */
	public static void addBitmapToMemoryCache( Bitmap bitmap) {

		Log.e("In Cache", bitmap.toString());
		mMemoryCache.put(totalImg++, bitmap);

	}

	/**
	 * 
	 * @param key accepts key index 
	 * @return returns appropriate bitmap
	 */
	public static Bitmap getBitmapFromMemCache(Integer key) {
		return mMemoryCache.get(key);
	}





}
