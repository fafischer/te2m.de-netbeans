/*
* Templates.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx;

/**
 * The Interface Templates.
 *
 * @author ffischer
 */
public interface Templates {

    /**
     * The Constant MAIL.
     */
    public static final String MAIL = "    MailService mailService = MailService.createEventBusProxy(vertx, \"vertx.mail\");\n"
            + "\n"
            + "    MailMessage email = new MailMessage()\n"
            + "      .setBounceAddress(\"bounce@example.com\")\n"
            + "      .setTo(\"user@example.com\")\n"
            + "      .setSubject(\"this message has no content at all\");\n"
            + "\n"
            + "    mailService.sendMail(email, result -> {\n"
            + "      if (result.succeeded()) {\n"
            + "        System.out.println(result.result());\n"
            + "      } else {\n"
            + "        System.out.println(\"got exception\");\n"
            + "        result.cause().printStackTrace();\n"
            + "      }\n"
            + "    });";

    /**
     * The Constant JDBC_CREATE.
     */
    public static final String JDBC_CREATE = "    // Adjust configuration as required"
            + "     final JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()\n"
            + "        .put(\"url\", \"jdbc:hsqldb:mem:test?shutdown=true\")\n"
            + "        .put(\"driver_class\", \"org.hsqldb.jdbcDriver\")\n"
            + "        .put(\"max_pool_size\", 30));";

    /**
     * The Constant JDBC_GET_CONNECTION.
     */
    public static final String JDBC_GET_CONNECTION = "    client.getConnection(conn -> {\n"
            + "      if (conn.failed()) {\n"
            + "        System.err.println(conn.cause().getMessage());\n"
            + "        return;\n"
            + "      }"
            + "      // Insert connection handling code here"
            + "    });";

    /**
     * The Constant HTTP_SERVER.
     */
    public static final String HTTP_SERVER = "        // Adjust sample data as required"
            + "        vertx.createHttpServer().requestHandler(req -> {\n"
            + "      req.response().putHeader(\"content-type\", \"text/html\").end(\"<html><body><h1>Hello World!</h1></body></html>\");\n"
            + "    }).listen(8080, result -> {\n"
            + "                    if (result.succeeded()) {\n"
            + "                        future.complete();\n"
            + "                    } else {\n"
            + "                        future.fail(result.cause());\n"
            + "                    }\n"
            + "                });";

    /**
     * The Constant DEPLOYMENT_OPTIONS.
     */
    public static final String DEPLOYMENT_OPTIONS = "   JsonObject config = new JsonObject();\n"
            + "   // TODO Extend configuration as required\n"
            + "   DeploymentOptions options = new DeploymentOptions().setConfig(config);";
}
