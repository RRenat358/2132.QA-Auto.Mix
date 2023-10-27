package properties01;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface App02ConfigProvider {

    Config config = readConfig();

    static Config readConfig(){
        return ConfigFactory.systemProperties().hasPath("testProperties")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProperties"))
                : ConfigFactory.load("app02.conf");



    }




}
