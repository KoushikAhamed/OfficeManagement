/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Data.DBManager;
import Model.AddressModel;
import Model.AddressDetailsModel;
import Model.TableDisplayModel;
//import UI.MvcFirstUi;
import java.sql.SQLException;
//import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author line 2
 */
public class OfficeManagementController {

    DBManager mvcData = null;
    List<AddressDetailsModel> textFieldData = null;
    //MvcModel mvcModel=new AddressDetailsModel();

//    public void Insert(String name, String department, String designation) throws SQLException{
//        mvcModel.setName(name);
//        mvcModel.setDepartment(department);
//        mvcModel.setDepartment(designation);
//        mvcData.insert(name, department, designation);
//    }
    public void insertToControl(List list) {

        try {

            if (mvcData == null) {
                mvcData = new DBManager();

                if (mvcData.connection().isClosed() || mvcData.connection() == null) {
                    mvcData.connection();
                }
            }

            textFieldData = list;
            mvcData.insertData(textFieldData);
//            for (int i = 0; i < textFieldData.size(); i++) {
//                int id = textFieldData.get(i).getId();
//                String name = textFieldData.get(i).getName();
//                String department = textFieldData.get(i).getDepartment();
//                String designation = textFieldData.get(i).getDesignation();
//                mvcData.insert(id, name, department, designation);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(OfficeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertToDatabase(int id, String name, String department, String designation) {

        try {

            if (mvcData == null) {
                mvcData = new DBManager();

                if (mvcData.connection().isClosed() || mvcData.connection() == null) {
                    mvcData.connection();
                }
            }

            mvcData.insert(id, name, department, designation);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateToData(List list) {
        try {
                if (mvcData == null) {
                    mvcData = new DBManager();
                    if (mvcData.connection().isClosed() || mvcData.connection() == null) {
                        mvcData.connection();
                    }
                }
                textFieldData = list;
                mvcData.updated(textFieldData);
            }catch (SQLException ex) {
                Logger.getLogger(OfficeManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    

    public void updateDb(int id, String name, String department, String designation) {
        try {
            if (mvcData == null) {
                mvcData = new DBManager();
                if (mvcData.connection().isClosed() || mvcData.connection() == null) {
                    mvcData.connection();
                }
            }
            mvcData.update(id, name, department, designation);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(List list) {
        try {
            textFieldData =list;
            if (mvcData == null) {
                mvcData = new DBManager();
                if (mvcData.connection().isClosed() || mvcData.connection() == null) {
                    mvcData.connection();
                }
            }
            mvcData.delete(textFieldData);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tableContent(DefaultTableModel model) {
        try {
            List<TableDisplayModel> mvcList = null;
            List<AddressModel> addressList = null;
            if (mvcData == null) {
                mvcData = new DBManager();
                if (mvcData.connection().isClosed()) {
                    mvcData.connection();
                }
            }
            mvcList = mvcData.table();
            if (!mvcList.isEmpty()) {
                for (int i = 0; i < mvcList.size(); i++) {
                    
                    String id = Integer.toString(mvcList.get(i).getId());
                    String name = mvcList.get(i).getName();
                    int departmentId = mvcList.get(i).getDepartmentId();
                    String department = mvcData.departmentName(departmentId);
                    //String department = Integer.toString(mvcList.get(i).getDepartmentId());
                    String designation = mvcList.get(i).getDesignation();
                    int managerId = mvcData.managerId(departmentId);
                    String managerName = mvcData.managerName(managerId);
                    String presentAddress = mvcData.presentAddress(mvcList.get(i).getId());
                    String parmanentAddress = mvcData.parmanentAddress(mvcList.get(i).getId()) ; 
//                    addressList  = mvcData.address(mvcList.get(i).getId());
//                    if(!addressList.isEmpty()){
//                        presentAddress = addressList.get(i).getPresentAddress();
//                        parmanentAddress = addressList.get(i).getParmanentAddress();
//                    }
                    //String managerName = mvcList.get(i).getManagerName();
                    model.insertRow(i, new Object[]{name, department, designation, id , managerName , presentAddress , parmanentAddress });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OfficeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
