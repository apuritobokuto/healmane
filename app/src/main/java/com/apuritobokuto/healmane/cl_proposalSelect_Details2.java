package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*public class cl_menuSelect_Choice extends AppCompatActivity implements View.OnClickListener {*/
public class cl_proposalSelect_Details2 extends AppCompatActivity {
    //private Button readButton;
    private ListView textlist;
    private ArrayAdapter adapter;
    private String ricedata;
    private ArrayList<String> code2;
    private String imgurl;
    private ImageLoader imageLoader;
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposaldetails);
        global = (Global)this.getApplication();

        Button button = (Button) findViewById(R.id.proposaldetails_ch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_proposalSelect_Result.class);
                if(code2.get(0).equals("nodata")) {
                    intent.putExtra("productID","nodata");
                }else {
                    intent.putExtra("productID", code2.get(0));
                }
                startActivity(intent);
            }
        });
        //リスト
        textlist = (ListView) findViewById(R.id.textlist);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        code2 = new ArrayList<String>();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        ricedata=i.getStringExtra("riceID");
        System.out.println(ricedata);
        rereadVolley();
        //サーバーからデータを読み込む

    }

/*
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.readbtn) {
            //サーバに再度アクセス
            rereadVolley();

        }
    }
*/

    /*Volleyを起動データがあれば読み込みを開始*/
    private void rereadVolley() {
        //サーバーのアドレス
        //if (ricedata.equals("nodata")) {
        //    adapter.clear();
        //    code2.clear();
        //    adapter.add("ごはんは選択しません");
        //    code2.add("nodata");
        //    textlist.setAdapter(adapter);
        //    adapter.notifyDataSetChanged();
        //    global.menu2="nodata";
        //} else {

            String POST_URL = "http://10.0.2.2/apuritobokuto/androidphp/detail.php";

            //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest request = new StringRequest(Request.Method.POST, POST_URL,
                    // 通信成功
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                                System.out.println("001");
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("status") == 1) {
                                    System.out.println("002");
                                    //if(jsonObject.getInt("status")==1){
                                    JSONObject menuInfo = jsonObject.getJSONObject("result");
                                    System.out.println("003");
                                    ChangeListView(menuInfo);
                                    // }  //リストを更新する
                                }
                            } catch (JSONException e) {
                                //error
                                System.out.println("error");
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
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", ricedata);
                    return params;
                }
            };

            queue.add(request);
        //}


    }


    private void ChangeListView(JSONObject response) {
        try {
            //Jsonデータを取得
            JSONArray count = response.getJSONArray("SQL_TEST");
            adapter.clear();
            code2.clear();

            //Jsonデータからリストを作成
            //for (int i = 0; i < count.length(); i++) {
            JSONObject data = count.getJSONObject(0);
            adapter.add(data.getString("menu") +" "+ data.get("money")+"円"
                    +"\nカロリー"+data.get("calory")+"kcal\n"
                    +" 赤"+data.get("red")+" 緑"+data.get("green") +" 黄"+data.get("yellow"));
            // System.out.println(i+":"+data.getString("code"));
            code2.add(data.getString("code"));
            imgurl="http://10.0.2.2/apuritobokuto/"+data.getString("img");
            System.out.println("code:"+code2.get(0));
            global.menu2 = code2.get(0);
            global.green2=data.getString("green");
            RequestQueue qu = Volley.newRequestQueue(getApplicationContext());
            imageLoader = new ImageLoader(qu,new JpgCache());
            NetworkImageView menuimage = (NetworkImageView)findViewById(R.id.menuimage);
            menuimage.setImageUrl(imgurl,new ImageLoader(qu, new JpgCache()));
            //System.out.println("code[]="+code[i]);
            //}

            textlist.setAdapter(adapter);
            // textlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //     public void onItemClick(AdapterView<?>parent, View view, int position,long id) {
            //         String msg = (String)textlist.getItemAtPosition(position);
            //         Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();//msg
            //         Intent intent = new Intent(getApplication(), cl_proposalSelect_Rice.class);
            //         intent.putExtra("productID",code2.get(position));
            //         startActivity(intent);
            //     }
            // });


            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplication(),"大変申し訳ございません、DBエラーです。\n 最初からやり直してみてください。",Toast.LENGTH_SHORT).show();
        }
    }


}