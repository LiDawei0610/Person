package com.example.administrator.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        //使用sql语句查询
        String sql="select * from user";
        //查询出来的数据,通过适配器的方式，显示在listview里面
        Cursor cursor = db.rawQuery(sql,null);
        //更新数据
        refresh(cursor);
        //使用特定方法查询数据--query
        //Cursor cursor = db.query("user",new String []{"_id","name","password"},
                                 //null,null,null,null,null);
        //使用模糊查询
       // String name = nameEt.getText().toString();
        //select * from user where name like "%name%" order by name DESC;
        //Cursor cursor=db.query("user",null,"name like ? ",new String[]{"%"+name+"%"},null,null,"name DESC");
        //refresh(cursor);
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
                //使用sql语句删除数据
                //String sql = "delete from user where _id="+id;
                //db.execSQL(sql);
                //使用特定方法删除数据
                db.delete("user","_id=?",new String[]{id+""});
                //更新表中数据
                ContentValues values = new ContentValues();
                values.put("password","1234567890");
                db.update("user",values,"_id=?",new String[]{id+""});
                //更新listview 的数据
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
        //使用sql语句进行插入数据
        //String sql = "insert into user(name,password) values (?,?)";
        //db.execSQL(sql, new String[]{name, password});
        //使用特定方法插入数据---insert（"表名","",字段名和值得映射）
        ContentValues values = new ContentValues();
        //key就是字段名，value就是值
        values.put("name",name);
        values.put("password",password);
        //返回行是新插入数值的id
        long id = db.insert("user",null,values);
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
