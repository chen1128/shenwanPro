package com.up72.shenwanapp.task;

import android.content.Context;

import com.up72.shenwanapp.Constants;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 网络请求类
 * Created by cwb on 2016/12/16.
 */
public class Task {

    /**
     * JAVA专用
     */
    public static <T> T java(Class<T> service) {
        return create(service);
    }

    /**
     * JAVA传图片专用
     */
    public static <T> T uploadByJava(Class<T> service) {
        return createUpLoad(Constants.baseHostUrl, service);
    }


    public static <T> T create(Class<T> service) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
//                .addInterceptor(new SignInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseHostUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(false, false))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .build();
        return retrofit.create(service);
    }

    /**
     * 上传头像
     */
    public static <T> T createUpLoad(String baseUrl, Class<T> service) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .addInterceptor(new SignInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(false, true))
                .build();
        return retrofit.create(service);
    }

    private static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
       /* int[] certficates = new int[]{R.raw.media};
        builder.socketFactory(getSSLSocketFactory(AppContext.context(), certficates));*/
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));

                if (is != null) {
                    is.close();
                }
            }
            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}