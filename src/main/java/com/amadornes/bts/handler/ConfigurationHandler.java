package com.amadornes.bts.handler;

import com.amadornes.bts.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static net.minecraftforge.common.config.Configuration.*;

public class ConfigurationHandler
{
    public static Configuration configuration;
    public static String CATEGORY_CUSTOM_TEXT = "custom text";
    public static String[] TitleScreenText;
    public static boolean TitleScreenMCVersion;
    public static boolean TitleScreenMCP;
    public static boolean TitleScreenFML;
    public static boolean TitleScreenForge;
    public static boolean TitleScreenMods;

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        TitleScreenText = configuration.getStringList("Text", CATEGORY_CUSTOM_TEXT, new String[]{"\u00A74B\u00A76E\u00A7eT\u00A72T\u00A73E\u00A71R \u00A75T\u00A74I\u00A76T\u00A7eL\u00A72E \u00A73S\u00A71C\u00A75R\u00A74E\u00A76E\u00A7eN"}, "Insert the text you want here.(Supports multiple lines)");
        TitleScreenMCVersion = configuration.getBoolean("Show MC version", CATEGORY_GENERAL, true, "Show which version of Minecraft the client is currently running.");
        TitleScreenMCP = configuration.getBoolean("Show MCP version", CATEGORY_GENERAL, false, "Show which version of Minecraft Coder Pack (MCP) the client is running.");
        TitleScreenFML = configuration.getBoolean("Show FML version", CATEGORY_GENERAL, false, "Show which version of Forge Mod Loader (FML) the client is running.");
        TitleScreenForge = configuration.getBoolean("Show Forge version", CATEGORY_GENERAL, false, "Show which version of Minecraft Forge the client is running.");
        TitleScreenMods = configuration.getBoolean("Show mods loaded", CATEGORY_GENERAL, true, "Show how many mods is loaded.");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }
}