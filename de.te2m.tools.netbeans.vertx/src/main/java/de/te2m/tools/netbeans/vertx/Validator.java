/*
* Validator.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx;

import java.util.StringTokenizer;

/**
 * The Class Validator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class Validator {
    
    /**
     * Validate docker image name.
     * It follows the rules as described here: https://docs.docker.com/registry/spec/api/
     * @param name the name
     * @return true, if successful
     */
    public static boolean validateDockerImageName(String name)
    {
        if(null==name)
        {
            return false;
        }
        if(name.length()> 256)
        {
            return false;
        }    
        if(name.length()==0)
        {
            return false;
        }  
        StringTokenizer st = new StringTokenizer(name, "/");
        while (st.hasMoreTokens()) {
            String segment = st.nextToken();
            if(segment.length()>30)
            {
                return false;
            }
            if(!segment.matches("[a-z0-9]+(?:[._-][a-z0-9]+)*"))
            {
                return false;
            }
            
        }
        return true;
    }
}
