package com.example.administrator.mydemo.view;


import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mydemo.R;



public class LoadImage1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSend;
    private ImageView iv;
    RequestQueue queue;//请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        //初始化控件
        btnSend = ((Button) findViewById(R.id.btn_send));
        iv = ((ImageView) findViewById(R.id.imageView));
        //按钮的点击事件
        btnSend.setOnClickListener(this);
        //创建请求队列
        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        //网络图片地址
    String url="http://d.hiphotos.baidu.com/pic/w%3D310/sign=a00aca825aafa40f3cc6c8dc9b65038c/060828381f30e9246b63e1814c086e061c95f7bf.jpg";
        //接下来要去new出一个ImageRequest对象
        ImageRequest imageRequest=new ImageRequest(url, new Response.Listener<Bitmap>() {
            //第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            //第六个参数是图片请求失败的回调，这里我们当请求失败时在ImageView中显示一张默认图片
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                iv.setImageResource(R.drawable.psd);
            }
        });
        //将请求添加到请求队列
        queue.add(imageRequest);
    }
}
