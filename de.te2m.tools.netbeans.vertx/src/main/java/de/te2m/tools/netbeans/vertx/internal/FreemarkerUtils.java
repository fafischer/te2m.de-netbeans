/*
* FreemarkerUtils.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.internal;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class FreemarkerUtils.
 *
 * @author ffischer
 */
public class FreemarkerUtils {

    /**
     * Generate.
     *
     * @param hashMap the hash map
     * @param templateBase the template base
     * @param template the template
     * @return the string
     */
    public static String generate(HashMap hashMap, String templateBase, String template) {
        String result = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtils.class, templateBase));
            Template tpl = cfg.getTemplate(template);
            result = generateFromTemplate(tpl, hashMap);
        } catch (TemplateException | IOException ex) {
            handleException(ex);
        }
        return result;
    }

    /**
     * Generate.
     *
     * @param hashMap the hash map
     * @param templateContent the template content
     * @return the string
     */
    public static String generate(Map<String, Object> hashMap, String templateContent) {
        String result = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setWhitespaceStripping(false);

            Template tpl = new Template("name", new StringReader(templateContent), cfg);
            result = generateFromTemplate(tpl, hashMap);
        } catch (TemplateException | IOException ex) {
            handleException(ex);
        }
        if (null != result) {
            return result;
        } else {
            return "";
        }
    }

    /**
     * Generate from template.
     *
     * @param tpl the tpl
     * @param hashMap the hash map
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws TemplateException the template exception
     */
    protected static String generateFromTemplate(Template tpl, Map<String, Object> hashMap) throws IOException, TemplateException {
        StringWriter sw = new StringWriter();
        tpl.process(hashMap, sw);
        return sw.toString();
    }

    /**
     * Handle exception.
     *
     * @param t the t
     */
    protected static void handleException(Throwable t) {
        t.printStackTrace();
    }
}
