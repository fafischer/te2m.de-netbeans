/*
* VertxWizardPackagingPanel.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.wizards.project;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import static org.openide.util.NbBundle.getMessage;

/**
 * Panel for managing the packaging related data.
 * This includes:
 * <ul>
 * <li>Control the creation of a FAT-Jar</li>
 * </ul>
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class VertxWizardPackagingPanel implements WizardDescriptor.Panel,
        WizardDescriptor.ValidatingPanel, WizardDescriptor.FinishablePanel {

    /**
     * The wizard descriptor.
     */
    private WizardDescriptor wizardDescriptor;
    
    /**
     * The component.
     */
    private VertxPackagingPanelVisual component;

    /**
     * The listeners.
     */
    private final Set<ChangeListener> listeners = new HashSet<>(1); // or can use ChangeSupport in NB 6.0

    /**
     * Instantiates a new vertx wizard panel.
     */
    public VertxWizardPackagingPanel() {
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#addChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    /**
     * Fire change event.
     */
    protected final void fireChangeEvent() {
        Set<ChangeListener> ls;
        synchronized (listeners) {
            ls = new HashSet<>(listeners);
        }
        ChangeEvent ev = new ChangeEvent(this);
        ls.stream().forEach((l) -> {
            l.stateChanged(ev);
        });
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#getComponent()
     */
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new VertxPackagingPanelVisual(this);
            component.setName(getMessage(VertxWizardPackagingPanel.class, "LBL_CreateVerticleStep"));
        }
        return component;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#getHelp()
     */
    @Override
    public HelpCtx getHelp() {
        return new HelpCtx(VertxWizardPackagingPanel.class);
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.FinishablePanel#isFinishPanel()
     */
    @Override
    public boolean isFinishPanel() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#isValid()
     */
    @Override
    public boolean isValid() {
        getComponent();
        return component.valid(wizardDescriptor);
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#readSettings(java.lang.Object)
     */
    @Override
    public void readSettings(Object settings) {
        wizardDescriptor = (WizardDescriptor) settings;
        component.read(wizardDescriptor);
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#removeChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#storeSettings(java.lang.Object)
     */
    @Override
    public void storeSettings(Object settings) {
        WizardDescriptor d = (WizardDescriptor) settings;
        component.store(d);
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.ValidatingPanel#validate()
     */
    @Override
    public void validate() throws WizardValidationException {
        getComponent();
        component.validate(wizardDescriptor);
    }

}
