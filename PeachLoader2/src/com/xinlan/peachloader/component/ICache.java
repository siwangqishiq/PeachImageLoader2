package com.xinlan.peachloader.component;

import android.graphics.Bitmap;

public interface ICache
{
    Bitmap getBitmapFromCache(String name);
    
    void clear();
    
    Bitmap getFromMemoryCahce(String name);
    
    Bitmap getFromFileCache(String name);
    
}//end inerface
