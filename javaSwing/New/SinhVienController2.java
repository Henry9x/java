/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.New;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admin
 */
public class SinhVienController2 {

    SinhVienimplDAO DAO;
    private SinhVienTableModel sinhvienModel;
    private SinhVienView2 sinhvienView;

    public SinhVienController2(SinhVienView2 _sinhvienView, SinhVienTableModel _sinhvienModel) {
        this.sinhvienView = _sinhvienView;
        sinhvienModel = _sinhvienModel;
        DAO = new SinhVienimplDAO("jdbc:mysql://localhost:3306/mydatabase", "root", "admin");
    }

    public void showSinhVienView() {
        sinhvienView.showListSinhVien(sinhvienModel);
        sinhvienView.addListSinhVienSelectionListener(new ListStudentSelectionListener());
        sinhvienView.addDeleteSinhVientListener(new DeleteStudentListener());
        sinhvienView.addUpdateSinhVienListener(new UpdateSinhVienListener());
        sinhvienView.addInsertSinhVientListener(new InsertSinhVientListener());
        sinhvienView.addClearSinhVientListener(new ClearStudentListener());
        sinhvienView.setVisible(true);
        sinhvienView.setEnabled(true);
    }

    class ListStudentSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            sinhvienView.fillSinhVienFromSelectedRow();
        }
    }

    class ClearStudentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            sinhvienView.clearSinhVientInfo();
        }
    }

    class InsertSinhVientListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SinhVien sv = sinhvienView.getSinhVienInfo();
            if (sv != null) {
                DAO.insert(sv);
                sinhvienView.clearSinhVientInfo();
                sinhvienView.showListSinhVien(new SinhVienTableModel(DAO.getAllSinhVien()));
                sinhvienView.showMessage("Th??m th??nh c??ng!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SinhVien sv = sinhvienView.getSinhVienInfo();

            if (sv != null) {
                DAO.DeleteSinhVien(sv);
                sinhvienView.clearSinhVientInfo();
                ArrayList<SinhVien> ds = DAO.getAllSinhVien();
                if (ds != null) {
                    sinhvienView.showListSinhVien(new SinhVienTableModel(ds));
                } else {
                    sinhvienView.showMessage("D??? li???u r???ng");
                }
                sinhvienView.showMessage("X??a th??nh c??ng!");
            }
        }
    }

    class UpdateSinhVienListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SinhVien sv = sinhvienView.getSinhVienInfo();
            if (sv != null) {
                DAO.updateSinhVien(sv);
                // sinhvienView.showStudent(sv);
                sinhvienView.showListSinhVien(new SinhVienTableModel(DAO.getAllSinhVien()));
                sinhvienView.showMessage("C???p nh???t th??nh c??ng!");
            }
        }
    }
}
