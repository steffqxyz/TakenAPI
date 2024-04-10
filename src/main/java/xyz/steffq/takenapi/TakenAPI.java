package xyz.steffq.takenapi;

import org.yaml.snakeyaml.Yaml;
import xyz.steffq.takenapi.configuration.YamlConfig;
import xyz.steffq.takenapi.logger.TakenLog;

import javax.annotation.Nullable;

public interface TakenAPI {

    @Nullable
    YamlConfig getConfigYml();
    TakenLog getLogger();

}
