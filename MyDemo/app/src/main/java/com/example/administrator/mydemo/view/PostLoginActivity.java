package com.example.administrator.mydemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mydemo.R;
import com.example.administrator.mydemo.beans.UserBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class PostLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPwd;
    private Button btnLogin;
    RequestQueue queue;//请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_login);
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
        final String  userName = etName.getText().toString();
        final String  userPwd = etPwd.getText().toString();
        String url="http://188.188.2.15:8080/MyDemo/logininfoservlet";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson=new Gson();
                //将gson 转换成userbean对象
                UserBean userBean = gson.fromJson(s, UserBean.class);
                if(!userBean.equals("")){
                    Toast.makeText(PostLoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(PostLoginActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(PostLoginActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
            }
        }){
            //重写getParams（）方法
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                //用户名
                map.put("userName",userName);
                //密码
                map.put("userPwd",userPwd);
                return map;
            }
        };
        queue.add(request);
    }
}
