package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final String FILE = "configUserData.env";
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .filename(FILE)
            .load();

    public static final String USER_LOGIN = dotenv.get("LOGIN");
    public static final String USER_PASSWORD = dotenv.get("PASSWORD");
}

