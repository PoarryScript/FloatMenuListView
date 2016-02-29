package com.poarry.training.floatmenulistview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.poarry.training.floatmenulistview.R;

/**
 * Created by j-yangbo on 2016/2/29.
 */
public class HeadView extends LinearLayout implements View.OnClickListener {
    View rootView;
    private Button firstMenu;
    private Button secondMenu;
    private Button thirdMenu;
    private ViewPager mViewPager;
    private View mFloatMenuView;

    private Context context;


    public interface OnItemClickListener {
        void onMenuClick(View view, int infex);
        void onMenuClick(int index);
    }

    private OnItemClickListener listener;

    public void setOnItemMenuClickListener(OnItemClickListener l) {
        listener = l;
    }

    public HeadView(Context context) {
        super(context);
        init(context);
    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        context = mContext;
        rootView = LayoutInflater.from(context).inflate(R.layout.viewpager_layout, null);

        mFloatMenuView = rootView.findViewById(R.id.fixheader);


        firstMenu = (Button) mFloatMenuView.findViewById(R.id.first_tag);
        firstMenu.setOnClickListener(this);
        secondMenu = (Button) mFloatMenuView.findViewById(R.id.second_tag);
        secondMenu.setOnClickListener(this);
        thirdMenu = (Button) mFloatMenuView.findViewById(R.id.third_tag);
        thirdMenu.setOnClickListener(this);
        mViewPager = (ViewPager) rootView.findViewById(R.id.head_top);
        mViewPager.setOnClickListener(this);
    }

    public View getmFloatMenuView() {
        return mFloatMenuView;
    }

    public void setmFloatMenuView(View mFloatMenuView) {
        this.mFloatMenuView = mFloatMenuView;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_tag:
                listener.onMenuClick( 0);
                break;
            case R.id.second_tag:
                listener.onMenuClick(1);
                break;
            case R.id.third_tag:
                listener.onMenuClick(2);
                break;

            case R.id.head_top:
                listener.onMenuClick(3);
                break;
            default:
                break;

        }
    }
}
