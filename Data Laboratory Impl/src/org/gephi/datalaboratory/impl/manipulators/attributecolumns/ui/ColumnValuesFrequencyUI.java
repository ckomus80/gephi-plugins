/*
Copyright 2008-2010 Gephi
Authors : Eduardo Ramos <eduramiba@gmail.com>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.datalaboratory.impl.manipulators.attributecolumns.ui;

import java.util.Map;
import javax.swing.JPanel;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.datalaboratory.impl.manipulators.attributecolumns.ColumnValuesFrequency;
import org.gephi.datalaboratory.spi.attributecolumns.AttributeColumnsManipulator;
import org.gephi.datalaboratory.spi.attributecolumns.AttributeColumnsManipulatorUI;
import org.gephi.ui.components.JFreeChartDialog;
import org.gephi.ui.components.SimpleHTMLReport;
import org.jfree.chart.JFreeChart;
import org.openide.windows.WindowManager;

/**
 * UI for ColumnValuesFrequency AttributeColumnsManipulator.
 * @author Eduardo Ramos <eduramiba@gmail.com>
 */
public class ColumnValuesFrequencyUI extends javax.swing.JPanel implements AttributeColumnsManipulatorUI {

    private ColumnValuesFrequency manipulator;
    private AttributeTable table;
    private AttributeColumn column;
    private Map<Object, Integer> valuesFrequencies;
    private JFreeChart pieChart;
    private JFreeChartDialog pieChartDialog;
    private SimpleHTMLReport reportDialog;

    /** Creates new form ColumnValuesFrequencyUI */
    public ColumnValuesFrequencyUI() {
        initComponents();
    }

    public void setup(AttributeColumnsManipulator m, AttributeTable table, AttributeColumn column) {
        this.table = table;
        this.column = column;
        this.manipulator = (ColumnValuesFrequency) m;
        valuesFrequencies = manipulator.buildValuesFrequencies(table, column);

        configurePieChartButton.setEnabled(valuesFrequencies.size()<=ColumnValuesFrequency.MAX_PIE_CHART_CATEGORIES);
    }

    public void unSetup() {
        if (reportDialog != null) {
            reportDialog.dispose();
        }
        if (pieChartDialog != null) {
            pieChartDialog.dispose();
        }
    }

    public String getDisplayName() {
        return manipulator.getName();
    }

    public JPanel getSettingsPanel() {
        return this;
    }

    public boolean isModal() {
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        configurePieChartButton = new javax.swing.JButton();
        showReportButton = new javax.swing.JButton();

        configurePieChartButton.setText(org.openide.util.NbBundle.getMessage(ColumnValuesFrequencyUI.class, "ColumnValuesFrequencyUI.configurePieChartButton.text")); // NOI18N
        configurePieChartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configurePieChartButtonActionPerformed(evt);
            }
        });

        showReportButton.setText(org.openide.util.NbBundle.getMessage(ColumnValuesFrequencyUI.class, "ColumnValuesFrequencyUI.showReportButton.text")); // NOI18N
        showReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(configurePieChartButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(showReportButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configurePieChartButton)
                    .addComponent(showReportButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void configurePieChartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configurePieChartButtonActionPerformed
        if (pieChart == null) {
            pieChart = manipulator.buildPieChart(valuesFrequencies);
        }
        if (pieChartDialog != null) {
            pieChartDialog.setVisible(true);
        } else {
            pieChartDialog = new JFreeChartDialog(WindowManager.getDefault().getMainWindow(), pieChart.getTitle().getText(), pieChart, 1000, 1000);
        }
    }//GEN-LAST:event_configurePieChartButtonActionPerformed

    private void showReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReportButtonActionPerformed

        if (pieChart == null) {
            pieChart = manipulator.buildPieChart(valuesFrequencies);
        }
        final String html = manipulator.getReportHTML(table, column, valuesFrequencies, pieChart, pieChartDialog != null ? pieChartDialog.getChartSize() : null);

        if (reportDialog != null) {
            reportDialog.dispose();
        }
        reportDialog = new SimpleHTMLReport(WindowManager.getDefault().getMainWindow(), html);
    }//GEN-LAST:event_showReportButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton configurePieChartButton;
    private javax.swing.JButton showReportButton;
    // End of variables declaration//GEN-END:variables
}
