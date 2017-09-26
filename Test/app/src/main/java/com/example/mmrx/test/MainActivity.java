package com.example.mmrx.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hxgraph.HXGraphManager;
import com.hxgraph.adapter.BarGraphAdapter;
import com.hxgraph.adapter.DotToLineAdapter;
import com.hxgraph.adapter.KLineAdapter;
import com.hxgraph.adapter.special.HXKLineAdapter;
import com.hxgraph.adapter.special.StockBarGraphAdapter;
import com.hxgraph.adapter.special.StockHistoryClosedGraphAdapter;
import com.hxgraph.model.Constant;
import com.hxgraph.model.param.BarGraphStrategyParam;
import com.hxgraph.model.param.DotToLineStrategyParam;
import com.hxgraph.model.param.KLineStrategyParam;
import com.hxgraph.model.param.special.HXKLineStrategyParam;
import com.hxgraph.model.param.special.StockBarGraphParams;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements IDrawListener{
    double[] rawData;
    HXGraphManager graphManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random(System.currentTimeMillis());
//        rawData = new double[][]{
//            {109.85,15.55,28.6,30.76,86.23},
//            {135.33,18.03,30.5,40.01,102.25},
//            {88.22,10.29,15.66,30.81,60.5},
//            {110.26,11.25,25.27,40.0,101.5}
//        };
//        int[] tech = new int[]{1,-1, Constant.NULLVALUE,1,-1};
//        rawData = new double[]{100,180,233,300,150};
        rawData = new double[50];
        for(int j=0;j<rawData.length;j++) {
            double temp = random.nextDouble() * 20.0+100;
//            if(j%2 == 0)
//                temp = - temp;
            rawData[j] = temp;
        }
//        float[] x = new float[]{10,50,100,200,250};
//        for(int i=0;i<rawData.length;i++){
//            for(int j=0;j<rawData[i].length;j++) {
//                double temp = random.nextDouble() * 100.0;
//                rawData[i][j] = temp;
//            }
//        }

//        graphManager = new HXGraphManager(new KLineAdapter());
//        graphManager = new HXGraphManager(new HXKLineAdapter());
//        graphManager = new HXGraphManager(new StockHistoryClosedGraphAdapter());
        graphManager = new HXGraphManager();
//        graphManager.setAdapter(new StockBarGraphAdapter());
        graphManager.setAdapter(new DotToLineAdapter(),null);
//        KLineStrategyParam params = new KLineStrategyParam();
//        StockBarGraphParams params = new StockBarGraphParams();
        DotToLineStrategyParam params = new DotToLineStrategyParam();
        params.setColor(Color.BLUE);
        params.setBgColor(Color.GRAY);
        params.setFillColor(true);
//        params.setColor(Color.RED);
//        params.setStrokeWidth(2);
//        params.setType(StockBarGraphParams.StockBarType.BBD);
//        params.setxCoordinates(x);
//        ((HXKLineAdapter)(graphManager.getmAdapter())).setTechArray(tech);
        graphManager.setData(rawData,params);
        graphManager.getmAdapter().setXstep(20);

        Graph graph = new Graph(this);
        graph.setListener(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout layout = (FrameLayout)findViewById(R.id.layout);
        layout.addView(graph,layoutParams);
    }

    @Override
    public void onDraw(View view, Canvas canvas) {
//        graphManager.draw(canvas,0,(int)(view.getMeasuredHeight()*0.2),(int)(view.getMeasuredHeight()*0.5));
        graphManager.draw(canvas,view.getMeasuredHeight());
//        Path mPath = new Path();
//
////        mPath.reset();
//        mPath.setFillType(Path.FillType.WINDING);
//        mPath.moveTo(10, 899);
//        mPath.lineTo(10, 899);
//        mPath.lineTo(50,540);
//        mPath.lineTo(100,301);
//        mPath.lineTo(200,2);
//        mPath.lineTo(250,675);
//        mPath.lineTo(250,899);
//        mPath.close();
//        LinearGradient linearGradient = new LinearGradient(0,0,250, 899, Color.BLUE,Color.TRANSPARENT, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setShader(linearGradient);
//        canvas.drawPath(mPath,paint);

//        Path path = new Path();
//        Path path2 = new Path();
//        path.addCircle(200, 200, 100, Path.Direction.CCW);
////        path2.addRect(200, 200, 300, 300, Path.Direction.CCW);
//        canvas.drawPath(path, paint);
    }
}
