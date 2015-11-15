/**
 * Defines all palette entries related to this Netbeans module.
 * Provides also all required code related to it.
 * 
 */
@PaletteItemRegistrations({
    @PaletteItemRegistration(
            paletteid = "VertxIOPalette",
            category = "vertx.io",
            itemid = "HTTP-Server",
            icon16 = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
            icon32 = "de/te2m/tools/netbeans/vertx/icons/logo32.png",
            body = Templates.HTTP_SERVER,
            tooltip = "Create simple HTTP Server",
            name = "HTTP-Server"),
    @PaletteItemRegistration(
            paletteid = "VertxIOPalette",
            category = "vertx.io",
            itemid = "DeploymentOptions",
            icon16 = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
            icon32 = "de/te2m/tools/netbeans/vertx/icons/logo32.png",
            body = Templates.DEPLOYMENT_OPTIONS,
            tooltip = "Initialize options",
            name = "Deployment-Options"),
    @PaletteItemRegistration(
            paletteid = "VertxIOPalette",
            category = "vertx.io - Integration",
            itemid = "Mail",
            icon16 = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
            icon32 = "de/te2m/tools/netbeans/vertx/icons/logo32.png",
            body = Templates.MAIL,
            tooltip = "JavaMail",
            name = "Mail"),
    @PaletteItemRegistration(
            paletteid = "VertxIOPalette",
            category = "vertx.io - JDBC",
            itemid = "Create",
            icon16 = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
            icon32 = "de/te2m/tools/netbeans/vertx/icons/logo32.png",
            body = Templates.JDBC_CREATE,
            tooltip = "Create JDBC",
            name = "Create"),
    @PaletteItemRegistration(
            paletteid = "VertxIOPalette",
            category = "vertx.io - JDBC",
            itemid = "Get Connection",
            icon16 = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
            icon32 = "de/te2m/tools/netbeans/vertx/icons/logo32.png",
            body = Templates.JDBC_GET_CONNECTION,
            tooltip = "Get JDBC Connection",
            name = "Get Connection")
})
package de.te2m.tools.netbeans.vertx.palette;

import de.te2m.tools.netbeans.vertx.Templates;
import org.netbeans.spi.palette.PaletteItemRegistration;
import org.netbeans.spi.palette.PaletteItemRegistrations;
