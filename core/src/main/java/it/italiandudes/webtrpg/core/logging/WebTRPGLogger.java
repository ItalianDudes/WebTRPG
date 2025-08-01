package it.italiandudes.webtrpg.core.logging;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebTRPGLogger {

    // Attributes
    @NotNull private final Logger LOGGER;

    // Constructor
    private WebTRPGLogger() {
        LOGGER = LoggerFactory.getLogger("WebTRPG");
    }

    // Instance
    private static WebTRPGLogger INSTANCE;
    @NotNull
    public static Logger getLogger() {
        if (INSTANCE == null) INSTANCE = new WebTRPGLogger();
        return INSTANCE.LOGGER;
    }
}
