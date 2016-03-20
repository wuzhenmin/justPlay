package com.example.zhenmin.justplay;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by hasee on 2016/2/25.
 */
public class SurfaceViewTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

    }

    class MyView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder holder;
        private MyThread myThread;

        public MyView(Context context) {
            super(context);
            holder = this.getHolder();
            holder.addCallback(this);
            myThread = new MyThread(holder);//创建一个绘图线程
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            myThread.isRun = true;
            myThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            myThread.isRun = false;
        }
    }

    class MyThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRun;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count  = 0;
            while (isRun){
                Canvas canvas = null;
                try{
                    synchronized (holder){
                        canvas = holder.lockCanvas();
                        canvas.drawColor(Color.BLACK);
                        Paint paint = new Paint();
                        paint.setColor(Color.WHITE);
                        Rect rect = new Rect(100,50,300,250);
                        canvas.drawRect(rect,paint);
                        canvas.drawText("这是第" + (count++) + "秒", 100, 310, paint);
                        Thread.sleep(1000);
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (canvas!=null){
                        holder.unlockCanvasAndPost(canvas);
                    }

                }
            }
        }
    }


}
