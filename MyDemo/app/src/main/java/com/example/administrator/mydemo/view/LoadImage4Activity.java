package com.example.administrator.mydemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mydemo.R;
import com.example.administrator.mydemo.bitmapCache.BitmapCache;

public class LoadImage4Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSend;
    private NetworkImageView networkImageView;
    RequestQueue queue;//请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image4);
        btnSend = ((Button) findViewById(R.id.btn_send));
        networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);
        btnSend.setOnClickListener(this);
        //创建请求队列
        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        String url="http://d.hiphotos.baidu.com/pic/w%3D310/sign=a00aca825aafa40f3cc6c8dc9b65038c/060828381f30e9246b63e1814c086e061c95f7bf.jpg";
        ImageLoader imageLoader = new ImageLoader(queue,new BitmapCache());

        //得到了NetworkImageView控件的实例之后，
        // 我们可以调用它的setDefaultImageResId()方法、
        // setErrorImageResId()方法和setImageUrl()方法来分别设置加载中显示的图片，
        // 加载失败时显示的图片，以及目标图片的URL地址
        networkImageView.setDefaultImageResId(R.drawable.name);
        networkImageView.setErrorImageResId(R.drawable.psd);
        networkImageView.setImageUrl(url, imageLoader);
    }
}
