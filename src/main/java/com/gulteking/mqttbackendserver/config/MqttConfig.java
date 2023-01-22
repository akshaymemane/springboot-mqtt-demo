package com.gulteking.mqttbackendserver.config;

public abstract class MqttConfig {
    protected final String broker = "107.155.65.25";
    protected final int qos = 1;
    protected Boolean hasSSL = false; /*By default SSL is disabled */
    protected Integer port = 1883; /* Default port */
    protected final String userName = "";
    protected final String password = "";
    protected final String TCP = "tcp://";
    protected final String SSL = "ssl://";

    /**
     * Custom Configuration
     *
     * @param broker
     * @param port
     * @param ssl
     * @param withUserNamePass
     */
    protected abstract void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass);

    /**
     * Default Configuration
     */
    protected abstract void config();
}
