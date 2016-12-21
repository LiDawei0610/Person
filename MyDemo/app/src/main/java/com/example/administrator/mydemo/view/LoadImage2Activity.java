package com.example.administrator.mydemo.view;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mydemo.R;

public class LoadImage2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSend;
    private ImageView iv;
    RequestQueue queue;//请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //控件的初始化
        setContentView(R.layout.activity_load_image2);
        btnSend = ((Button) findViewById(R.id.btn_send));
        iv = ((ImageView) findViewById(R.id.imageView));
        //按钮的点击事件
        btnSend.setOnClickListener(this);
        //创建请求队列
        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        //网络图片的地址
        String url="http://d.hiphotos.baidu.com/pic/w%3D310/sign=a00aca825aafa40f3cc6c8dc9b65038c/060828381f30e9246b63e1814c086e061c95f7bf.jpg";
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        //我们通过调用ImageLoader的getImageListener()方法能够获取到一个ImageListener对象
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv,
                R.drawable.name, R.drawable.psd);
        //调用ImageLoader的get()方法加载网络上的图片
         imageLoader.get(url,listener,1000,1000);
    }
}
