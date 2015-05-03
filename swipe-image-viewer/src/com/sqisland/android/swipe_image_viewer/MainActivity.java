package com.sqisland.android.swipe_image_viewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public static Bitmap bitmap;
	public static ProgressDialog progressDialog;
	public static int currentItemViewPager;
	Cache cache;
	ImagePagerAdapter adapter;
	ViewPager viewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FlickrManager flickr=new FlickrManager();
		cache=flickr.cache;


		viewPager = (ViewPager) findViewById(R.id.view_pager);
		//bitmapImages.add(getBitmapFromURL("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg"));
		//bitmapImages.add(getBitmapFromURL("http://tvfiles.alphacoders.com/100/hdclearart-10.png"));
		// new getBitmapFromImage().execute("http://cdn3.nflximg.net/images/3093/2043093.jpg");
		// new getBitmapFromImage().execute("http://cdn3.nflximg.net/images/3093/2043093.jpg");

		adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);
		for(int i=0;i<10;i++)
		{
			new getBitmapFromImage().execute();
		}


		Log.e("start","start");

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				//Toast.makeText(getApplicationContext(),"This is "+viewPager.getCurrentItem(),Toast.LENGTH_SHORT).show();
				Log.e("Check Current Item : ",""+position);

			}

			@Override
			public void onPageSelected(int position) {
				Log.e("PS Current Item : ",""+position);
				//Toast.makeText(getApplicationContext(), "This is " + position, Toast.LENGTH_SHORT).show();
				if(position+7>Cache.totalImg)
				{
					//   new getBitmapFromImage().execute("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

					for(int i=0;i<10;i++)
					{
						new getBitmapFromImage().execute();
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	public class ImagePagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Cache.totalImg;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((ImageView) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Context context = MainActivity.this;
			ImageView imageView = new ImageView(context);
			int padding = 0;//context.getResources().getDimensionPixelSize(R.dimen.padding_small);
			imageView.setPadding(padding, padding, padding, padding);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			//imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			//imageView.setImageResource(bitmapImages[position]);
			//new getBitmapFromImage().execute();
			Log.e("InInstantiateItem",Cache.getBitmapFromMemCache(position).toString());
			imageView.setImageBitmap(Cache.getBitmapFromMemCache(position));
			((ViewPager) container).addView(imageView, 0);

			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((ImageView) object);
		}
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

	public class getBitmapFromImage extends AsyncTask<Void, Void, Void> {
		Bitmap bmp;

		@Override
		protected void onPreExecute() {
			//progressDialog = ProgressDialog.show(MainActivity.this, "Wait", "Downloading...");
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			FlickrManager.callFlickr();
			return null;
		}

		@Override
		protected void onPostExecute(Void bitmap) {
			//progressDialog.dismiss();



			//adapter.instantiateItem(viewPager,bitmapImages.size()-1);

			adapter.notifyDataSetChanged();

			Log.e("InPost", "hi");
			//

			super.onPostExecute(null);
		}
	}

}