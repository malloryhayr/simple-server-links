package dev.igalaxy.simpleserverlinks;

import com.mojang.datafixers.util.Either;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.packet.s2c.common.ServerLinksS2CPacket;
import net.minecraft.network.packet.s2c.play.ServerMetadataS2CPacket;
import net.minecraft.server.ServerLinks;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleServerLinksMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("simple-server-links");
	public static final SimpleServerLinksConfig CONFIG = SimpleServerLinksConfig.createToml(FabricLoader.getInstance().getConfigDir(), "simple-server-links", "simple-server-links", SimpleServerLinksConfig.class);

	@Override
	public void onInitialize() {
		ServerLinks serverLinks = new ServerLinks(
				CONFIG.links.value().entrySet().stream().map(entry ->
						ServerLinks.Entry.create(Text.translatable(entry.getKey()), URI.create(entry.getValue()))
				).toList()
		);

		ServerConfigurationConnectionEvents.CONFIGURE.register((configurationNetworkHandler, server) -> {
			configurationNetworkHandler.sendPacket(new ServerLinksS2CPacket(serverLinks.getLinks()));
		});
	}
}