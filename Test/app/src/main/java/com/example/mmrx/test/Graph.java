package com.example.mmrx.test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liulinru on 2017/4/21.
 */

public class Graph extends View {
    IDrawListener listener;
    public Graph(Context context) {
        super(context);
    }

    public Graph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Graph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if(listener != null)
            listener.onDraw(this,canvas);
    }

    public IDrawListener getListener() {
        return listener;
    }

    public void setListener(IDrawListener listener) {
        this.listener = listener;
    }
}
