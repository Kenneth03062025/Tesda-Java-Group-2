package com.app.util;

import com.app.model.Response;

import java.sql.Connection;

public class FunctionWithTryCatch {
        public static void tryCatchExecuteTransact(DBConnection connection, RiskyFunction func) {
        try {
            Connection conn = connection.getConnection();
            conn.setAutoCommit(false);
            func.execute();
            conn.commit();
        }catch (Exception e){
            connection.backOff();
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            connection.disconnect();
        }
    }

    public static void tryCatchExecute(DBConnection connection, RiskyFunction func){
            try {
                func.execute();
            } catch (Exception e){
                System.out.println("Exception caught: " + e.getMessage());
            } finally {
                connection.disconnect();
            }
    }

    public static Response<?> tryCatchAnyResponseExecute(DBConnection connection, RiskyFunctionAnyType func){
            Response<?> res = new Response<>();
        try {
            res = func.execute();
        } catch (Exception e){
            //System.out.println("Exception caught: " + e.getMessage());
            res.setStatus("failed");
            res.setMessage(e.getMessage());
        } finally {
            connection.disconnect();
        }
        return res;
    }

    public static Response<?> tryCatchTransactionalExecute(DBConnection connection, RiskyFunctionAnyType func){
        Response<?> res = new Response<>();
        try {
            Connection conn = connection.getConnection();
            conn.setAutoCommit(false);
            res = func.execute();
            conn.commit();
        }catch (Exception e){
            res.setStatus("failed");
            res.setMessage(e.getMessage());
            connection.backOff();
            //System.out.println("Exception caught: " + e.getMessage());
        } finally {
            connection.disconnect();
        }

        return res;
    }

}
