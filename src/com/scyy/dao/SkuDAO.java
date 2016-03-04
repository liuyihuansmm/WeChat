package com.scyy.dao;

import com.scyy.beans.SkuBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LYH on 2016/3/2.
 */
public class SkuDAO {

    private static Connection         connection;
    private static PreparedStatement  stmt;
    private static ResultSet          result;



    public static  List<SkuBean> queryDescr(String descr) {
        List<SkuBean> list = new ArrayList<SkuBean>();
        StringBuilder sql = new StringBuilder("select descr,susrsyx46,susrsyx41,susrsyx28 from sku where descr like ?");
        connection = BaseDAO.getCon();
        try {
            stmt =connection.prepareStatement(sql.toString());
            stmt.setString(1,"%"+descr+"%");
            result = stmt.executeQuery();
            while(result.next()) {
                SkuBean temp = new SkuBean();
                temp.setDescr(result.getString("descr"));
                temp.setSusrsyx46(result.getString("susrsyx46"));
                temp.setSusrsyx41(result.getString("susrsyx41"));
                temp.setSusrsyx28(result.getString("susrsyx28"));
                list.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        return list;
    }

}
