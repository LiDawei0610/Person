package com.example.administrator.mydemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mydemo.R;
import com.example.administrator.mydemo.beans.UserBean;
import com.google.gson.Gson;

public class GetLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etPwd;
    private Button btnLogin;
    RequestQueue queue;//请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        etName = ((EditText) findViewById(R.id.et_name));
        etPwd = ((EditText) findViewById(R.id.et_pwd));
        btnLogin = ((Button) findViewById(R.id.btn_login));
        //设置按钮点击事件
        btnLogin.setOnClickListener(this);
        //创建请求队列
         queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
       //点击登录，回调onclick
        //获取用户名和密码的值
        String  userName = etName.getText().toString();
        String  userPwd = etPwd.getText().toString();
        String url="http://188.188.2.15:8080/MyDemo/logininfoservlet?userName="+userName+"&userPwd="+userPwd;
       //创建请求对象
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson  gson=new Gson();
                //将gson 转换成userbean对象
                UserBean userBean = gson.fromJson(s, UserBean.class);
                if(!userBean.equals("")){
                    Toast.makeText(GetLoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(GetLoginActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(GetLoginActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
            }
        });
        //将请求添加到请求队列
        queue.add(request);
    }
}
