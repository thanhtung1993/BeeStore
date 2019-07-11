package com.example.beestore.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.beestore.R;
import com.example.beestore.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {

                }
                finally {
                    Intent itrangchu=new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(itrangchu);
                }
            }
        });
        thread.start();
    }
}

