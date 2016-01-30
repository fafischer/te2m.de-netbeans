/*
* FormattingPanel.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.options;

import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.PROPERTY_DATE_FORMAT;
import static org.openide.util.NbPreferences.forModule;

/**
 * The Class GeneratePanel.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public final class FormattingPanel extends javax.swing.JPanel {

    /**
     * The controller.
     */
    private final FormattingOptionsPanelController controller;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * The date format label.
     */
    private javax.swing.JLabel dateFormatLabel;

    /**
     * The date format text field.
     */
    private javax.swing.JTextField dateFormatTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * Instantiates a new generate panel.
     *
     * @param controller the controller
     */
    FormattingPanel(FormattingOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateFormatTextField = new javax.swing.JTextField();
        dateFormatLabel = new javax.swing.JLabel();

        dateFormatTextField.setText(org.openide.util.NbBundle.getMessage(FormattingPanel.class, "FormattingPanel.dateFormatTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dateFormatLabel, org.openide.util.NbBundle.getMessage(FormattingPanel.class, "FormattingPanel.dateFormatLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateFormatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateFormatTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFormatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFormatLabel))
                .addContainerGap(273, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Load.
     */
    void load() {
        dateFormatTextField.setText(forModule(FormattingPanel.class).get(PROPERTY_DATE_FORMAT, "dd.MM.yyyy"));
    }

    /**
     * Store.
     */
    void store() {
        forModule(FormattingPanel.class).put(PROPERTY_DATE_FORMAT, dateFormatTextField.getText());
    }
    /**
     * Valid.
     *
     * @return true, if successful
     */
    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
}
