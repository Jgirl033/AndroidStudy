package com.example.admin.layout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.admin.R;


public class MainActivity extends AppCompatActivity {

    private Button btnLinear;
    private Button btnTable;
    private Button btnGrid;
    private Button btnFrame;
    private Button btnReturn;
    private View layoutInclude;
    private View layoutLinear;
    private View layoutGrid;
    private View layoutTable;
    private View layoutFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLinear=(Button)findViewById(R.id.btnLinear);
        btnTable=(Button)findViewById(R.id.btnTable);
        btnGrid=(Button)findViewById(R.id.btnGrid);
        btnFrame=(Button)findViewById(R.id.btnFrame);
        layoutLinear=(View)findViewById(R.id.include_linear);
        layoutInclude=(View)findViewById(R.id.include_layout);
        layoutFrame=(View)findViewById(R.id.include_frame);
        layoutGrid=(View)findViewById(R.id.include_grid);
        layoutTable=(View)findViewById(R.id.include_table);

        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInclude.setVisibility(View.GONE);
                layoutLinear.setVisibility(View.VISIBLE);
                layoutGrid.setVisibility(View.GONE);
                layoutTable.setVisibility(View.GONE);
                layoutFrame.setVisibility(View.GONE);
                btnReturn.setVisibility(View.VISIBLE);
            }
        });

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInclude.setVisibility(View.GONE);
                layoutLinear.setVisibility(View.GONE);
                layoutGrid.setVisibility(View.GONE);
                layoutTable.setVisibility(View.VISIBLE);
                layoutFrame.setVisibility(View.GONE);
                btnReturn.setVisibility(View.VISIBLE);
            }
        });

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInclude.setVisibility(View.GONE);
                layoutLinear.setVisibility(View.GONE);
                layoutGrid.setVisibility(View.VISIBLE);
                layoutTable.setVisibility(View.GONE);
                layoutFrame.setVisibility(View.GONE);
                btnReturn.setVisibility(View.VISIBLE);

            }
        });

        btnFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInclude.setVisibility(View.GONE);
                layoutLinear.setVisibility(View.GONE);
                layoutGrid.setVisibility(View.GONE);
                layoutTable.setVisibility(View.GONE);
                layoutFrame.setVisibility(View.VISIBLE);
                btnReturn.setVisibility(View.VISIBLE);

            }
        });

        btnReturn=(Button)this.findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInclude.setVisibility(View.VISIBLE);
                layoutLinear.setVisibility(View.GONE);
                layoutGrid.setVisibility(View.GONE);
                layoutTable.setVisibility(View.GONE);
                layoutFrame.setVisibility(View.GONE);
                btnReturn.setVisibility(View.GONE);
            }
        });


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
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
