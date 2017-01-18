package com.apuritobokuto.healmane;


/*public class cl_menuSelect_Choice extends AppCompatActivity implements View.OnClickListener {*/


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ImageLoader;


/*public class cl_menuSelect_Choice extends AppCompatActivity implements View.OnClickListener {*/
public class cl_information extends AppCompatActivity {
    //private Button readButton;

    private String imgurl;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

    }


    @Override
    protected void onResume() {
        super.onResume();
        //サーバーからデータを読み込む
        imageload();
    }


    /*Volleyを起動データがあれば読み込みを開始*/


    private void imageload() {

            imgurl = "http://10.0.2.2/apuritobokuto/androidphp/test.png";
            RequestQueue qu = Volley.newRequestQueue(getApplicationContext());
            imageLoader = new ImageLoader(qu, new JpgCache());
            NetworkImageView infoimage = (NetworkImageView) findViewById(R.id.infoimage);
            //infoimage.setErrorImageResId(errorImageResId);
            infoimage.setImageUrl(imgurl, new ImageLoader(qu, new JpgCache()));
            infoimage.setErrorImageResId(R.mipmap.error);

    }



}