package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;
import android.content.ContentValues;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import android.content.SharedPreferences;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ImageLoader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class cl_proposalSelect_Result extends AppCompatActivity {

    private ListView textview0;//選択したメニュー
    private ListView textview1;//提案1
    private ListView textview2;//提案2
    private ListView textview3;//提案3
    private ArrayAdapter adapter0;
    private ArrayAdapter adapter1;
    private ArrayAdapter adapter2;
    private ArrayAdapter adapter3;

    private ArrayList<String> code2;
    private ArrayList<String> imgurllist;
    private ArrayList<String> imgurllist1;
    private ArrayList<String> imgurllist2;
    private ArrayList<String> imgurllist3;
    private double green[];
    private double yellow[];
    private double red[];
    private ImageLoader imageLoader1;
    private ImageLoader imageLoader2;
    private ImageLoader imageLoader3;
    private ImageLoader imageLoader4;
    private ImageLoader imageLoader5;
    private ImageLoader imageLoader6;
    private ImageLoader imageLoader7;
    private ImageLoader imageLoader8;
    private String Sum;
    private String dtmp;
    private Date today;

    private SimpleDateFormat dateformat;
    private static final String dkey="date";



    private void savedate(){
        SharedPreferences registered = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor setdate = registered.edit();
        setdate.putString(dkey,dtmp);
        setdate.commit();
    }

    private void insertdb(String day,double r,double y,double g){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplication());
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        System.out.println("insert"+"start");
        insertValues.put("day",day);
        insertValues.put("red",r);
        insertValues.put("yellow",y);
        insertValues.put("green",g);
        System.out.println(insertValues);
        db.insert("healmane",null,insertValues);
        System.out.println("insert"+"end");
    }
    Global global;//買い物カゴ的な役割


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposalresult);
        global = (Global)this.getApplication();
        double a=0.0,b=0.0,sum=0.0;
        a=Double.parseDouble(global.green1);
        b=Double.parseDouble(global.green2);
        System.out.println("a:"+a+"b:"+b);
        sum=(1.0-(a+b))/2.0;//緑は1食1.0目安で2品提案したい
        Sum=String.valueOf(sum);
        System.out.println("Sum:"+Sum);
        today = new Date();
        dateformat = new SimpleDateFormat("dd");
        dtmp=dateformat.format(today);
        System.out.println("date:"+dtmp);



        Button button = (Button) findViewById(R.id.proposaldecide1);
        button.setOnClickListener(new View.OnClickListener() {
          
          @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                savedate();
                insertdb(dtmp,red[0],yellow[0],green[0]);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        Button button2 = (Button) findViewById(R.id.proposaldecide2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                savedate();
                insertdb(dtmp,red[1],yellow[1],green[1]);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        });


        Button button3 = (Button) findViewById(R.id.proposaldecide3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                savedate();
                insertdb(dtmp,red[2],yellow[2],green[2]);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        //リスト
        textview0 = (ListView) findViewById(R.id.textlist0);
        textview1 = (ListView) findViewById(R.id.textlist1);
        textview2 = (ListView) findViewById(R.id.textlist2);
        textview3 = (ListView) findViewById(R.id.textlist3);
        adapter0 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        adapter3 = new ArrayAdapter(this,android.R.layout.simple_list_item_1);

        code2 = new ArrayList<String>();
        imgurllist = new ArrayList<String>();
        imgurllist1 = new ArrayList<String>();
        imgurllist2 = new ArrayList<String>();
        imgurllist3 = new ArrayList<String>();
        green = new double[3];
        yellow = new double[3];
        red = new double[3];

    }


    @Override
    protected void onResume() {
        super.onResume();

        //サーバーからデータを読み込む
        System.out.println("globalmenu1:"+global.menu1);
        System.out.println("globalmenu2:"+global.menu2);
        rereadVolley();
    }




    /*Volleyを起動データがあれば読み込みを開始*/
    private void rereadVolley() {
        //サーバーのアドレス
        String POST_URL = "http://10.0.2.2/apuritobokuto/androidphp/presult.php";

        //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, POST_URL,
                // 通信成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                            System.out.println("001");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectp = new JSONObject(response);
                            if(jsonObject.getInt("status")==1) {
                                System.out.println("002");
                                JSONObject menuInfo = jsonObject.getJSONObject("result");
                                System.out.println("003");
                                ChangeListView(menuInfo);
                               //リストを更新する
                            }

                            if(jsonObjectp.getInt("status")==1) {
                                System.out.println("0022");
                                JSONObject proInfo = jsonObjectp.getJSONObject("proposal");
                                System.out.println("0033");
                                ChangeProposalView(proInfo);
                                //リストを更新する
                            }
                        }catch(JSONException e) {
                            //error
                            System.out.println("json代入error");
                        }
                    }
                },

                // 通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "通信に失敗しました。", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String>params=new HashMap<String,String>();
                params.put("id1",global.menu1);
                params.put("id2",global.menu2);
                params.put("green",Sum);
                return params;
            }
        };

        queue.add(request);
    }


    private void ChangeListView(JSONObject response) {
        try {
            //Jsonデータを取得
                JSONArray count = response.getJSONArray("SQL_TEST1");
                //JSONArray count2 = response.getJSONArray("SQL_TEST2");
                adapter0.clear();
                code2.clear();


                //Jsonデータからリストを作成
                for (int i = 0; i < count.length(); i++) {
                    JSONObject data = count.getJSONObject(i);
                    //JSONObject data2 = count2.getJSONObject(i);
                    adapter0.add(data.getString("menu") + " " + data.get("money") + "円"
                            + "\nカロリー" + data.get("calory") + "kcal\n"
                            + " 赤" + data.get("red") + " 緑" + data.get("green") + " 黄" + data.get("yellow"));

                    imgurllist.add("http://10.0.2.2/apuritobokuto/" + data.getString("img"));
                    String tmp;
                    tmp=data.getString("green");
                    green[0]=Double.parseDouble(tmp);
                    tmp=data.getString("yellow");
                    yellow[0]+=Double.parseDouble(tmp);
                    tmp=data.getString("red");
                    red[0]+=Double.parseDouble(tmp);
                    tmp=data.getString("green");
                    green[1]=Double.parseDouble(tmp);
                    tmp=data.getString("yellow");
                    yellow[1]+=Double.parseDouble(tmp);
                    tmp=data.getString("red");
                    red[1]+=Double.parseDouble(tmp);
                    tmp=data.getString("green");
                    green[2]=Double.parseDouble(tmp);
                    tmp=data.getString("yellow");
                    yellow[2]+=Double.parseDouble(tmp);
                    tmp=data.getString("red");
                    red[2]+=Double.parseDouble(tmp);

                }
                          /*select*/
                RequestQueue qu = Volley.newRequestQueue(getApplicationContext());

                imageLoader1 = new ImageLoader(qu,new JpgCache());
                imageLoader2 = new ImageLoader(qu,new JpgCache());

                NetworkImageView menuimage1 = (NetworkImageView)findViewById(R.id.menuimage1);
                NetworkImageView menuimage2 = (NetworkImageView)findViewById(R.id.menuimage2);
                menuimage1.setImageUrl(imgurllist.get(0),new ImageLoader(qu, new JpgCache()));
                menuimage2.setImageUrl(imgurllist.get(1),new ImageLoader(qu, new JpgCache()));


                textview0.setAdapter(adapter0);
                adapter0.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void ChangeProposalView(JSONObject response) {
        try {
            //Jsonデータを取得

            //if(count==0) {
            JSONArray count = response.getJSONArray("SQL_TEST2");

            adapter1.clear();
            adapter2.clear();
            adapter3.clear();
            code2.clear();


            //Jsonデータからリストを作成
            for (int i = 0; i < count.length(); i++) {
                JSONObject data = count.getJSONObject(i);
                if(i<2) {
                    adapter1.add(data.getString("menu") + " " + data.get("money") + "円"
                            + "\nカロリー" + data.get("calory") + "kcal\n"
                            + " 赤" + data.get("red") + " 緑" + data.get("green") + " 黄" + data.get("yellow"));
                    imgurllist1.add("http://10.0.2.2/apuritobokuto/" + data.getString("img"));
                    String tmp;
                    tmp=data.getString("green");
                    green[0]+=Double.parseDouble(tmp);
                    tmp=data.getString("yellow");
                    yellow[0]+=Double.parseDouble(tmp);
                    tmp=data.getString("red");
                    red[0]+=Double.parseDouble(tmp);
                }else if(i<4){
                    adapter2.add(data.getString("menu") + " " + data.get("money") + "円"
                            + "\nカロリー" + data.get("calory") + "kcal\n"
                            + " 赤" + data.get("red") + " 緑" + data.get("green") + " 黄" + data.get("yellow"));
                    imgurllist2.add("http://10.0.2.2/apuritobokuto/" + data.getString("img"));
                    String tmp;
                    tmp=data.getString("green");
                    green[1]+=Double.parseDouble(tmp);
                    tmp=data.getString("yellow");
                    yellow[1]+=Double.parseDouble(tmp);
                    tmp=data.getString("red");
                    red[1]+=Double.parseDouble(tmp);

                }else{
                    adapter3.add(data.getString("menu") + " " + data.get("money") + "円"
                            + "\nカロリー" + data.get("calory") + "kcal\n"
                            + " 赤" + data.get("red") + " 緑" + data.get("green") + " 黄" + data.get("yellow"));
                    imgurllist3.add("http://10.0.2.2/apuritobokuto/" + data.getString("img"));
                    String tmp;
                    tmp=data.getString("green");
                    green[2]+=Double.parseDouble(tmp);
                    tmp=data.getString("yellow");
                    yellow[2]+=Double.parseDouble(tmp);
                    tmp=data.getString("red");
                    red[2]+=Double.parseDouble(tmp);

                }

            }
                          /*select*/
            RequestQueue qu = Volley.newRequestQueue(getApplicationContext());


            //JSONArray count2 = response.getJSONArray("SQL_TEST2");


            imageLoader3 = new ImageLoader(qu,new JpgCache());
            imageLoader4 = new ImageLoader(qu,new JpgCache());
            imageLoader5 = new ImageLoader(qu,new JpgCache());
            imageLoader6 = new ImageLoader(qu,new JpgCache());
            imageLoader7 = new ImageLoader(qu,new JpgCache());
            imageLoader8 = new ImageLoader(qu,new JpgCache());


            NetworkImageView menuimage3 = (NetworkImageView)findViewById(R.id.menuimage3);
            NetworkImageView menuimage4 = (NetworkImageView)findViewById(R.id.menuimage4);
            menuimage3.setImageUrl(imgurllist1.get(0),new ImageLoader(qu, new JpgCache()));
            menuimage4.setImageUrl(imgurllist1.get(1),new ImageLoader(qu, new JpgCache()));

            NetworkImageView menuimage5 = (NetworkImageView)findViewById(R.id.menuimage5);
            NetworkImageView menuimage6 = (NetworkImageView)findViewById(R.id.menuimage6);
            menuimage5.setImageUrl(imgurllist2.get(0),new ImageLoader(qu, new JpgCache()));
            menuimage6.setImageUrl(imgurllist2.get(1),new ImageLoader(qu, new JpgCache()));

            NetworkImageView menuimage7 = (NetworkImageView)findViewById(R.id.menuimage7);
            NetworkImageView menuimage8 = (NetworkImageView)findViewById(R.id.menuimage8);
            menuimage7.setImageUrl(imgurllist3.get(0),new ImageLoader(qu, new JpgCache()));
            menuimage8.setImageUrl(imgurllist3.get(1),new ImageLoader(qu, new JpgCache()));



            textview1.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
            textview2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();
            textview3.setAdapter(adapter3);
            adapter3.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


