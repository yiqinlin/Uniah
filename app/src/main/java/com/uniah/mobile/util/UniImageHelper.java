package com.uniah.mobile.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.io.File;

public class UniImageHelper {

    static ImageLoader imageLoader;

    public static ImageLoader getDefaultImageLoader(Context context) {
        File file = UniFileHelper.getFilePath(UniFileHelper.FileType.ImgCache);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)//配置内存缓存图片的尺寸
                .memoryCacheSize(2 * 1024 * 1024)//配置内存缓存的大小
                .threadPoolSize(3)//配置加载图片的线程数
                .threadPriority(1000)//配置线程的优先级
                .diskCache(new UnlimitedDiskCache(file))//UnlimitedDiskCache 限制这个图片的缓存路径
                .diskCacheFileCount(50)//配置sdcard缓存文件的数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//MD5这种方式生成缓存文件的名字
                .diskCacheSize(50 * 1024 * 1024)//在sdcard缓存50MB
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);
        return imageLoader;
    }

    public static DisplayImageOptions getSimpleOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                // .showImageForEmptyUri(R.mipmap.ic_empty)//图片地址有误
                //.showImageOnFail(R.mipmap.ic_error)//当图片加载出现错误的时候显示的图片
                //.showImageOnLoading(R.mipmap.loading)//图片正在加载的时候显示的图片
                .imageScaleType(ImageScaleType.NONE)// 设置图片以如何的编码方式显示
                .considerExifParams(true).bitmapConfig(Bitmap.Config.ARGB_8888)// 设置图片的解码类型
                .build();// 构建完成
    }

    public static void clearImageLoaderCache(Context context) {
        if (imageLoader == null) {
            imageLoader = getDefaultImageLoader(context);
        }
        imageLoader.clearDiskCache();//清除磁盘缓存
        imageLoader.clearMemoryCache();//清除内存缓存
    }

    private static void checkImageLoader(Context context) {
        if (imageLoader == null) {
            imageLoader = getDefaultImageLoader(context);
        }
    }

    public static void displayImage(Context context, String url, ImageView imageView) {
        displayImage(context, url, imageView, null, null);
    }

    public static void displayImage(Context context, String url, ImageView imageView, ImageLoadingListener loadListener) {
        displayImage(context, url, imageView, loadListener, null);
    }

    public static void displayImage(Context context, String url, ImageView imageView, ImageLoadingProgressListener progressListener) {
        displayImage(context, url, imageView, null, progressListener);
    }

    public static void displayImage(Context context, String url, ImageView imageView, ImageLoadingListener loadListener, ImageLoadingProgressListener progressListener) {
        checkImageLoader(context);
        imageLoader.displayImage(url, imageView, getSimpleOptions(), loadListener, progressListener);
    }


    public static Intent getCropIntent(Uri in, Uri out) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(in, "image/*");//数据和类型
        intent.putExtra("crop", "true");//开启的Intent 显示View是可裁减的
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);//裁剪的图片的宽高。最终得到的输出图片的宽高400
        intent.putExtra("circleCrop", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, out);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", false);//裁剪后的数据通过intent返回回来
        intent.putExtra("noFaceDetection", true);
        return intent;
    }
}
