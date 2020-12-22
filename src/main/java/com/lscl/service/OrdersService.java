package com.lscl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class OrdersService {

    @Autowired
    @Qualifier("ordersDS")
    private AtomikosDataSourceBean ordersDS;            //RM1

    @Autowired
    @Qualifier("storeDS")
    private AtomikosDataSourceBean storeDS;             //RM2

    @Transactional
    public void add(Integer flag) throws Exception {
        Connection ordersConn = null;
        Connection storeConn = null;

        try {
            ordersConn = ordersDS.getConnection();
            storeConn = storeDS.getConnection();

            // 订单+1
            String ordersSQL = "update t_orders set count=count+1";

            // 库存-1
            String storeSQL = "update t_store set count=count-1";

            // 执行订单+1
            Statement ordersState = ordersConn.createStatement();
            ordersState.execute(ordersSQL);

            if (flag == 500) {
                int i = 1 / 0;          // 模拟异常
            }

            // 执行库存-1
            Statement storeState = storeConn.createStatement();
            storeState.execute(storeSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (ordersConn != null) {
                ordersConn.close();
            }

            if (storeConn != null) {
                storeConn.close();
            }
        }
    }
}