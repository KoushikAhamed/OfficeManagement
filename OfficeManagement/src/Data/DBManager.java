/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.AddressModel;
import Model.AddressDetailsModel;
import Model.TableDisplayModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;

/**
 *
 * @author line 2
 */
public class DBManager {

    Connection con = null;
    List<AddressDetailsModel> mvcList = null;

    public Connection connection() {
        String host = "jdbc:derby://localhost:1527/myDatabase";
        String uName = "koushik";
        String uPassword = "koushik";

        try {
            con = DriverManager.getConnection(host, uName, uPassword);
            //System.out.println("connected");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void insertData(List list) throws SQLException {

        try {
            mvcList = list;
            //int i = 0;
            //PreparedStatement preparedStatement = null;
            PreparedStatement ps1 = null;
            //PreparedStatement preparedStatement2 = null;
            PreparedStatement ps11 = null;
            PreparedStatement ps2 = null;
//            PreparedStatement ps21 = null;
            //ps = connection().prepareStatement("INSERT INTO KOUSHIK.MVC (NAME, DEPARTMENT, DESIGNATION, ID) VALUES (?, ?, ?, ?)");
            ps1 = connection().prepareStatement("INSERT INTO KOUSHIK.EMPLOYEE (ID, NAME, DEPARTMENTID , DESIGNATION) VALUES (?, ?, ?, ?)");
            // preparedStatement2 = connection().prepareStatement("INSERT INTO KOUSHIK.MANAGER (ID , NAME, EMPLOYEEID, DEPARTMENT) VALUES (?, ?, ?, ?)");
            ps11 = connection().prepareStatement("SELECT * FROM KOUSHIK.DEPARTMENT WHERE NAME = ?");
            //ps21 = connection().prepareStatement("SELECT * FROM KOUSHIK.MANAGER WHERE DEPARTMENT = ? ");

            ps2 = connection().prepareStatement("INSERT INTO KOUSHIK.ADDRESS (ID, PRESENTADDRESS , PARMANENTADDRESS ) VALUES (?, ?, ? )");
//            preparedStatement = connection().prepareStatement("INSERT INTO KOUSHIK.MVC (NAME, DEPARTMENT, DESIGNATION, ID) VALUES (?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?)");
            for (AddressDetailsModel mvcModel : mvcList) {

                int departmentId = 0;

                System.out.println(mvcModel.getDepartment());
                ps11.setString(1, mvcModel.getDepartment());
                ResultSet resultSet = ps11.executeQuery();
////                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//                int columnsNumber = resultSetMetaData.getColumnCount();
                while (resultSet.next()) {
                    departmentId = resultSet.getInt(1);
//                    for (int i = 1; i <= columnsNumber; i++) {
//                        if (i > 1) {
//                            System.out.print(",  ");
//                        }
//                        String columnValue = resultSet.getString(i);
//                        System.out.print(columnValue + " " + resultSetMetaData.getColumnName(i));
//                    }
//                    System.out.println("");

//                     System.out.println(resultSet.getString(1));
                    //System.out.println(resultSet.getInt(1));
                }

//                ps21.setString(1, mvcModel.getDepartment());
//                ResultSet resultSet2 = ps21.executeQuery();
//                
//                preparedStatement.setString(1, mvcModel.getName());
//                preparedStatement.setString(2, mvcModel.getDepartment());
//                preparedStatement.setString(3, mvcModel.getDesignation());
//                preparedStatement.setInt(4, mvcModel.getId());
//                preparedStatement.addBatch();
//                resultSet2.getInt(1);
////                resultSet2.getString(2);
                ps1.setInt(1, mvcModel.getId());
                ps1.setString(2, mvcModel.getName());
                ps1.setInt(3, departmentId);
                ps1.setString(4, mvcModel.getDesignation());
                ps1.addBatch();
                
                
//                preparedStatement2.setInt(1, resultSet2.getInt(1));
//                preparedStatement2.setString(2, resultSet2.getString(2));
//                preparedStatement2.setInt(3, mvcModel.getId());
//                preparedStatement2.setString(4, mvcModel.getDepartment());
//                preparedStatement2.addBatch();
            }
//            preparedStatement.executeBatch();
            ps1.executeBatch();
            
            for(AddressDetailsModel mvcModel : mvcList){
                ps2.setInt(1 , mvcModel.getId());
                ps2.setString(2 , mvcModel.getPresentAddress());
                ps2.setString(3 , mvcModel.getParmanentAddress());
                ps2.addBatch();
            }
            ps2.executeBatch();
//            preparedStatement2.executeBatch();

//            if (  i <= mvcList.size()*4 ) {
//               
//                for( int j = 1 ; j <= mvcList.size() ; j++ ){
//                //ps.setInt(i , mvcList.get(j).getId());
//                preparedStatement.setString(i, mvcList.get(j).getName() );
//                preparedStatement.setString(i++, mvcList.get(j).getDepartment());
//                preparedStatement.setString(i+2, mvcList.get(j).getDesignation());
//                preparedStatement.setInt(i+3, mvcList.get(j).getId());
//                }
//            }
//            if (i <= mvcList.size() * 4) {
//                for (int j = 0; j < mvcList.size(); j++) {
//                //ps.setInt(i , mvcList.get(j).getId());
//
//                    preparedStatement.setString(i=i+1, mvcList.get(j).getName());
//                    preparedStatement.setString(i = i + 1, mvcList.get(j).getDepartment());
//                    preparedStatement.setString(i = i + 1, mvcList.get(j).getDesignation());
//                    preparedStatement.setInt(i = i + 1, mvcList.get(j).getId());
//
//                }
//
//                preparedStatement.executeUpdate();
//
//            }
            //JOptionPane.showConfirmDialog(null, "Inserted Successfully!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.connection().close();
        }
    }

    public void updated(List list) throws SQLException {
        try {
            mvcList = list;
            PreparedStatement ps = null;
            ps = connection().prepareStatement("UPDATE KOUSHIK.MVC SET NAME=? , DEPARTMENT = ? , DESIGNATION = ? WHERE ID = ? ");
            for (AddressDetailsModel mvcModel : mvcList) {
                ps.setString(1, mvcModel.getName());
                ps.setString(2, mvcModel.getDepartment());
                ps.setString(3, mvcModel.getDesignation());
                ps.setInt(4, mvcModel.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(int id, String name, String department, String designation) throws SQLException {

        try {
            PreparedStatement ps;
            ps = connection().prepareStatement("INSERT INTO KOUSHIK.MVC (NAME, DEPARTMENT, DESIGNATION, ID) VALUES (?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, designation);
            ps.setInt(4, id);
            ps.executeUpdate();

            //JOptionPane.showConfirmDialog(null, "Inserted Successfully!!!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.connection().close();
        }
    }

    public void update(int id, String name, String department, String designation) throws SQLException {

        try {
            PreparedStatement ps;
            ps = connection().prepareStatement("UPDATE KOUSHIK.MVC SET NAME=? , DEPARTMENT = ? , DESIGNATION = ? WHERE ID = ? ");
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, designation);
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.connection().close();
        }
    }

    public void delete(List list) throws SQLException {
        try {
            mvcList = list;
            PreparedStatement ps;
            ps = connection().prepareStatement("DELETE FROM KOUSHIK.MVC WHERE ID = ? ");
            for (AddressDetailsModel mvcModel : mvcList) {
                ps.setInt(1, mvcModel.getId());
                ps.addBatch();
            }

            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.connection().close();
        }
    }
    public String departmentName(int id) {
        String department = null;
        int departmentId = id;
        try {
            
            PreparedStatement preparedStatement2 = connection().prepareStatement(" select * from KOUSHIK.DEPARTMENT WHERE ID = ? ");
            
            
            preparedStatement2.setInt(1, departmentId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                department = resultSet2.getString(2);
                //managerId = resultSet2.getInt(3);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }

    public int managerId(int id) {
        int managerId = 0;
        int departmentId = id;
        try {
            
            PreparedStatement preparedStatement2 = connection().prepareStatement(" select * from KOUSHIK.DEPARTMENT WHERE ID = ? ");
            
            
            preparedStatement2.setInt(1, departmentId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                int d = resultSet2.getInt(3);
                managerId = resultSet2.getInt(3);
                //managerId = resultSet2.getInt(3);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return managerId;
    }
    public String managerName(int id) {
        int managerId = id;
        String managertName = null;
        try {
            
            PreparedStatement preparedStatement2 = connection().prepareStatement(" select * from KOUSHIK.MANAGER WHERE ID = ? ");
            
            
            preparedStatement2.setInt(1, managerId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                managertName = resultSet2.getString(2);
               

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return managertName;
    }
    public String presentAddress(int id) {
        int userId = id;
        String presentAddress = null;
        try {
            
            PreparedStatement preparedStatement2 = connection().prepareStatement(" select * from KOUSHIK.ADDRESS WHERE ID = ? ");
            
            
            preparedStatement2.setInt(1, userId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                presentAddress = resultSet2.getString(2);
               

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return presentAddress;
    }
    public String parmanentAddress(int id) {
        int userId = id;
        String parmanentAddress = null;
        try {
            
            PreparedStatement preparedStatement2 = connection().prepareStatement(" select * from KOUSHIK.ADDRESS WHERE ID = ? ");
            
            
            preparedStatement2.setInt(1, userId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                parmanentAddress = resultSet2.getString(3);
               

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parmanentAddress;
    }
//    public List<AddressModel> address( int id) throws SQLException {
//        int userId = id ;
//        List<AddressModel> tableList = new LinkedList<AddressModel>();
//        AddressModel addressModel;
//        
//        PreparedStatement preparedStatement = connection().prepareStatement(" select * from KOUSHIK.ADDRESS WHWRE ID = ? ");
//        preparedStatement.setInt(1 , userId );
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            
//                addressModel = new AddressModel();
//                
//                addressModel.setPresentAddress(resultSet.getString(2));
//                addressModel.setParmanentAddress(resultSet.getString(3));
//                tableList.add(addressModel);
//        }
//        return tableList;
//    }
    public List<TableDisplayModel> table() throws SQLException {
        List<TableDisplayModel> tableList = new LinkedList<TableDisplayModel>();
        TableDisplayModel tableDisplayModel;
        
        PreparedStatement preparedStatement = connection().prepareStatement(" select * from KOUSHIK.EMPLOYEE");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
                int id = resultSet.getInt(1);
                
                String name = resultSet.getString(2);
                int departmentId = resultSet.getInt(3);
                String designation = resultSet.getString(4);

                tableDisplayModel = new TableDisplayModel();
                tableDisplayModel.setId(id);
                tableDisplayModel.setName(name);
                tableDisplayModel.setDepartmentId(departmentId);
                tableDisplayModel.setDesignation(designation);
                tableList.add(tableDisplayModel);
        }
        return tableList;
    }

    public List<TableDisplayModel> tableDisplay() throws SQLException {
        List<TableDisplayModel> tableList = new LinkedList<TableDisplayModel>();
        List<AddressDetailsModel> mvcList = new LinkedList<>();
        TableDisplayModel tableDisplayModel;
        AddressDetailsModel mvcModel;

        try {

            PreparedStatement preparedStatement = connection().prepareStatement(" select * from KOUSHIK.EMPLOYEE");
            ResultSet resultSet = preparedStatement.executeQuery();

            String department = null;
            String manager = null;
            int departmentId = 0;
            int managerId = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                departmentId = resultSet.getInt(3);
                String name = resultSet.getString(2);
                String designation = resultSet.getString(4);

                mvcModel = new AddressDetailsModel();
                mvcModel.setId(id);
                mvcModel.setName(name);
                mvcModel.setDepartment(department);
                mvcModel.setDesignation(designation);
                mvcList.add(mvcModel);

//                tableDisplayModel = new TableDisplayModel();
//                tableDisplayModel.setId(id);
//                tableDisplayModel.setName(name);
//                tableDisplayModel.setDepartment(department);
//                tableDisplayModel.setDesignation(designation);
//                tableDisplayModel.setManagerName(manager);
//                tableList.add(tableDisplayModel);
//                mvcModel = new AddressDetailsModel();
//                mvcModel.setName(name);
//                mvcModel.setDepartment(department);
//                mvcModel.setDesignation(designation);
//                mvcModel.setId(id);
//                mvcList.add(mvcModel);
            }
            PreparedStatement preparedStatement2 = connection().prepareStatement(" select * from KOUSHIK.DEPARTMENT WHERE ID = ? ");

            preparedStatement2.setInt(1, departmentId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                department = resultSet2.getString(2);
                managerId = resultSet2.getInt(3);

            }
            ResultSet resultSet1 = null;
            PreparedStatement preparedStatement1 = connection().prepareStatement(" select * from KOUSHIK.MANAGER WHERE ID = ? ");
            preparedStatement1.setInt(1, managerId);
            resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                manager = resultSet1.getString(2);
            }

        } catch (SQLException ex) {
//            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {

            this.connection().close();
        }
        return tableList;
    }

    
}

//    public void delete(int id){
//        PreparedStatement preparedStatement;
//        preparedStatement = connection().prepareStatement("DELETE ");
//    }

