package com.majiekun.echartstest;

import com.majiekun.echartstest.entity.coalStock;

import android.app.Activity;
import android.support.v7.app.AppCompatCallback;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import android.os.Build;
import org.json.*;

public class MainActivity  extends Activity implements OnClickListener {
//OnClickListener
    private Button linechart_bt;
    private Button barchart_bt;
    private Button piechart_bt;
    private Button testjs_bt;
    private Button swich_bt;
    private WebView chartshow_wb ;
    private ArrayList<coalStock> cs = new  ArrayList<coalStock>();
    private JSONArray jsonArray = new JSONArray();
    private Map<String,Object> coalMap = new HashMap<String,Object>();
    public coalStock test=new coalStock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                cs = (ArrayList<coalStock>) DBUtil.getStockCoal();
                System.out.println("DEBUG::MainActivity::onCreate: cs.size() = " + cs.size());
            }
        }).start();
        initView();
    }


    private void initView() {
        linechart_bt=(Button)findViewById(R.id.linechart_bt);
        barchart_bt = (Button) findViewById(R.id.barchart_bt);
        testjs_bt = (Button) findViewById(R.id.testjs_bt);
        piechart_bt=(Button)findViewById(R.id.piechart_bt);
        swich_bt = (Button) findViewById(R.id.swich_bt);
        linechart_bt.setOnClickListener(this);
        barchart_bt.setOnClickListener(this);
        testjs_bt.setOnClickListener(this);
        piechart_bt.setOnClickListener(this);
        swich_bt.setOnClickListener(this);
        chartshow_wb = (WebView) findViewById(R.id.chartshow_wb);
        //进行的一堆设置
        //适应分辨率
        chartshow_wb.getSettings().setUseWideViewPort(true);
        //设置编码
        chartshow_wb.getSettings().setDefaultTextEncodingName("UTF-8");
        //开启本地文件读取（默认为true，不设置也可以）
        chartshow_wb.getSettings().setAllowFileAccess(true);
        //开启远程调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
                //开启JS脚本支持
        chartshow_wb.getSettings().setJavaScriptEnabled(true);
        chartshow_wb.loadUrl("file:///android_asset/testjs.html");

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        chartshow_wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });

        //coalStock test = new coalStock();
//        test.setDate("2015-5-10");
//        test.setStockHeat(500);
//        test.setStockCoal(700);
//        test.setStockSulfur(23.12);
//        test.setStockUnitPrice(250.26);
//        cs.add(test);
//
//        coalStock test2 = new coalStock();
//        test2.setDate("2015-5-11");
//        test2.setStockHeat(500);
//        test2.setStockCoal(700);
//        test2.setStockSulfur(23.12);
//        test2.setStockUnitPrice(250.26);
//        cs.add(test2);
//        chartshow_wb.addJavascriptInterface(test, "coalStock");
        /*创建装载月份数组*/

//        String date=new String();
//        String[] coalStock=new String[cs.size()];
//        String[] heatStock=new String[cs.size()];
//        String[] unitPrice=new String[cs.size()];
//        String[] sulfurStock=new String[cs.size()];
//        /*定义数组下标*/
//        int i=0;
//        /*遍历数据库数据*/
//        for (coalStock temp : cs) {
//            /*将月份放入数组*/
//            date[i]=temp.getDate();
//            coalStock[i]=String.valueOf(temp.getStockCoal());
//            heatStock[i]=String.valueOf(temp.getStockHeat());
//            unitPrice[i]=String.valueOf(temp.getStockUnitPrice());
//            sulfurStock[i]=String.valueOf(temp.getStockSulfur());
//            i++;
//        }
//        /*将数组存入集合中*/
//        coalMap.put("date", date);
//        coalMap.put("coalStock", coalStock);
//        coalMap.put("heatStock", heatStock);
//        coalMap.put("unitPrice", unitPrice);
//        coalMap.put("sulfurStock", sulfurStock);
//        //将cs传到JS,AndroidtoJS类对象映射到js的coalStock对象
//        chartshow_wb.addJavascriptInterface(this, "coalMap");
        System.out.println(cs);


    }

    //注解表示:JS可以调用android的这个方法
    @JavascriptInterface
    public coalStock getcoalStockObject(int index) {
        return cs.get(index);
    }

    @JavascriptInterface
    public int getcoalStockSize() {
        return cs.size();
    }

    private String utilDecorate(String[] strList) {
        StringBuilder sb = new StringBuilder("[");
        for (String s : strList) {
            sb.append("\""+ s + "\", ");
        }
        sb.append("],");
        return sb.toString();
    }
    private String utilDecorate(int[] l) {
        StringBuilder sb = new StringBuilder("[");
        for (int s : l) {
            sb.append(s);
            sb.append(", ");
        }
        sb.append("],");
        return sb.toString();
    }

    private String utilDecorate(float[] l) {
        StringBuilder sb = new StringBuilder("[");
        for (float s : l) {
            sb.append(s); // 缺少保留两位小数的处理
            sb.append(", ");
        }
        sb.append("],\n");
        return sb.toString();
    }

    private String genSourceData() {
        StringBuilder sb = new StringBuilder();
        String[] labels = {"月份", "库存煤量", "库存热值", "库存单价", "库存硫分"};
        sb.append("[" + utilDecorate(labels));

        /*定义数组下标*/
        int i=0;
        /*遍历数据库数据*/
        for (coalStock temp : cs) {
            /*将月份放入数组*/
            sb.append("[\"" + temp.getDate() + "\", ");
            sb.append(temp.getStockCoal()/100.0);
            sb.append(", ");
            sb.append(temp.getStockHeat()/10.0);
            sb.append(", ");
            sb.append(temp.getStockUnitPrice());
            sb.append(", ");
            sb.append(temp.getStockSulfur());
            sb.append("],\n");
            i++;
        }
        sb.append("]");
        System.err.println(sb.toString());
        return sb.toString();
    }
    @Override
    public void onClick(View v) {
        String source_str = genSourceData();
        switch (v.getId()) {
            case R.id.swich_bt:
                Intent intent = new Intent();
                intent.setClass(com.majiekun.echartstest.MainActivity.this, IntakeFacrotyStoveDataActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
                break;
            case R.id.testjs_bt:
                chartshow_wb.loadUrl("javascript:callJS();");
                break;
            case R.id.linechart_bt:
                System.out.println("DEBUG::MainActivity::onCreate: cs.size() = " + cs.size());
                //String source_str = genSourceData();
                chartshow_wb.loadUrl("javascript:createChart('line'," + source_str +");");
                break;
            case R.id.barchart_bt:
                chartshow_wb.loadUrl("javascript:createChart('bar'," + source_str +");");
                break;
            case R.id.piechart_bt:
                chartshow_wb.loadUrl("javascript:doCreateChart();");
                break;
            default:
                break;
        }
    }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    Thread.sleep(2000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                chartshow_wb.loadUrl("javascript:createBarChart([98,98,76,54,32,34])");
//            }
//        }).start();
    }






































































