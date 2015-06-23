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
		// // ��ȡ�����ļ������
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

	public native String helloWorldFromJNI();// native��������ʾ�����������Native�㡣ʵ�ֹ����Ѿ���native��ʵ����

	public native int showIntFromJNI();// native��������ʾ�����������Native�㡣ʵ�ֹ����Ѿ���native��ʵ����

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

			// ����һ��������
			File inFile = new File(localPath);
			// �ж�����ص��ļ��Ƿ����
			if (!inFile.exists()) {
				// ����Զ�������ļ�
				Log.v(TAG, inFile.getAbsolutePath() + " is not fond!");
				return 1;
			} else
				Log.v(TAG, inFile.getAbsolutePath() + " is found!" + mContext);

			File dir = mContext.getDir("libs", Context.MODE_PRIVATE);
			Log.e(TAG,
					"### " + dir.getAbsolutePath() + " is exists:"
							+ dir.exists());

			FileInputStream fis = new FileInputStream(inFile);
			// ��ȡ�����ļ������
			// if (dir.exists())

			File soFile = new File(dir, path);
			if (!soFile.exists()) {
				Log.e(TAG, "### " + soFile.getAbsolutePath() + " is not exists");
				FileOutputStream fos = new FileOutputStream(soFile);

				// �ֽ������������д�뵽�ڴ���(ram)
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				// ���ڴ浽д�뵽�����ļ�
				fos.write(baos.toByteArray());
				// �ر��ļ���
				baos.close();
				fos.close();
			}
			fis.close();
			Log.v(TAG, "### System.load start");
			// ������������
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