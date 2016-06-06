package com.example.admin.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.admin.R;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu2Activity extends AppCompatActivity {

    AlertDialog dialogMenu;
    GridView gridMenu;
    View ViewMenu;
    private TextView textView;
    int[] menu_image_array = {R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6};
    String[] menu_name_array={"收藏","留言","社区","下载","网址","账户"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.txtMenuShow);
        ViewMenu = View.inflate(this, R.layout.menu2_main, null);
        dialogMenu = new AlertDialog.Builder(this).create();
        dialogMenu.setView(ViewMenu);
        dialogMenu.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU)// 监听按键
                    dialog.dismiss();   //关闭对话框
                return false;
            }
        });


        gridMenu = (GridView)ViewMenu.findViewById(R.id.gridMenu);
        gridMenu.setAdapter(getMenuAdapter(menu_name_array, menu_image_array));
        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(menu_name_array[position]);
                dialogMenu.dismiss();
            }
        });
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (dialogMenu == null) {
            dialogMenu = new AlertDialog.Builder(this).setView(ViewMenu).show();
        } else {
            dialogMenu.show();
        }
        return false;// 返回为true 则显示系统menu
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("menu");// 必须创建一项
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public SimpleAdapter getMenuAdapter(String[] menuNameArray, int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
                R.layout.menu2_item, new String[] { "itemImage", "itemText" },
                new int[] { R.id.imageMenuItem, R.id.txtMenuItem});
        return simperAdapter;
    }
}
