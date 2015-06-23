package com.dynamic.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class DynamicImpl implements IDynamic {
	static String TAG = "Daynamic";
	static Context mContext = null;

	static {
		// System.load("/sdcard/libHelloWorld.so");
		// String dexPath = "./libs/libHelloWorld.so";
		// load("libHelloWorld.so");
		// if(mContext!=null)
		// if (mContext != null) {
		// File dir = mContext.getDir("libs", Context.MODE_PRIVATE);
		// // 获取驱动文件输出流
		// File soFile = new File(dir, "loadin.so");
		// System.load(soFile.getAbsolutePath());
		// }
		// System.loadLibrary("HelloWorld");
	}

	public DynamicImpl() {
		// TODO Auto-generated constructor stub
	}

	public DynamicImpl(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public native String helloWorldFromJNI();// native声明，表示这个方法来自Native层。实现过程已经在native层实现了

	public native int showIntFromJNI();// native声明，表示这个方法来自Native层。实现过程已经在native层实现了

	@Override
	public void init(Context context) {
		this.mContext = context;
		load("libloadin.so");
	}

	@Override
	public void destory() {
		mContext = null;
	}

	@Override
	public void showTipe() {
		Toast.makeText(mContext, helloWorldFromJNI(), Toast.LENGTH_LONG).show();
	}

	private static int load(String path) {
		Log.v(TAG, "LazyBandingLib localPath:");

		try {
			String localPath = Environment.getExternalStorageDirectory()
					+ File.separator + path;
			Log.v(TAG, "LazyBandingLib localPath:" + localPath);

			// 开辟一个输入流
			File inFile = new File(localPath);
			// 判断需加载的文件是否存在
			if (!inFile.exists()) {
				// 下载远程驱动文件
				Log.v(TAG, inFile.getAbsolutePath() + " is not fond!");
				return 1;
			} else
				Log.v(TAG, inFile.getAbsolutePath() + " is found!" + mContext);

			File dir = mContext.getDir("libs", Context.MODE_PRIVATE);
			Log.e(TAG,
					"### " + dir.getAbsolutePath() + " is exists:"
							+ dir.exists());

			FileInputStream fis = new FileInputStream(inFile);
			// 获取驱动文件输出流
			// if (dir.exists())

			File soFile = new File(dir, path);
			if (!soFile.exists()) {
				Log.e(TAG, "### " + soFile.getAbsolutePath() + " is not exists");
				FileOutputStream fos = new FileOutputStream(soFile);

				// 字节数组输出流，写入到内存中(ram)
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				// 从内存到写入到具体文件
				fos.write(baos.toByteArray());
				// 关闭文件流
				baos.close();
				fos.close();
			}
			fis.close();
			Log.v(TAG, "### System.load start");
			// 加载外设驱动
			System.load(soFile.getAbsolutePath());
			Log.v(TAG, "### System.load End");
			return 0;
		} catch (Exception e) {
			Log.v(TAG, "Exception   " + e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}