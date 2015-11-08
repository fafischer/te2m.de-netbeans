/*
* JavaSourceFileLayerPaletteFactory.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.palette;

import java.io.IOException;
import javax.swing.Action;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.palette.DragAndDropHandler;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.datatransfer.ExTransferable;

/**
 * A factory for creating JavaSourceFileLayerPalette objects.
 */
public class JavaSourceFileLayerPaletteFactory {

    /**
     * The palette.
     */
    private static PaletteController palette = null;

    /**
     * Creates a new JavaSourceFileLayerPalette object.
     *
     * @return the palette controller
     */
    @MimeRegistration(mimeType = "text/x-java", service = PaletteController.class)
    public static PaletteController createPalette() {
        try {
            if (null == palette) {
                return PaletteFactory.createPalette(
                        //Folder:
                        "VertxIOPalette",
                        //Palette Actions:
                        new PaletteActions() {
                            @Override
                            public Action[] getImportActions() {
                                return null;
                            }

                            @Override
                            public Action[] getCustomPaletteActions() {
                                return null;
                            }

                            @Override
                            public Action[] getCustomCategoryActions(Lookup lkp) {
                                return null;
                            }

                            @Override
                            public Action[] getCustomItemActions(Lookup lkp) {
                                return null;
                            }

                            @Override
                            public Action getPreferredAction(Lookup lkp) {
                                return null;
                            }
                        },
                        //Palette Filter:
                        null,
                        //Drag and Drop Handler:
                        new DragAndDropHandler(true) {
                            @Override
                            public void customize(ExTransferable et, Lookup lkp) {
                            }
                        });
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
}
