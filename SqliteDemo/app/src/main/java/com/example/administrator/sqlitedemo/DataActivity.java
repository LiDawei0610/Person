package com.example.administrator.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    private EditText nameEt;
    private EditText passwordEt;
    private SQLiteDatabase db;
    private String dbName = "user.db";
    private ListView listView;
    private MyOpenHelper helper;
    //处理查询按钮
    public  void myQuery(View view){
        String sql="select * from user";
        //查询出来的数据,通过适配器的方式，显示在listview里面
        Cursor cursor = db.rawQuery(sql,null);
       //更新数据
         refresh(cursor);
    }
    //刷新listview中的数据
    public  void refresh(Cursor cursor){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.activity_list_item,
                cursor,new String[]{"_id","name","password"},
                new int[]{R.id.userId,R.id.userName,R.id.userPassword},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER );
        listView.setAdapter(adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_data);
        nameEt = ((EditText) findViewById(R.id.name));
        passwordEt = ((EditText) findViewById(R.id.pssword));
        listView = ((ListView) findViewById(R.id.list));
        //设置item的点击删除事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv=(TextView) view.findViewById(R.id.userId);
                int id=Integer.parseInt(tv.getText().toString());
                String sql = "delete from user where _id="+id;
                db.execSQL(sql);
                //更新数据
                String sql1="select * from user";
                //查询出来的数据,通过适配器的方式，显示在listview里面
                Cursor cursor = db.rawQuery(sql1,null);
                //更新数据
                refresh(cursor);
                return true ;
            }
        });
        //第二个参数一般采用默认的工厂对象--null
        //getFilesDir()获取应用程序的私有目录
        //db = SQLiteDatabase.openOrCreateDatabase(getFilesDir()+"/"+dbName ,null);
        helper=new MyOpenHelper(this,"user.db",null,1);
         db=helper.getReadableDatabase();

    }
    public void insert(SQLiteDatabase db,String name,String password){
        String sql = "insert into user(name,password) values (?,?)";
        db.execSQL(sql, new String[]{name, password});
    }
    //public void create(SQLiteDatabase db){
    // String sql = "create table user(_id integer primary key AUTOINCREMENT,name ,password)";
    // db.execSQL(sql);
    //}
    public void mySave(View view){
        String name = nameEt.getText().toString();
        String password = passwordEt.getText().toString();
        //try {
            //插入数据
           insert(db,name,password);
        //}catch (Exception e){
            //创建新表
             //create(db);
           // insert(db,name,password);
        //}
        Toast.makeText(this,"插入成功",Toast.LENGTH_LONG).show();
        nameEt.setText("");
        passwordEt.setText("");
    }
}
