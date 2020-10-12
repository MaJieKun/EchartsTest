package com.majiekun.echartstest;

import android.util.Log;
import com.majiekun.echartstest.entity.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.sql.ResultSet;
import java.util.Set;

import com.majiekun.echartstest.entity.coalStock;

public class DBUtil {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    //Android模拟器总是把它自己作为了localhost,也就是说，代码中使用localhost或者
    //127.0.0.1，都是访问模拟器自己。
    private static final String DBURL = "jdbc:mysql://10.21.168.29:3306/echartstest?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=FALSE";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "8941980";

    public static Connection connDB() {
        Connection conn=null;
        PreparedStatement stmt=null;
        try{
            Class.forName(DBDRIVER);// 动态加载类
            try{

                conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("连接数据库失败！");
            }
        }catch (ClassNotFoundException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("DEBUG::connDB():加载驱动失败！");
        }
        return null;
    }

    //返回coalStock
    public static ArrayList<coalStock> getStockCoal(){
        ArrayList<coalStock> infoArr = new ArrayList<coalStock>();
        try {
            // 建立数据库连接
            Connection conn = connDB();
            // mysql简单的查询语句。
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM month_stock_coal");
            ResultSet rs = stmt.executeQuery();

            //将test表中的数据存储到线性表中
            while(rs.next()) {
                coalStock c = new coalStock() ;
                c.setDate(rs.getString("date"));
                c.setFCMC(rs.getString("FCMC"));
                c.setStockCoal(rs.getInt("stock_coal"));
                c.setStockHeat(rs.getInt("stock_heat"));
                c.setStockSulfur(rs.getDouble("stock_sulfur"));
                c.setStockUnitPrice(rs.getDouble("stock_unit_price"));
                infoArr.add(c);
            }
            System.out.println("BEBUG::connDB()::getStockCoal():"+infoArr.size());
            //关闭连接
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoArr;
    }

    //返回coalStock
    public static ArrayList<intakeStock> getIntakeStock(){
        ArrayList<intakeStock> infoArr = new ArrayList<intakeStock>();
        try {
            // 建立数据库连接
            Connection conn = connDB();
            // mysql简单的查询语句。
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM month_intake_test");
            ResultSet rs = stmt.executeQuery();

            //将test表中的数据存储到线性表中
            while(rs.next()) {
                intakeStock c = new intakeStock() ;
                c.setDate(rs.getString("date"));
                c.setCoalFactory(rs.getInt("coal_factory"));
                c.setCoalStove(rs.getInt("coal_stove"));
                c.setStock(rs.getInt("stock"));
                c.setIntakeFactoryList(rs.getDouble("intake_factory_list"));
                c.setIntakeStoveList(rs.getDouble("intake_stove_list"));
                c.setIntakeFactoryCoal(rs.getDouble("intake_factory_coal"));
                c.setIntakeStoveCoal(rs.getDouble("intake_stove_coal"));
                infoArr.add(c);
            }
            System.out.println("BEBUG::connDB()::getIntakeStock().size:"+infoArr.size());
            //关闭连接
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoArr;
    }
//    //测试
//    public static void main(String[] args) {
//        ArrayList<coalStock> cs=(ArrayList<coalStock>) DBUtil.getStockCoal();
//        ArrayList<intakeStock> is=(ArrayList<intakeStock>) DBUtil.getIntakeStock();
//        int cslength=cs.size();
//        for(int i=0;i<cslength;i++){
//            System.out.println(cs.get(i).getFCMC());
//        }
//
//        int islength=is.size();
//        for(int i=0;i<islength;i++){
//            System.out.println(is.get(i).getDate());
//        }
//
//    }

}
