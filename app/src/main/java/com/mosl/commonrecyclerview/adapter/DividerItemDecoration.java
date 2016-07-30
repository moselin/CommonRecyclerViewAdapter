package com.mosl.commonrecyclerview.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Description
 * @Author MoseLin
 * @Date 2016/7/21.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration
{

    private Drawable mDivider;
    private int mDividerHeight;//分割线高度，默认为1px
    private int mOrientation;//画竖线：LinearLayoutManager.VERTICAL或画横线LinearLayoutManager.HORIZONTAL
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    private DividerItemDecoration(Context context, int orientation) {
        this(context, orientation,2);

    }
    private DividerItemDecoration(Context context, int orientation,int mDividerHeight) {
        this(context, orientation,-1,mDividerHeight);

    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    private DividerItemDecoration(Context context, int orientation, int drawableId,int mDividerHeight) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请输入正确画线方向！");
        }
        mOrientation = orientation;
        if ( drawableId == -1 )
        {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }else
        {
            mDivider = ContextCompat.getDrawable(context, drawableId);
        }
        this.mDividerHeight = mDividerHeight;
    }


    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        if ( parent.getChildPosition(view) != 0 )
            outRect.set(0,0,0,mDividerHeight);
    }
    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
        }
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
        }
    }

    public static DividerItemDecoration createVerticalDivider(Context context,int mDividerHeight){
        return new DividerItemDecoration(context, LinearLayoutManager.VERTICAL,mDividerHeight);
    }
    public static DividerItemDecoration createHorizontalDivider(Context context,int mDividerHeight){
        return new DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL,mDividerHeight);
    }
    public static DividerItemDecoration createCustomHorizontalDivider(Context context,int drawableId,int mDividerHeight){
        return new DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL,drawableId,mDividerHeight);
    }
    public static DividerItemDecoration createCustomVerticalDivider(Context context,int drawableId,int mDividerHeight){
        return new DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL,drawableId,mDividerHeight);
    }
}
