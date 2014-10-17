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
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

public final class SubActivity extends Activity
{
    private ImageView img1, img2, img3;

    public static final String url1 = "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg";
    public static final String url2 = "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg";
    public static final String url3 = "http://image3.suning.cn/images/shop/cms/4225/1413115665607_1200.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        ImageLoader.getInstance().displayImage(url1, img1);
        ImageLoader.getInstance().displayImage(url2, img2);
        ImageLoader.getInstance().displayImage(url3, img3);
    }
}// end class
