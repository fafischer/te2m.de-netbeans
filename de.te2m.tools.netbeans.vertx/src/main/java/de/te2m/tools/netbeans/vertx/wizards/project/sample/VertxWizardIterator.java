/*
* VertxWizardIterator.java
*
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules
* (https://github.com/fafischer/te2m.de-netbeans).
*
 */
package de.te2m.tools.netbeans.vertx.wizards.project.sample;

import static de.te2m.tools.netbeans.vertx.internal.FreemarkerUtils.generate;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import static java.text.MessageFormat.format;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import static org.netbeans.api.project.ProjectManager.getDefault;
import org.netbeans.api.templates.TemplateRegistration;
import static org.netbeans.spi.project.ui.support.ProjectChooser.setProjectsFolder;
import static org.netbeans.spi.project.ui.templates.support.Templates.getTemplate;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import static org.openide.filesystems.FileUtil.copy;
import static org.openide.filesystems.FileUtil.createData;
import static org.openide.filesystems.FileUtil.createFolder;
import static org.openide.filesystems.FileUtil.normalizeFile;
import static org.openide.filesystems.FileUtil.toFileObject;
import static org.openide.util.Exceptions.printStackTrace;
import org.openide.util.NbBundle.Messages;
import static org.openide.util.NbBundle.getMessage;
import static org.openide.xml.XMLUtil.parse;
import static org.openide.xml.XMLUtil.write;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// TODO define position attribute
/**
 * The Class VertxWizardIterator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
@TemplateRegistration(folder = "Project/Samples/vertx.io", displayName = "#Vertx_displayName", description = "VertxDescription.html", iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png", content = "VertxProject.zip")
@Messages("Vertx_displayName=Create a sample Vertx.io 3.1 Project")
public class VertxWizardIterator implements WizardDescriptor./*Progress*/InstantiatingIterator {

    /**
     * Creates the iterator.
     *
     * @return the vertx wizard iterator
     */
    public static VertxWizardIterator createIterator() {
        return new VertxWizardIterator();
    }

    /**
     * Filter project xml.
     *
     * @param fo the fo
     * @param str the str
     * @param name the name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static void filterProjectXML(FileObject fo, ZipInputStream str, String name) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            copy(str, baos);
            Document doc = parse(new InputSource(new ByteArrayInputStream(baos.toByteArray())), false, false, null, null);
            NodeList nl = doc.getDocumentElement().getElementsByTagName("name");
            if (nl != null) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element el = (Element) nl.item(i);
                    if (el.getParentNode() != null && "data".equals(el.getParentNode().getNodeName())) {
                        NodeList nl2 = el.getChildNodes();
                        if (nl2.getLength() > 0) {
                            nl2.item(0).setNodeValue(name);
                        }
                        break;
                    }
                }
            }
            try (OutputStream out = fo.getOutputStream()) {
                write(doc, out, "UTF-8");
            }
        } catch (Exception ex) {
            printStackTrace(ex);
            writeFile(str, fo);
        }

    }

    /**
     * Un zip file.
     *
     * @param source the source
     * @param projectRoot the project root
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static void unZipFile(InputStream source, FileObject projectRoot) throws IOException {
        try {
            ZipInputStream str = new ZipInputStream(source);
            ZipEntry entry;
            while ((entry = str.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    createFolder(projectRoot, entry.getName());
                } else {
                    FileObject fo = createData(projectRoot, entry.getName());
                    if ("nbproject/project.xml".equals(entry.getName())) {
                        // Special handling for setting name of Ant-based projects; customize as needed:
                        filterProjectXML(fo, str, projectRoot.getName());
                    } else {
                        writeFile(str, fo);
                    }
                }
            }
        } finally {
            source.close();
        }
    }

    /**
     * Write file.
     *
     * @param str the str
     * @param fo the fo
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static void writeFile(ZipInputStream str, FileObject fo) throws IOException {
        try (OutputStream out = fo.getOutputStream()) {
            copy(str, out);
        }
    }

    /**
     * The index.
     */
    private int index;

    /**
     * The panels.
     */
    private WizardDescriptor.Panel[] panels;

    /**
     * The wiz.
     */
    private WizardDescriptor wiz;

    /**
     * Instantiates a new vertx wizard iterator.
     */
    public VertxWizardIterator() {
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#addChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void addChangeListener(ChangeListener l) {
    }

    /**
     * Creates the panels.
     *
     * @return the wizard descriptor. panel[]
     */
    private WizardDescriptor.Panel[] createPanels() {
        return new WizardDescriptor.Panel[]{
            new VertxWizardPanel(),};
    }

    /**
     * Creates the steps.
     *
     * @return the string[]
     */
    private String[] createSteps() {
        return new String[]{
            getMessage(VertxWizardIterator.class, "LBL_CreateProjectStep")
        };
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#current()
     */
    @Override
    public WizardDescriptor.Panel current() {
        return panels[index];
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return index < panels.length - 1;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#hasPrevious()
     */
    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#initialize(org.openide.WizardDescriptor)
     */
    @Override
    public void initialize(WizardDescriptor wiz) {
        this.wiz = wiz;
        index = 0;
        panels = createPanels();
        // Make sure list of steps is accurate.
        String[] steps = createSteps();
        for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                // Step #.
                // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_*:
                jc.putClientProperty("WizardPanel_contentSelectedIndex", i);
                // Step name (actually the whole list for reference).
                jc.putClientProperty("WizardPanel_contentData", steps);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#instantiate()
     */
    @Override
    public Set/*<FileObject>*/ instantiate(/*ProgressHandle handle*/) throws IOException {
        Set<FileObject> resultSet = new LinkedHashSet<>();
        File dirF = normalizeFile((File) wiz.getProperty("projdir"));
        dirF.mkdirs();

        FileObject template = getTemplate(wiz);
        FileObject dir = toFileObject(dirF);
        unZipFile(template.getInputStream(), dir);

        // Always open top dir as a project:
        resultSet.add(dir);
        // Look for nested projects to open as well:
        Enumeration<? extends FileObject> e = dir.getFolders(true);
        while (e.hasMoreElements()) {
            FileObject subfolder = e.nextElement();
            if (getDefault().isProject(subfolder)) {
                resultSet.add(subfolder);
            }
        }
        HashMap<String, Object> params = new HashMap<>();
        out.println(generate(params, "de/te2m/tools/netbeans/vertx", "pom.xml.template"));
        File parent = dirF.getParentFile();
        if (parent != null && parent.exists()) {
            setProjectsFolder(parent);
        }

        return resultSet;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#name()
     */
    @Override
    public String name() {
        return format("{0} of {1}",
                new Object[]{index + 1, panels.length});
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#nextPanel()
     */
    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#previousPanel()
     */
    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#removeChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void removeChangeListener(ChangeListener l) {
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#uninitialize(org.openide.WizardDescriptor)
     */
    @Override
    public void uninitialize(WizardDescriptor wiz) {
        this.wiz.putProperty("projdir", null);
        this.wiz.putProperty("name", null);
        this.wiz = null;
        panels = null;
    }

}
