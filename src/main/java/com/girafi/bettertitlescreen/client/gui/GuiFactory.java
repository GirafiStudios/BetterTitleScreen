/*
package com.girafi.bettertitlescreen.client.gui;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import com.girafi.bettertitlescreen.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.DefaultGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class GuiFactory extends DefaultGuiFactory {

    public GuiFactory() {
        super(Reference.MOD_ID, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen, getConfigElements(), Reference.MOD_ID, false, false, title);
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();

        List<IConfigElement> listCustomText = new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.CATEGORY_CUSTOM_TEXT)).getChildElements();
        List<IConfigElement> general = new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements();

        list.add(new DummyConfigElement.DummyCategoryElement(I18n.format(Reference.MOD_ID + ".config.category.listCustomText.title"), Reference.MOD_ID + ".config.category.listCustomText", listCustomText));
        list.add(new DummyConfigElement.DummyCategoryElement(I18n.format(Reference.MOD_ID + ".config.category.general.title"), Reference.MOD_ID + ".config.category.general", general));

        return list;
    }
}*/
