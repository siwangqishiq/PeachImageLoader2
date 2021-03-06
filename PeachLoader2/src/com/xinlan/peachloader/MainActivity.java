package com.xinlan.peachloader;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xinlan.gaussblur.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public final class MainActivity extends Activity
{
    private ImageView img1, img2, img3;

    public static final String url1 = "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg";
    public static final String url2 = "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg";
    public static final String url3 = "http://image3.suning.cn/images/shop/cms/4225/1413115665607_1200.jpg";

    public static final String[] urls = {
            "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg",
            "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg" };

    private ExecutorService threadPool;
    private LinearLayout mGroup;

    private Button btn;

    private void addImage()
    {
        mGroup = (LinearLayout) findViewById(R.id.group);

        for (int i = 0, len = urls.length; i < len; i++)
        {
            ImageView addView = new ImageView(this);
            addView.setImageResource(R.drawable.ic_launcher);
            mGroup.addView(addView);
            ImageLoader.getInstance().displayImage(urls[i], addView);
        }// end for i
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        threadPool = Executors.newFixedThreadPool(3);

        ImageLoader.getInstance().displayImage(url1, img1);
        ImageLoader.getInstance().displayImage(url2, img2);

        addImage();

        // ImageLoader.getInstance().displayImage(url3, img3);
        Handler mainThreadHandler = new Handler();
        final DisplayImageOptions opts = new DisplayImageOptions.Builder()
                .handler(mainThreadHandler).build();

        threadPool.submit(new Runnable()
        {
            @Override
            public void run()
            {
                ImageLoader.getInstance().displayImage(url3, img3, opts, null,
                        null);
            }
        });

        Future<String> future = null;

        future = threadPool.submit(new Callable<String>()
        {
            @Override
            public String call()
            {
                try
                {
                    Thread.sleep(4000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return "test";
            }
        });

        try
        {
            String futureResult = future.get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(MainActivity.this, SubActivity.class);
                MainActivity.this.startActivity(it);
            }
        });
    }
}// end class
