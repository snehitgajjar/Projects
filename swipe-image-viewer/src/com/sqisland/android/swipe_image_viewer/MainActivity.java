package com.sqisland.android.swipe_image_viewer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * 
 * @author snehitgajjar
 *
 */

public class MainActivity extends Activity {


	// Member variable Declaration.
	private Cache cache;
	private ImagePagerAdapter adapter;
	private ViewPager viewPager;
	public static ImageView imageView;
	private FlickrManager flickr;

	/**
	 * onCreate(Bundle) method starts Application.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//set content view AFTER ABOVE sequence (to avoid crash)
		setContentView(R.layout.activity_main);

		if (!isOnline())
		{
			showNoConnectionDialog(this);
			//Toast.makeText(getApplicationContext(), "Internet connection is disabled!", Toast.LENGTH_LONG).show();
		}

		// Create Object of FlickrManager
		flickr=new FlickrManager();

		//Get Reference of Cache class object
		cache=flickr.cache;

		// initialize viewPager component
		viewPager = (ViewPager) findViewById(R.id.view_pager);

		// create adapter which can hold images
		adapter = new ImagePagerAdapter();

		// add above created adapter 
		viewPager.setAdapter(adapter);

		// load 10 images to cache when application starts
		for(int i=0;i<10;i++)
		{
			new GetBitmapFromImage().execute();
		}

		// viewPage setOnPageChangeListener which checks current page number
		// when it satisfy condition given below it adds 10 more photos to cache
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}



			@Override
			public void onPageSelected(int position) {

				if(position+7>Cache.totalImg)
				{
					for(int i=0;i<10;i++)
					{
						new GetBitmapFromImage().execute();
					}
				}

				// Rotation Animation when image is chanced
				RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
				anim.setInterpolator(new LinearInterpolator());
				anim.setRepeatCount(Animation.ABSOLUTE);
				anim.setDuration(150);
				imageView.startAnimation(anim);

			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * 
	 * @param ctx1 takes applicaiton context as parameter
	 * showNoConnecitonDialog method shows dialog box to turn on network if it is off.
	 */
	public static void showNoConnectionDialog(Context ctx1) 
	{
		final Context ctx = ctx1;
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setCancelable(true);
		builder.setMessage(R.string.internet_dialoge_msg);
		builder.setTitle(R.string.internet_dialoge_title);
		builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				ctx.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});

		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				return;
			}
		});

		builder.setOnCancelListener(new DialogInterface.OnCancelListener() 
		{
			@Override
			public void onCancel(DialogInterface dialog) {
				return;
			}
		});

		builder.show();
	}

	/**
	 * 
	 * @return true if network is already on else false
	 */
	public boolean isOnline() 
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) 
		{
			return true;
		}
		return false;
	}



	/**
	 * 
	 * @author snehitgajjar
	 *	ImagePagerAdapter which is subclass of PagerAdapter dynamically adds ImageView and add
	 *	images to ImageView.
	 */
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
			imageView = new ImageView(context);
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


	/**
	 * 
	 * @author snehitgajjar
	 *	GetBitmapFromImage class handles background activities such as network connection.
	 */
	public class GetBitmapFromImage extends AsyncTask<Void, Void, Void> {


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