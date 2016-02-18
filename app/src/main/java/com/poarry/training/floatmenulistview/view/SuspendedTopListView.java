package com.poarry.training.floatmenulistview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by j-yangbo on 2015/12/29.
 */
public class SuspendedTopListView extends ListView {

    private static final String TAG = "SuspendedTopListView";
    private static boolean DEBUG_LIFECYCLE = false;
    private View fixedHeader = null;
    private View mFloatView = null;
    private boolean isNeedDraw = false;

    public SuspendedTopListView(Context context) {
        super(context);
    }


    public SuspendedTopListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuspendedTopListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "onFinishInflate  " + this + "  ");
        }


    }

    /**
     * listView 回到顶部
     */
    public void setScroll2Top() {
        setSelection(0);
    }

    public void setFloatMenuView(View views) {
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "setSuspendedView  " + this + "  " + views);
        }
        if (views == null) {
            throw new IllegalStateException("the floatMenuView is not found");
        }
        if (views != null) {
            mFloatView = views;
        }
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "layoutChildren  " + this + "  ");
        }
        ListAdapter adapter = getAdapter();
        fixedHeader = adapter.getView(0, null, this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "onMeasure  " + this + "  ");
        }
      /*  if (fixedHeader != null && mFloatView != null) {
            LayoutParams layoutParams = (LayoutParams) fixedHeader.getLayoutParams();
//            if (layoutParams == null) {
                layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//            }
            int heightMode = MeasureSpec.getMode(layoutParams.height);
            if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
                heightMode = MeasureSpec.EXACTLY;
            }
            int heightSize = MeasureSpec.getSize(layoutParams.height);
            int maxHeight = getHeight() - getListPaddingTop() - getListPaddingBottom();
            if (heightSize > maxHeight) {
                heightSize = maxHeight;
            }
            int widthSpec = MeasureSpec.makeMeasureSpec(getWidth() - getListPaddingLeft() - getListPaddingRight(), MeasureSpec.EXACTLY);
            int heightSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
            mFloatView.measure(widthSpec, heightSpec);
        }*/
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "onLayout  " + this + "  ");
        }
        if (fixedHeader != null && mFloatView != null) {
            mFloatView.layout(0, 0, mFloatView.getMeasuredWidth(), mFloatView.getMeasuredHeight());
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "computeScroll  " + this + "  ");
        }
        if (fixedHeader != null && mFloatView != null) {
            int headerHeight = mFloatView.getMeasuredHeight();
            int height = fixedHeader.getMeasuredHeight();
            View item = getChildAt(0);
            //记录ListView的滚动高度
            int scrolly = -(fixedHeader.getTop()) + getFirstVisiblePosition() * item.getHeight();
            int position = (height - headerHeight);
            if (position <= scrolly) {
                isNeedDraw = true;
                mFloatView.layout(0, 0, mFloatView.getMeasuredWidth(),
                        mFloatView.getMeasuredHeight() + 0);
            } else {
                isNeedDraw = false;
                mFloatView.layout(0, position, mFloatView.getMeasuredWidth(),
                        mFloatView.getMeasuredHeight() + position);
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "dispatchTouchEvent  " + this + "  " + ev);
        }
        if (fixedHeader != null && mFloatView != null && isNeedDraw && ev.getY() < mFloatView.getMeasuredHeight()) {
            mFloatView.dispatchTouchEvent(ev);
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    @SuppressWarnings("NullableProblems")
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (DEBUG_LIFECYCLE) {
            Log.d(TAG, "dispatchDraw  " + this + "  " + canvas);
        }
        if (fixedHeader != null && mFloatView != null && isNeedDraw) {
            drawChild(canvas, mFloatView, getDrawingTime());
        }

    }

}