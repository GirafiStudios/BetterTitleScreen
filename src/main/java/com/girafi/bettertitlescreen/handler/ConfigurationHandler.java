package com.girafi.bettertitlescreen.handler;

import com.girafi.bettertitlescreen.reference.Reference;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.nio.file.Path;
import java.util.List;

@EventBusSubscriber
public class ConfigurationHandler {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final CustomText CUSTOM_TEXT = new CustomText(BUILDER);
    public static final General GENERAL = new General(BUILDER);

    public static class CustomText {
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> titleScreenText;
        static final String DEFAULT_TEXT = "\u00A74B\u00A76E\u00A7eT\u00A72T\u00A73E\u00A71R \u00A75T\u00A74I\u00A76T\u00A7eL\u00A72E \u00A73S\u00A71C\u00A75R\u00A74E\u00A76E\u00A7eN";

        private static final ForgeConfigSpec spec = BUILDER.build();

        CustomText(ForgeConfigSpec.Builder builder) {
            builder.push("custom text");
            titleScreenText = builder
                    .comment("Insert the custom title screen text you want here (supports multiple lines)")
                    .translation("bettertitlescreen.configgui.titleScreenText")
                    .defineList("text", Lists.newArrayList(DEFAULT_TEXT), o -> o instanceof String);
            builder.pop();
        }
    }

    public static class General {
        public final ForgeConfigSpec.BooleanValue titleScreenMCVersion;
        public final ForgeConfigSpec.BooleanValue titleScreenMCP;
        public final ForgeConfigSpec.BooleanValue titleScreenForge;
        public final ForgeConfigSpec.BooleanValue titleScreenMods;

        General(ForgeConfigSpec.Builder builder) {
            builder.push("versions");
            titleScreenMCVersion = builder
                    .comment("Show which version of Minecraft the client is currently running.")
                    .translation("bettertitlescreen.configgui.mcVersion")
                    .define("showMcVersion", true);
            titleScreenMCP = builder
                    .comment("Show which version of Minecraft Coder Pack (MCP) the client is running.")
                    .translation("bettertitlescreen.configgui.mcpVersion")
                    .define("showMcpVersion", false);
            titleScreenForge = builder
                    .comment("Show which version of Minecraft Forge the client is running.")
                    .translation("bettertitlescreen.configgui.forgeVersion")
                    .define("showForgeVersion", false);
            titleScreenMods = builder
                    .comment("Show how many mods are loaded.")
                    .translation("bettertitlescreen.configgui.modsLoaded")
                    .define("showModsLoaded", false);
            builder.pop();
        }
    }

    private static final ForgeConfigSpec spec = BUILDER.build();

    public static void loadFrom(final Path configRoot) {
        Path configFile = configRoot.resolve(Reference.MOD_ID + ".toml");
        spec.setConfigFile(configFile);
    }
}