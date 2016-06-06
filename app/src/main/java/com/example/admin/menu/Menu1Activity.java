package com.example.admin.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.admin.R;

public class Menu1Activity extends AppCompatActivity {

    TextView txtGender,txtHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtGender=(TextView)findViewById(R.id.txtGender);
        txtHobby=(TextView)findViewById(R.id.txtHobby);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//加载菜单
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.male:
            case R.id.female:
                item.setChecked(true);
                txtGender.setText(item.getTitle());
                break;
            case R.id.sport:
            case R.id.sing:
            case R.id.trip:
                item.setChecked(!item.isChecked());
                if (item.isChecked())
                    txtHobby.setText(txtHobby.getText().toString() + item.getTitle());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
