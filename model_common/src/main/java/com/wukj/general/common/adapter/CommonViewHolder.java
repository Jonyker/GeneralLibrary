package com.wukj.general.common.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目名称：model_library
 * 类名称：com.jonyker.common.base.viewholder
 * 类描述：
 * 创建人：Jonyker
 * 创建时间：2017/8/9 0009 下午 2:50
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2017/8/9 0009 下午 2:50
 * 修改备注：
 * 版本：V.1.0
 */

public class CommonViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    public View getConvertView() {
        return mConvertView;
    }

    public CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        this.mPosition = position;
        this.mContext = context;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static CommonViewHolder get(Context context, View convertView,
                                       ViewGroup parent, int layoutId, int position) {
        if (null == convertView) {
            return new CommonViewHolder(context, parent, layoutId, position);
        } else {
            CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
            holder.mPosition = position;

            return holder;
        }
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    /**
     * 给ID为viewId的TextView设置文字text，并返回this
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 给ID为viewId的TextView设置文字颜色，并返回this
     */
    public CommonViewHolder setTextColor(int viewId, int colorId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 给ID为viewId的TextView设置文字text，并返回this
     */
    public CommonViewHolder setImage(int viewId, int resouceId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resouceId);
        return this;
    }

    public int getmPosition() {
        return mPosition;
    }

}
