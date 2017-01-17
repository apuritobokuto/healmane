package com.apuritobokuto.healmane;

import android.content.ContentValues;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import android.widget.EditText;
import java.util.ArrayList;

/*public class cl_menuSelect_Choice extends AppCompatActivity implements View.OnClickListener {*/
public class cl_menuSelect_Choice extends AppCompatActivity {
    //private Button readButton;
    private ListView proposallist;
    private ArrayAdapter adapter;
    private String data;
    private ArrayList<String> code1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposalchoice);

        //リスト
        proposallist = (ListView) findViewById(R.id.proposallist);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        code1 = new ArrayList<String>();
    }




    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        data=i.getStringExtra("data");
        System.out.println(data);
        /*list*/

        //サーバーからデータを読み込む
        rereadVolley();
    }


    /*Volleyを起動データがあれば読み込みを開始*/
    private void rereadVolley() {
        //サーバーのアドレス
        String POST_URL = "http://10.0.2.2/apuritobokuto/androidphp/menulist.php";

        //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, POST_URL,
                // 通信成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                            System.out.println("001");
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getInt("status")==1) {
                                System.out.println("002");
                                //if(jsonObject.getInt("status")==1){
                                JSONObject menuInfo = jsonObject.getJSONObject("result");
                                System.out.println("003");
                                ChangeListView(menuInfo);
                                // }  //リストを更新する
                            }
                        }catch(JSONException e) {
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
        ){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String>params=new HashMap<String,String>();
                params.put("category",data);
                return params;
            }
        };

        queue.add(request);
    }


    private void ChangeListView(JSONObject response) {
        try {
            //Jsonデータを取得
            JSONArray count = response.getJSONArray("SQL_TEST");
            adapter.clear();
            code1.clear();

            //Jsonデータからリストを作成
            for (int i = 0; i < count.length(); i++) {
                JSONObject data = count.getJSONObject(i);
                adapter.add(data.getString("menu") + "\n" + data.get("money")+"円");
                System.out.println(i+":"+data.getString("code"));
                code1.add(data.getString("code"));
                System.out.println("code:"+code1.get(i));
                //System.out.println("code[]="+code[i]);
            }

            proposallist.setAdapter(adapter);
            proposallist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?>parent, View view, int position,long id) {
                    String msg = (String)proposallist.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();//msg
                    Intent intent = new Intent(getApplication(), cl_menuSelect_Details.class);
                    //intent.putExtra("productID",code1.get(position));
                    intent.putExtra("productID",code1.get(position));
                    startActivity(intent);
                }
            });


            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

