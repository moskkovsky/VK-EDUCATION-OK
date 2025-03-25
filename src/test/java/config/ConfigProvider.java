package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("configUser.conf");
    }
    String USER_NAME = readConfig().getString("userParams.user.name");
    String WRONG_LOGIN = readConfig().getString("userParams.userWrong.login");
    String WRONG_PASSWORD = readConfig().getString("userParams.userWrong.password");
}