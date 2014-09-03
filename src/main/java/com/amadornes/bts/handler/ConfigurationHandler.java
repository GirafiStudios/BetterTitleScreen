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
    public static String CATEGORY_CUSTOM_TEXT="Custom Text";
    public static String[] TitleScreenText;
    public static boolean TitleScreenMods;
    public static boolean TitleScreenForge;
    public static boolean TitleScreenFML;
    public static boolean TitleScreenMCP;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        TitleScreenText = configuration.getStringList("Text", CATEGORY_CUSTOM_TEXT, new String[]{"§4B§6E§eT§2T§3E§1R §5T§4I§6T§eL§2E §3S§1C§5R§4E§6E§eN"}, "Insert the text you want here.(Supports multiple lines)");
        TitleScreenMods = configuration.getBoolean("Show mods loaded", CATEGORY_GENERAL, true, "Show how many mods is loaded(W.I.P.)");
        TitleScreenForge = configuration.getBoolean("Show Forge version", CATEGORY_GENERAL, false, "Show which version of Forge the client is running.(W.I.P.)");
        TitleScreenFML = configuration.getBoolean("Show FML version", CATEGORY_GENERAL, false, "Show which version of FML the client is running.(W.I.P.)");
        TitleScreenMCP = configuration.getBoolean("Show MCP version", CATEGORY_GENERAL, false, "Show which version of MCP the client is running.(W.I.P.)");

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