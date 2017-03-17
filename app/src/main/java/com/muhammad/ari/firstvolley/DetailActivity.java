package com.muhammad.ari.firstvolley;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.muhammad.ari.firstvolley.Url.Url.url;
import static com.muhammad.ari.firstvolley.Url.Url.urlDetail;
import static com.muhammad.ari.firstvolley.Url.Url.urlHDImage;
import static com.muhammad.ari.firstvolley.Url.Url.urlImage;

public class DetailActivity extends AppCompatActivity {
    ImageView ivDetailGambarHD;
    TextView tvDetailJudul, tvDetailProfesi;
    WebView wvDetailKeterangan;
    String gambar, keterangan, profesi, judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivDetailGambarHD = (ImageView) findViewById(R.id.iv_detail_gambar_hd);
        tvDetailJudul = (TextView) findViewById(R.id.tv_detail_judul);
        tvDetailProfesi = (TextView) findViewById(R.id.tv_detail_profesi);
        wvDetailKeterangan = (WebView) findViewById(R.id.wv_detail_keterangan);


        profesi = getIntent().getStringExtra("profesi");
        gambar = getIntent().getStringExtra("hd");
        keterangan = getIntent().getStringExtra("keterangan");
        judul = getIntent().getStringExtra("nama");

        tvDetailJudul.setText(judul);
        tvDetailProfesi.setSingleLine(false);
        tvDetailProfesi.setText(profesi);

//        ======================= mengload gambar ==============================================
//        Glide.with(getApplicationContext())
//                .load(urlHDImage + gambar)
//                .into(ivDetailGambarHD);

//        wvDetailKeterangan.loadUrl(urlDetail + keterangan);
//        wvDetailKeterangan.setInitialScale(10000);
//        wvDetailKeterangan.getSettings().setLoadWithOverviewMode(true);
//        wvDetailKeterangan.getSettings().setUseWideViewPort(true);
//        wvDetailKeterangan.getSettings().setJavaScriptEnabled(true);
//        wvDetailKeterangan.setBackgroundColor(0);
//        wvDetailKeterangan.setVerticalScrollBarEnabled(true);

    }
}
