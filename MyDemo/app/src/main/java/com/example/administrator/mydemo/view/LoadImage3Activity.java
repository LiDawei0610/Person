package com.example.administrator.mydemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mydemo.R;
import com.example.administrator.mydemo.bitmapCache.BitmapCache;

public class LoadImage3Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSend;
    private ImageView iv;
    RequestQueue queue;//请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image3);
        btnSend = ((Button) findViewById(R.id.btn_send));
        iv = ((ImageView) findViewById(R.id.imageView));
        btnSend.setOnClickListener(this);
        //创建请求队列
        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        //网络图片地址
        String url="http://d.hiphotos.baidu.com/pic/w%3D310/sign=a00aca825aafa40f3cc6c8dc9b65038c/060828381f30e9246b63e1814c086e061c95f7bf.jpg";

        ImageLoader imageLoader = new ImageLoader(queue,new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv,
                R.drawable.name, R.drawable.psd);
        imageLoader.get(url,listener,500,500);
    }
}
