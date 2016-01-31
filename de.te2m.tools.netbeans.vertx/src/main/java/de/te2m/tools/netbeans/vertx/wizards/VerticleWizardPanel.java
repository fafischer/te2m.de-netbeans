/*
* VerticleWizardPanel.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
 */
package de.te2m.tools.netbeans.vertx.wizards;

import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import static org.openide.util.HelpCtx.DEFAULT_HELP;
import static org.openide.util.NbBundle.getMessage;

/**
 * The Class VerticleWizardPanel.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class VerticleWizardPanel implements WizardDescriptor.Panel<WizardDescriptor> {

    /**
     * The wizard descriptor.
     */
    private WizardDescriptor wizardDescriptor;

    /**
     * The listeners.
     */
    private final Set<ChangeListener> listeners = new HashSet<>(1);

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private VerticleWizardPanelVisual component;

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#addChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#getComponent()
     */
    @Override
    public VerticleWizardPanelVisual getComponent() {
        if (component == null) {
            component = new VerticleWizardPanelVisual(this);
            component.setName(getMessage(VerticleWizardPanel.class, "LBL_CreateVerticleStep"));
        }
        return component;
    }


    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#getHelp()
     */
    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
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
    public void readSettings(WizardDescriptor wiz) {
        wizardDescriptor = (WizardDescriptor) wiz;
        component.read(wizardDescriptor);
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#removeChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Panel#storeSettings(java.lang.Object)
     */
    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wizardDescriptor = wiz;
        component.store(wizardDescriptor);
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
    

}
