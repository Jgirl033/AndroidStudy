package com.example.admin.datastore;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.R;

import java.util.ArrayList;
import java.util.List;

public class SqlLiteLoginActivity extends AppCompatActivity {
    TextView txtUsername,txtPassword;
    Button btnLogin,btnRegister;

    private ListView listview;
    MyOpenHelper myHelper; // 声明一个MyOpenHelper对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtUsername=(TextView)findViewById(R.id.login_userid);
        txtPassword=(TextView)findViewById(R.id.login_password);
        btnLogin=(Button)findViewById(R.id.login_ok);
        btnRegister=(Button)findViewById(R.id.login_reg);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = login(txtUsername.getText().toString().trim(),txtPassword.getText().toString().trim());
                if (result)
                    Toast.makeText(getApplicationContext(), "登录成功！",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "登录失败！",
                            Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqlLiteLoginActivity.this, SqlLiteRegisterActivity.class);
                startActivity(intent);
            }
        });

        listview = (ListView) this.findViewById(R.id.listStudents);
        myHelper = new MyOpenHelper(this, MyOpenHelper.DB_NAME, null, 1);// 打开数据表库表
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SqlLiteLoginActivity.this, android.R.layout.simple_list_item_1, getUsers());
        listview.setAdapter(adapter);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public List<String> getUsers() {//从数据库中获取数据，显示在界面上
        List<String> data = new ArrayList<String>();
        SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
        Cursor c = db.query(MyOpenHelper.TABLE_NAME, new String[]{
                        MyOpenHelper.ID, MyOpenHelper.PWD, MyOpenHelper.STUDENTNAME,
                        MyOpenHelper.GANDER, MyOpenHelper.CLASSNAME}, null, null,
                null, null, null);
        int idindex = c.getColumnIndex(MyOpenHelper.ID);//获取ID字段这一列数据
        int pwdindex = c.getColumnIndex(MyOpenHelper.PWD);//获取PWD字段这一列数据
        while (c.moveToNext()) {
            String sid = c.getString(idindex);
            String pwd = c.getString(pwdindex);
            data.add(sid + "," + pwd);
        }
        return data;
    }

    //模拟登陆过程
    public Boolean login(String stid, String pwd) {
        boolean rid = false; // 插入数据
        SQLiteDatabase db = myHelper.getReadableDatabase(); // 获得数据库对象
        Cursor c = db.query(MyOpenHelper.TABLE_NAME, new String[] {
                        MyOpenHelper.ID, MyOpenHelper.PWD, MyOpenHelper.STUDENTNAME,
                        MyOpenHelper.GANDER, MyOpenHelper.CLASSNAME }, MyOpenHelper.ID
                        + "=? and " + MyOpenHelper.PWD + "=?",
                new String[] { stid, pwd }, null, null, null);
        if (c.getCount() > 0) {
            rid = true;
        }
        db.close();
        return rid;
    }
}
