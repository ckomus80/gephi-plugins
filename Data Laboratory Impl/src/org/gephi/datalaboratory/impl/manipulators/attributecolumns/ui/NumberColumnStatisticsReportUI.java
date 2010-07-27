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

import java.math.BigDecimal;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.datalaboratory.impl.manipulators.attributecolumns.NumberColumnStatisticsReport;
import org.gephi.datalaboratory.spi.attributecolumns.AttributeColumnsManipulator;
import org.gephi.datalaboratory.spi.attributecolumns.AttributeColumnsManipulatorUI;
import org.gephi.ui.components.JFreeChartDialog;
import org.gephi.ui.components.SimpleHTMLReport;
import org.jfree.chart.JFreeChart;
import org.openide.windows.WindowManager;

/**
 * UI for NumberColumnStatisticsReport AttributeColumnsManipulator.
 * @author Eduardo Ramos <eduramiba@gmail.com>
 */
public class NumberColumnStatisticsReportUI extends javax.swing.JPanel implements AttributeColumnsManipulatorUI {

    private NumberColumnStatisticsReport manipulator;
    private AttributeColumn column;
    private Number[] columnNumbers;
    private BigDecimal[] statistics;
    private JFreeChart boxPlot, scatterPlot;
    private JFreeChartDialog boxPlotDialog, scatterPlotDialog;
    private SimpleHTMLReport reportDialog;

    /** Creates new form NumberColumnStatisticsReportUI */
    public NumberColumnStatisticsReportUI() {
        initComponents();
    }

    public void setup(AttributeColumnsManipulator m, AttributeTable table, AttributeColumn column) {
        this.manipulator = (NumberColumnStatisticsReport) m;
        this.column = column;

        columnNumbers = manipulator.getColumnNumbers(table, column);
        statistics = manipulator.buildStatistics(table, column);

        configureBoxPlotButton.setEnabled(statistics != null);
        configureScatterPlotButton.setEnabled(statistics != null);
    }

    public void unSetup() {
        if (reportDialog != null) {
            reportDialog.dispose();
        }
        if (boxPlotDialog != null) {
            boxPlotDialog.dispose();
        }
        if (scatterPlotDialog != null) {
            scatterPlotDialog.dispose();
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

        configureBoxPlotButton = new javax.swing.JButton();
        configureScatterPlotButton = new javax.swing.JButton();
        showReportButton = new javax.swing.JButton();

        configureBoxPlotButton.setText(org.openide.util.NbBundle.getMessage(NumberColumnStatisticsReportUI.class, "NumberColumnStatisticsReportUI.configureBoxPlotButton.text")); // NOI18N
        configureBoxPlotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureBoxPlotButtonActionPerformed(evt);
            }
        });

        configureScatterPlotButton.setText(org.openide.util.NbBundle.getMessage(NumberColumnStatisticsReportUI.class, "NumberColumnStatisticsReportUI.configureScatterPlotButton.text")); // NOI18N
        configureScatterPlotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureScatterPlotButtonActionPerformed(evt);
            }
        });

        showReportButton.setText(org.openide.util.NbBundle.getMessage(NumberColumnStatisticsReportUI.class, "NumberColumnStatisticsReportUI.showReportButton.text")); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(configureBoxPlotButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(configureScatterPlotButton, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(showReportButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configureBoxPlotButton)
                    .addComponent(showReportButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(configureScatterPlotButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void configureBoxPlotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureBoxPlotButtonActionPerformed
        if (boxPlot == null) {
            boxPlot = manipulator.buildBoxPlot(columnNumbers, column.getTitle());
        }
        if (boxPlotDialog != null) {
            boxPlotDialog.setVisible(true);
        } else {
            boxPlotDialog = new JFreeChartDialog(WindowManager.getDefault().getMainWindow(), boxPlot.getTitle().getText(), boxPlot, 300, 500);
        }
    }//GEN-LAST:event_configureBoxPlotButtonActionPerformed

    private void configureScatterPlotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureScatterPlotButtonActionPerformed
        if (scatterPlot == null) {
            scatterPlot = manipulator.buildScatterPlot(columnNumbers, column.getTitle());
        }
        if (scatterPlotDialog != null) {
            scatterPlotDialog.setVisible(true);
        } else {
            scatterPlotDialog = new JFreeChartDialog(WindowManager.getDefault().getMainWindow(), scatterPlot.getTitle().getText(), scatterPlot, 600, 400);
        }

    }//GEN-LAST:event_configureScatterPlotButtonActionPerformed

    private void showReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReportButtonActionPerformed
        if (boxPlot == null) {
            boxPlot = manipulator.buildBoxPlot(columnNumbers, column.getTitle());
        }
        if (scatterPlot == null) {
            scatterPlot = manipulator.buildScatterPlot(columnNumbers, column.getTitle());
        }
        final String html = manipulator.getReportHTML(column, statistics, boxPlot, scatterPlot, boxPlotDialog != null ? boxPlotDialog.getChartSize() : null, scatterPlotDialog != null ? scatterPlotDialog.getChartSize() : null);

        if (reportDialog != null) {
            reportDialog.dispose();
        }
        reportDialog = new SimpleHTMLReport(WindowManager.getDefault().getMainWindow(), html);
    }//GEN-LAST:event_showReportButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton configureBoxPlotButton;
    private javax.swing.JButton configureScatterPlotButton;
    private javax.swing.JButton showReportButton;
    // End of variables declaration//GEN-END:variables
}
