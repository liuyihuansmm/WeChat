package com.test;


import com.scyy.beans.SkuBean;
import com.scyy.dao.SkuDAO;

import java.util.List;

/**
 * Created by LYH on 2016/2/26.
 */
public class Test {
    public  static  void main(String[] args){
        List<SkuBean> list = SkuDAO.queryDescr("匹多");
        System.out.println(list);
    }
}
