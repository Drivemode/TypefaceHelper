package com.drivemode.android.typeface;

import android.app.Application;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Helper class for setting typeface to the text views.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public APIs
public final class TypefaceHelper {
	public static final String TAG = TypefaceHelper.class.getSimpleName();
	private static volatile TypefaceHelper sHelper;
	private final TypefaceCache mCache;

	private TypefaceHelper(Application application) {
		mCache = TypefaceCache.getInstance(application);
	}

	/**
	 * Initialize the instance.
	 * @param application the application context.
	 */
	public static synchronized void initialize(Application application) {
		if (sHelper != null) {
			Log.v(TAG, "already initialized");
		}
		sHelper = new TypefaceHelper(application);
	}

	/**
	 * Terminate the instance.
	 */
	public static synchronized void destroy() {
		if (sHelper == null) {
			Log.v(TAG, "not initialized yet");
			return;
		}
		sHelper = null;
	}

	/**
	 * Retrieve the helper instance.
	 * @return the helper instance.
	 */
	public static synchronized TypefaceHelper getInstance() {
		if (sHelper == null) {
			throw new IllegalArgumentException("Instance is not initialized yet. Call initialize() first.");
		}
		return sHelper;
	}

	/**
	 * Set the typeface to the target view.
	 * @param view to set typeface.
	 * @param typefaceName typeface name.
	 * @param <V> text view parameter.
	 */
	public <V extends TextView> void setTypeface(V view, String typefaceName) {
		Typeface typeface = mCache.get(typefaceName);
		view.setTypeface(typeface);
	}

	/**
	 * Set the typeface to the target view.
	 * @param view to set typeface.
	 * @param typefaceName typeface name.
	 * @param style the typeface style.
	 * @param <V> text view parameter.
	 */
	public <V extends TextView> void setTypeface(V view, String typefaceName, int style) {
		Typeface typeface = mCache.get(typefaceName);
		view.setTypeface(typeface, style);
	}

	/**
	 * Set the typeface to the all text views belong to the view group.
	 * @param viewGroup the view group that contains text views.
	 * @param typefaceName typeface name.
	 * @param <V> view group parameter.
	 */
	public <V extends ViewGroup> void setTypeface(V viewGroup, String typefaceName) {
		int count = viewGroup.getChildCount();
		for (int i = 0; i < count; i++) {
			View child = viewGroup.getChildAt(i);
			if (!(child instanceof TextView)) {
				continue;
			}
			setTypeface((TextView) child, typefaceName);
		}
	}

	/**
	 * Set the typeface to the all text views belong to the view group.
	 * @param viewGroup the view group that contains text views.
	 * @param typefaceName typeface name.
	 * @param style the typeface style.
	 * @param <V> view group parameter.
	 */
	public <V extends ViewGroup> void setTypeface(V viewGroup, String typefaceName, int style) {
		int count = viewGroup.getChildCount();
		for (int i = 0; i < count; i++) {
			View child = viewGroup.getChildAt(i);
			if (!(child instanceof TextView)) {
				continue;
			}
			setTypeface((TextView) child, typefaceName, style);
		}
	}

	/**
     * Set the typeface to the target paint.
     * @param paint the set typeface.
     * @param typefaceName typeface name.
     */
    public void setTypeface(Paint paint, String typefaceName) {
        Typeface typeface = mCache.get(typefaceName);
        paint.setTypeface(typeface);
    }

    /**
	 * Set the typeface to the all text views belong to the view group.
	 * @param context the context.
	 * @param layoutRes the layout resource id.
	 * @param typefaceName typeface name.
	 * @return the view.
	 */
	public View setTypeface(Context context, @LayoutRes int layoutRes, String typefaceName) {
		ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(layoutRes, null);
		setTypeface(view, typefaceName);
		return view;
	}

	/**
	 * Set the typeface to the all text views belong to the view group.
	 * @param context the context.
	 * @param layoutRes the layout resource id.
	 * @param typefaceName typeface name.
	 * @param style the typeface style.
	 * @return the view.
	 */
	public View setTypeface(Context context, @LayoutRes int layoutRes, String typefaceName, int style) {
		ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(layoutRes, null);
		setTypeface(view, typefaceName, style);
		return view;
	}
}
