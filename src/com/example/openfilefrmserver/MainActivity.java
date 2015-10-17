package com.example.openfilefrmserver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void opnPdf(View v){

		//		showPdf();
		DownloadFileFromOnline download=new DownloadFileFromOnline();
		download.execute("");
	}

	public void showPdf(View v)
	{

	}
	class DownloadFileFromOnline extends AsyncTask<String,String,String>{ 
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("In opnPdf().........");
			String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
			System.out.println(extStorageDirectory+"................");
			File folder = new File(extStorageDirectory, "pdf");
			folder.mkdir();
			File file = new File(folder, "Read.pdf");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Downloader.DownloadFile("http://earnestcep.in/nw.pdf", file);

			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			File file = new File(Environment.getExternalStorageDirectory()+"/pdf/Read.pdf");
			PackageManager packageManager = getPackageManager();
			Intent testIntent = new Intent(Intent.ACTION_VIEW);
			testIntent.setType("application/pdf");
			List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/pdf");
			startActivity(intent);
		}
	}

}

