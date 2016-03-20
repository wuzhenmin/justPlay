package com.example.zhenmin.justplay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenmin on 2015/12/2.
 */
public class TestActivityForRecycleView extends ActionBarActivity {

    private ListView lvPros;
    private String url = "https://list.tmall.com/search_product.htm?spm=a221t.1710963.8073444875.14.P35nan&cat=50037917&shopType=any&sort=s&style=g&acm=lb-zebra-7499-292762.1003.8.427962&search_condition=23&promo=43906&industryCatId=50026245&active=1&from=sn_1_rightnav&scm=1003.8.lb-zebra-7499-292762.ITEM_14417575456466_427962";
    private List<Map<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        lvPros = (ListView) findViewById(R.id.show_pros_lv);
        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connection conn = Jsoup.connect(url);
            // 修改http包中的header,伪装成浏览器进行抓取
            conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
            Document doc = null;
            try {
                doc = conn.get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements elements = doc.select("div.product-iWrap");
            for(Element element : elements) {
                String price = element.getElementsByClass("productPrice").text();
                String name = element.getElementsByClass("productTitle").text();
                Map<String, Object> map = new HashMap<>();
                map.put("price", price);
                map.put("name", name);
                list.add(map);
            }
            // 执行完毕后给handler发送一个空消息
            handler.sendEmptyMessage(0);
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 收到消息后执行handler
            show();
        }
    };

    // 将数据填充到ListView中
    private void show() {
            SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.pro_ceil,
                    new String[]{"price", "name"},
                    new int[]{R.id.pro_price,  R.id.pro_name});
        lvPros.setAdapter(adapter);
    }
}
