/*
* mavenPanelController.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.options;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import static javax.swing.SwingUtilities.invokeLater;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

/**
 * The Class MavenPanelController.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
@OptionsPanelController.SubRegistration(
        location = "Te2m",
        displayName = "#AdvancedOption_DisplayName_Maven",
        keywords = "#AdvancedOption_Keywords_Maven",
        keywordsCategory = "Te2m/Maven"
)
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_Maven=Maven Defaults", "AdvancedOption_Keywords_Maven=JSF,vertx, Maven"})
public final class MavenPanelController extends OptionsPanelController {

    /**
     * The panel.
     */
    private MavenPanel panel;
    
    /**
     * The pcs.
     */
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * The changed.
     */
    private boolean changed;

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#applyChanges()
     */
    @Override
    public void applyChanges() {
        invokeLater(() -> {
            getPanel().store();
            changed = false;
        });
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#cancel()
     */
    @Override
    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    /**
     * Changed.
     */
    void changed() {
        if (!changed) {
            changed = true;
            pcs.firePropertyChange(PROP_CHANGED, false, true);
        }
        pcs.firePropertyChange(PROP_VALID, null, null);
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#getComponent(org.openide.util.Lookup)
     */
    @Override
    public JComponent getComponent(Lookup masterLookup) {
        return getPanel();
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#getHelpCtx()
     */
    @Override
    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    /**
     * Gets the panel.
     *
     * @return the panel
     */
    private MavenPanel getPanel() {
        if (panel == null) {
            panel = new MavenPanel(this);
        }
        return panel;
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#isChanged()
     */
    @Override
    public boolean isChanged() {
        return changed;
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#isValid()
     */
    @Override
    public boolean isValid() {
        return getPanel().valid();
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /* (non-Javadoc)
     * @see org.netbeans.spi.options.OptionsPanelController#update()
     */
    @Override
    public void update() {
        getPanel().load();
        changed = false;
    }

}
