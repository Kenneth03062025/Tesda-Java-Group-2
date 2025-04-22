package com.app.service.implementations;

import com.app.model.Setup;
import com.app.service.interfaces.SetupService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import static com.app.util.FunctionWithTryCatch.tryCatchExecute;


public class SetupServiceImpl implements SetupService {


    @Override
    public void showSetup() {
        String query = "SELECT * FROM setup" ;
        Setup setup = new Setup();

        DBConnection con = new DBConnection();


        RiskyFunction func = () -> {
            Statement statement;
            ResultSet resultSet;
            statement = con.getConnection().createStatement();
            resultSet =  statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String identification_name = resultSet.getString(2) ;
                String identification_prefix = resultSet.getString(3);
                int starting_number = resultSet.getInt(4);
                int current_number = resultSet.getInt(5);

                System.out.print(id);
                System.out.print(" | ");
                System.out.print(identification_name + "\t\t\t");
                System.out.print(" | ");
                System.out.print(identification_prefix + "\t");
                System.out.print(" | ");
                System.out.print(starting_number + "\t\t\t");
                System.out.print(" | ");
                System.out.print(current_number + "\t\t\t");
                System.out.print(" | ");
                System.out.println();
            }
//            preparedStatement.set(con.connection.prepareStatement(query));
        };

        tryCatchExecute(con, func);

//        con.setConnection(connection);
//        statement = con.createStatement();



    }

//    public void tryCatchShowSetup(){
//
//    }
}
