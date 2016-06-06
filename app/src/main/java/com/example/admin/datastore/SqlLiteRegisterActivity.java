package com.example.admin.datastore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.R;
import com.example.admin.menu.Menu1Activity;

public class SqlLiteRegisterActivity extends AppCompatActivity {

    TextView studentId, studentName, password, className;
    private RadioButton male = null, female = null;
    Button login_reg, login_ok;
    MyOpenHelper myHelper; // 声明一个MyOpenHelper对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentId = (TextView) this.findViewById(R.id.studentId);
        studentName = (TextView) this.findViewById(R.id.studentName);
        password = (TextView) this.findViewById(R.id.password);
        this.male = (RadioButton) super.findViewById(R.id.male);
        this.female = (RadioButton) super.findViewById(R.id.female);
        className = (TextView) this.findViewById(R.id.inputClass);
        login_reg = (Button) this.findViewById(R.id.btnRegister);
        login_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sex = "男";
                if (female.isChecked())
                    sex = "女";
                long rid = regStudent(studentId.getText().toString().trim(),
                        password.getText().toString().trim(), studentName
                                .getText().toString().trim(), sex, className
                                .getText().toString().trim());//存储数据到数据库SqlLite
                if (rid != -1) {
                    Intent intent = new Intent(SqlLiteRegisterActivity.this,SqlLiteLoginActivity.class);//注册成功，则跳转到登录界面
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

        });

        login_ok = (Button) this.findViewById(R.id.btnLogin);
        login_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqlLiteRegisterActivity.this, SqlLiteLoginActivity.class);
                startActivity(intent);
            }
        });
        myHelper = new MyOpenHelper(this, MyOpenHelper.DB_NAME, null, 1);// 打开数据表库表，若第一次执行，会创建数据库及对应的表（若在onCreate方法里有相关代码）

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public long regStudent(String id, String pwd, String name, String gander, String classname) {
        SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
        ContentValues values = new ContentValues();
        values.put(MyOpenHelper.ID,  id );
        values.put(MyOpenHelper.PWD, pwd);
        values.put(MyOpenHelper.STUDENTNAME,  name );
        values.put(MyOpenHelper.CLASSNAME, classname);
        values.put(MyOpenHelper.GANDER,  gander );
        long rid = db.insert(MyOpenHelper.TABLE_NAME, MyOpenHelper.ID, values); // 插入数据
        db.close();
        return rid;
    }

}
