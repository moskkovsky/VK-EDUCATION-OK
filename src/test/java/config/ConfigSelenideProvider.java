package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigSelenideProvider {
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("configSelenide.conf");
    }

    String BROWSER = readConfig().getString("browser");
    Boolean HEADLESS = readConfig().getBoolean("headless");
    Integer TIMEOUT = readConfig().getInt("timeout");
    Integer PAGE_LOAD_TIMEOUT = readConfig().getInt("pageLoadTimeout");
    String BROWSER_SIZE = readConfig().getString("browserSize");
    String URL = readConfig().getString("url");
}