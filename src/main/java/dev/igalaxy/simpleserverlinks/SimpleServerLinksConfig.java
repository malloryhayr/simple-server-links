package dev.igalaxy.simpleserverlinks;

import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueMap;

public class SimpleServerLinksConfig extends ReflectiveConfig {
    @Comment("Check the README for a list of generic link types.")
    public final TrackedValue<ValueMap<String>> links = this.map("")
            .put("known_server_link.website", "https://example.com")
            .build();
}
