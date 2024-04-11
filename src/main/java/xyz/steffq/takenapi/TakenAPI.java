package xyz.steffq.takenapi;

import xyz.steffq.takenapi.configuration.YamlConfig;

import javax.annotation.Nullable;

public interface TakenAPI {

    @Nullable
    YamlConfig getConfigYml();

}
