/*
* NewCompositeComponentWizardPanel1.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.jsfutils project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.netbeans.jsfutils.wizards.cc;

import javax.swing.event.ChangeListener;

import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 * The Class NewCompositeComponentWizardPanel1.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class NewCompositeComponentWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private NewCompositeComponentVisualPanel1 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#getComponent()
     */
    @Override
    public NewCompositeComponentVisualPanel1 getComponent() {
        if (component == null) {
            component = new NewCompositeComponentVisualPanel1();
        }
        return component;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#getHelp()
     */
    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#isValid()
     */
    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#addChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#removeChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#readSettings(java.lang.Object)
     */
    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#storeSettings(java.lang.Object)
     */
    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty(NewCompositeComponentVisualPanel1.PROPERTY_NAME, getNameFromVisualPanel());
        wiz.putProperty(NewCompositeComponentVisualPanel1.PROPERTY_DECRIPTION, getDescriptionFromVisualPanel());
        wiz.putProperty(NewCompositeComponentVisualPanel1.PROPERTY_FOLDER, getFolderFromVisualPanel());
    }

    /**
     * Gets the name from visual panel.
     *
     * @return the name from visual panel
     */
    private String getNameFromVisualPanel() {
        return ((NewCompositeComponentVisualPanel1) component).getTargetName();
    }

    /**
     * Gets the description from visual panel.
     *
     * @return the description from visual panel
     */
    private String getDescriptionFromVisualPanel() {
        return ((NewCompositeComponentVisualPanel1) component).getTargetDescription();
    }

    /**
     * Gets the folder from visual panel.
     *
     * @return the folder from visual panel
     */
    private String getFolderFromVisualPanel() {
        return ((NewCompositeComponentVisualPanel1) component).getTargetFolder();
    }
}
