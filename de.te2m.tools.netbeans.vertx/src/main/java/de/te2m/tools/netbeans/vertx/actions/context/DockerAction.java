/*
 * Copyright (C) 2016 ffi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.te2m.tools.netbeans.vertx.actions.context;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "de.te2m.tools.netbeans.vertx.actions.context.DockerAction"
)
@ActionRegistration(
        iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
        displayName = "#CTL_DockerAction"
)
@ActionReference(path = "Loaders/text/x-java/Actions", position = -100)
@Messages("CTL_DockerAction=Create Dockerfile")
public final class DockerAction implements ActionListener {

    private final DataObject context;

    public DockerAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // TODO use context
    }
}
