package com.majiekun.echartstest;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.majiekun.echartstest.entity.intakeStock;
import java.util.ArrayList;

public class IntakeFacrotyStoveDataActivity extends Activity implements OnClickListener {
    private ArrayList<intakeStock> is = new ArrayList<>();
    private Button line_br;
    private Button bar_br;
    private WebView chartshow_wb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake_facroty_stove_data);
        new Thread(new Runnable() {
            @Override
            public void run() {
                is = (ArrayList<intakeStock>) DBUtil.getIntakeStock();
                System.out.println("DEBUG::IntakeFactoryStoveActivity::onCreate: is.size() = " + is.size());
            }
        }).start();
        initView();
    }
    private void initView() {
        line_br = (Button)findViewById(R.id.line_bt);
        bar_br = (Button)findViewById(R.id.bar_bt);
        chartshow_wb = (WebView) findViewById(R.id.chartshow_wb);
        line_br.setOnClickListener(this);
        bar_br.setOnClickListener(this);

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
        chartshow_wb.loadUrl("file:///android_asset/intakeStock.html");
    }
    private String utilDecorate(String[] strList) {
        StringBuilder sb = new StringBuilder("[");
        for (String s : strList) {
            sb.append("\""+ s + "\", ");
        }
        sb.append("],");
        return sb.toString();
    }

    private String genSourceData() {
        StringBuilder sb = new StringBuilder();
        String[] labels = {"月份", "入厂煤量", "入炉煤量", "库存", "入厂标量","入炉标量","入厂标煤量","入炉标煤量"};
        sb.append("[" + utilDecorate(labels));

        /*遍历数据库数据*/
        for (intakeStock temp : is) {
            /*将月份放入数组*/
            sb.append("[\"" + temp.getDate() + "\", ");
            sb.append(temp.getIntakeFactoryCoal());
            sb.append(", ");
            sb.append(temp.getIntakeStoveCoal());
            sb.append(", ");
            sb.append(temp.getStock());
            sb.append(", ");
            sb.append(temp.getIntakeFactoryList());
            sb.append(", ");
            sb.append(temp.getIntakeStoveList());
            sb.append(", ");
            sb.append(temp.getCoalFactory());
            sb.append(", ");
            sb.append(temp.getCoalStove());
            sb.append("],\n");
        }
        sb.append("]");
        System.err.println(sb.toString());
        return sb.toString();
    }

    @Override
    public void onClick(View v) {
        String sourceData = genSourceData();
        switch (v.getId()){
            case R.id.line_bt:
                chartshow_wb.loadUrl("javascript:createChart('line'," + sourceData+");");
                break;
            case R.id.bar_bt:
                chartshow_wb.loadUrl("javascript:createChart('bar'," + sourceData +");");
                break;
            default:
                break;
        }
    }
}
