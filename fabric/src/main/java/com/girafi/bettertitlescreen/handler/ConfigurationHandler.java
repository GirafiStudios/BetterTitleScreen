package com.girafi.bettertitlescreen.handler;

import com.google.common.collect.Lists;
import net.neoforged.common.ModConfigSpec;

import java.util.List;

public class ConfigurationHandler {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final CustomText CUSTOM_TEXT = new CustomText(BUILDER);
    public static final General GENERAL = new General(BUILDER);

    public static class CustomText {
        public final ModConfigSpec.ConfigValue<List<? extends String>> titleScreenText;
        static final String DEFAULT_TEXT = "\u00A74B\u00A76E\u00A7eT\u00A72T\u00A73E\u00A71R \u00A75T\u00A74I\u00A76T\u00A7eL\u00A72E \u00A73S\u00A71C\u00A75R\u00A74E\u00A76E\u00A7eN";

        CustomText(ModConfigSpec.Builder builder) {
            builder.push("custom text");
            titleScreenText = builder
                    .comment("Insert the custom title screen text you want here (supports multiple lines)")
                    .translation("bettertitlescreen.configgui.titleScreenText")
                    .defineList("text", Lists.newArrayList(DEFAULT_TEXT), o -> o instanceof String);
            builder.pop();
        }
    }

    public static class General {
        public final ModConfigSpec.BooleanValue titleScreenMCVersion;
        public final ModConfigSpec.BooleanValue titleScreenFabric;

        General(ModConfigSpec.Builder builder) {
            builder.push("versions");
            titleScreenMCVersion = builder
                    .comment("Show which version of Minecraft the client is currently running.")
                    .translation("bettertitlescreen.configgui.mcVersion")
                    .define("showMcVersion", true);
            titleScreenFabric = builder
                    .comment("Show the Fabric suffix, after the Minecraft version (By default Fabric behavior)")
                    .translation("bettertitlescreen.configgui.forgeVersion")
                    .define("showFabric", true);
            builder.pop();
        }
    }

    public static final ModConfigSpec spec = BUILDER.build();
}