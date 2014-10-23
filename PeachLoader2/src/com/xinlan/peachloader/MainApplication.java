package com.xinlan.peachloader;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.xinlan.gaussblur.R;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

public class MainApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context)
    {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        File cacheDir = StorageUtils.getCacheDirectory(context);
        System.out.println("´ÅÅÌÏÂÔØÄ¿Â¼--->"+cacheDir.getAbsolutePath());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher) // resource or
                .cacheInMemory(true) // default
                .cacheOnDisc(true) // defaultcacheOnDisc
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .build();
        
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .threadPoolSize(4) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .discCache(new UnlimitedDiscCache(cacheDir)) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .defaultDisplayImageOptions(options) // default
                .writeDebugLogs()
                .build();
//        
//        
//        ImageLoaderConfiguration defaultConfig = ImageLoaderConfiguration
//                .createDefault(context);
        // ImageLoaderConfiguration config = new
        // ImageLoaderConfiguration.Builder(context)
        // .threadPriority(Thread.NORM_PRIORITY - 2)
        // .denyCacheImageMultipleSizesInMemory()
        // .diskCacheFileNameGenerator(new Md5FileNameGenerator())
        // .diskCacheSize(50 * 1024 * 1024) // 50 Mb
        // .tasksProcessingOrder(QueueProcessingType.LIFO)
        // .writeDebugLogs() // Remove for release app
        // .build();
        ImageLoader.getInstance().init(config);
        // Initialize ImageLoader with configuration.
        // ImageLoader.getInstance().init(
        // ImageLoaderConfiguration.createDefault(context));
    }
}// end class
