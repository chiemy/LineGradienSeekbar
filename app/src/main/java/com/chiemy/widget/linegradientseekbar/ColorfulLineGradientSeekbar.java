package com.chiemy.widget.linegradientseekbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class ColorfulLineGradientSeekbar extends SeekBar{

	public ColorfulLineGradientSeekbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ColorfulLineGradientSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ColorfulLineGradientSeekbar(Context context) {
		super(context);
		init();
	}

	private void init(){
		int colorCount = 12;
		int colorAngleStep = 360 / 12;
		int [] colors = new int[colorCount + 1];
		float hsv[] = new float[] { 0f, 1f, 1f };
		for (int i = 0; i < colors.length; i++) {
			hsv[0] = (i * colorAngleStep) % 360;
			colors[i] = Color.HSVToColor(hsv);
		}
		setColor(colors);
	}
	
	private void setColor(int [] colors) {
		GradientDrawable drawable = new GradientDrawable(Orientation.LEFT_RIGHT, colors);
		drawable.setCornerRadius(5);//圆角
		//drawable.setBounds(getProgressDrawable().getBounds());
		//setProgressDrawable(drawable);
		//按以上方式drawable会充满整个seekbar，要按下列方式设置
		Drawable drawable1 = getProgressDrawable();
		if (drawable1 != null){
			Rect bounds = getProgressDrawable().getBounds();
			setProgressDrawable(drawable);
			getProgressDrawable().setBounds(bounds);
		}
	}

	/**
	 * 获取进度值对应的颜色
	 * @return
	 */
	public int getCurrentProgressColor() {
		float [] hsv = getHsv(getProgress());
		return Color.HSVToColor(hsv);
	}

	/**
	 * 获取进度值对应的颜色
	 * @return
	 */
	public int getMaxProgressColor() {
		float [] hsv = getHsv(getMax());
		return Color.HSVToColor(hsv);
	}

	/**
	 * 获取进度值为0的颜色
	 * @return
	 */
	public int getProgress0Color() {
		float [] hsv = getHsv(0);
		return Color.HSVToColor(hsv);
	}

	private float [] getHsv(float progress) {
		float hsv[] = new float[] { 0f, 1f, 1f };
		hsv[0] = progress / getMax() * 360;
		return hsv;
	}
}
