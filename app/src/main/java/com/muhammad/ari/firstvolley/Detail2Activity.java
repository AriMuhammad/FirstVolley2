package com.muhammad.ari.firstvolley;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.muhammad.ari.firstvolley.Url.Url.urlDetail;
import static com.muhammad.ari.firstvolley.Url.Url.urlHDImage;

public class Detail2Activity extends AppCompatActivity {
    ImageView ivDetailGambarHD;
    WebView wvDetailKeterangan;
    String gambar, keterangan, profesi, judul, contentKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        wvDetailKeterangan = (WebView) findViewById(R.id.wv_detail2_keterangan);
        ivDetailGambarHD = (ImageView) findViewById(R.id.iv_detail_gambar_hd2);


        profesi = getIntent().getStringExtra("profesi");
        gambar = getIntent().getStringExtra("hd");
        keterangan = getIntent().getStringExtra("keterangan");
        judul = getIntent().getStringExtra("nama");
        contentKeterangan = getIntent().getStringExtra("keterangan");

        Glide.with(getApplicationContext())
                .load(urlHDImage + gambar)
                .into(ivDetailGambarHD);

        toolbar.setTitle(profesi);
        setSupportActionBar(toolbar);

        Toast.makeText(this, "profesi" + gambar, Toast.LENGTH_SHORT).show();
        wvDetailKeterangan.loadData(contentKeterangan, "text/html", null);
        wvDetailKeterangan.getSettings().setJavaScriptEnabled(true);

    }
}
