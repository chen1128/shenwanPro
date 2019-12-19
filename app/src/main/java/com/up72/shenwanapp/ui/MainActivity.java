package com.up72.shenwanapp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.up72.shenwanapp.R;
import com.up72.shenwanapp.base.BaseActivity;
import com.up72.shenwanapp.manager.RouteManager;
import com.up72.shenwanapp.task.Task;
import com.up72.shenwanapp.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity {

    private Button btnOne,btnWeb,btnPpt,btnVideo;
    private static final String TAG = "TAG";
    private static final String HOST = "192.168.4.31";
    private static final int PORT = 1188;
    private TextView tvContent;
    private String data = "";
    private TextView tvSerial;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 22) {
                if (data!=null && !data.equals("null")) {
                    tvContent.setText(data + "\n" + tvContent.getText());
                }
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected int getContentView() {
        return R.layout.main_act;
    }


    @Override
    protected void onStart() {
        super.onStart();
        // 适配android M，检查权限
        List<String> permissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isNeedRequestPermissions(permissions)) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean isNeedRequestPermissions(List<String> permissions) {
        // 手机状态。获取系列号
        addPermission(permissions, Manifest.permission.READ_PHONE_STATE);
        return permissions.size() > 0;
    }

    private void addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
        }
    }

    @Override
    protected void initView() {
        btnOne =findViewById(R.id.btnOne);
        btnWeb =findViewById(R.id.btnWeb);
        btnPpt =findViewById(R.id.btnPpt);
        btnVideo =findViewById(R.id.btnVideo);
        tvContent =findViewById(R.id.tvContent);
        tvSerial =findViewById(R.id.tvSerial);
    }

    @Override
    protected void initListener() {
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteManager.getInstance().toBanner(MainActivity.this);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteManager.getInstance().toWebView(MainActivity.this,"https://www.csdn.net/");
            }
        });

        btnPpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteManager.getInstance().toWebView(MainActivity.this,"http://view.officeapps.live.com/op/view.aspx?src=http%3A%2F%2Fqra.emscloud.com.cn%2F1.pptx");
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteManager.getInstance().toVideo(MainActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        //序列号
        tvSerial.setText(Utils.getSerialNumber());
        addPadRequst();
//        startTimerTask();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 申请成功
                    tvSerial.setText("设备序列号："+Utils.getSerialNumber());
                    addPadRequst();
                } else {
                    // 申请失败
                    tvSerial.setText("设备序列号：没有同意权限");
                }
            }
        }
    }

    //scoket tcp连接
    protected void TCPClient(){
        try {
            //创建客户端Socket，指定服务器的IP地址和端口
            Socket socket = new Socket(HOST,PORT);
            //获取输出流，向服务器发送数据
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("Android客户端给服务器端发送的数据");
            pw.flush();
            //关闭输出流
            socket.shutdownOutput();

            //获取输入流，接收服务器发来的数据
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //读取客户端数据
            while((data = br.readLine()) != null){
                System.out.println("客户端接收到服务器回应的数据：" + data);
                handler.sendEmptyMessage(22);
            }
            //关闭输入流
//            socket.shutdownInput();

            //关闭资源
            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //定时任务 5秒请求socket连接
    private Timer timerOrder;

    private void startTimerTask() {
        cancelTimerTask();
        timerOrder = new Timer();
        timerOrder.schedule(new TimerTask() {
            @Override
            public void run() {
               TCPClient();
            }
        }, 0, 5000);
    }

    private void cancelTimerTask() {
        if (timerOrder != null) {
            timerOrder.cancel();
            timerOrder.purge();
            timerOrder = null;
        }
    }

    //pad上线
    private void addPadRequst(){
//        Log.d("getDeviceName---",android.os.Build.BRAND+Utils.getDeviceName(this));
        Task.java(MainService.class).onlineAddpad(Utils.getSerialNumber(), android.os.Build.BRAND+":"+Utils.getDeviceName(this)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更显UI
                .subscribe(new Observer<String>() {//订阅
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace(); //请求过程中发生错误
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                       showToast("接口请求成功");
                    }
                });
    }


}