/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.stats.StatBase
 *  net.minecraftforge.common.AchievementPage
 */
package com.mrcrayfish.furniture;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;

public class FurnitureAchievements {
    public static Map<String, Achievement> achievements = new HashMap<String, Achievement>();
    public static Achievement installMod;
    public static Achievement mineKea;
    public static Achievement placeTree;
    public static Achievement unwrapPresent;
    public static Achievement houseParty;
    public static Achievement applianceCity;
    public static Achievement cookItem;
    public static Achievement buyItem;
    public static Achievement freezeItem;
    public static Achievement copyItem;
    public static Achievement whatDidYouEat;
    public static Achievement mailBox;
    public static Achievement sendMail;
    public static Achievement firstMail;
    public static Achievement donator;
    public static Achievement privacy;
    public static Achievement tapped;
    public static Achievement heyeyey;
    public static Achievement dingDong;
    public static Achievement careful;
    public static Achievement allClean;
    public static Achievement modernTechnology;
    public static Achievement gardening;
    public static Achievement bathroom;
    public static AchievementPage page;

    public static void loadAchievements() {
        installMod = new Achievement("achievement.cfm_install", "cfm_install", 0, 0, new ItemStack(MrCrayfishFurnitureMod.itemCrayfish), null).registerStat();
        mineKea = new Achievement("achievement.cfm_minekea", "cfm_minekea", 2, 0, new ItemStack(MrCrayfishFurnitureMod.itemChairWood), installMod);
        placeTree = new Achievement("achievement.cfm_placetree", "cfm_placetree", 3, 1, new ItemStack(MrCrayfishFurnitureMod.itemTree), mineKea);
        unwrapPresent = new Achievement("achievement.cfm_unwrappresent", "cfm_unwrappresent", 4, 2, new ItemStack(MrCrayfishFurnitureMod.itemPresentRed), placeTree);
        privacy = new Achievement("achievement.cfm_privacy", "cfm_privacy", 4, 0, new ItemStack(MrCrayfishFurnitureMod.itemCurtains), mineKea);
        applianceCity = new Achievement("achievement.cfm_appliancecity", "cfm_appliancecity", 1, 2, new ItemStack(MrCrayfishFurnitureMod.itemOven), installMod);
        cookItem = new Achievement("achievement.cfm_cookitem", "cfm_cookitem", 2, 3, new ItemStack(Items.cooked_chicken), applianceCity);
        freezeItem = new Achievement("achievement.cfm_freezeitem", "cfm_freezeitem", 0, 3, new ItemStack(Blocks.ice), applianceCity);
        modernTechnology = new Achievement("achievement.cfm_moderntechnology", "cfm_moderntechnology", -3, 2, new ItemStack(MrCrayfishFurnitureMod.itemComputer), installMod);
        buyItem = new Achievement("achievement.cfm_buyitem", "cfm_buyitem", -2, 1, new ItemStack(Items.emerald), modernTechnology);
        copyItem = new Achievement("achievement.cfm_copyitem", "cfm_copyitem", -4, 1, new ItemStack(Items.book), modernTechnology);
        houseParty = new Achievement("achievement.cfm_houseparty", "cfm_houseparty", -3, 0, new ItemStack(MrCrayfishFurnitureMod.itemStereo), modernTechnology);
        heyeyey = new Achievement("achievement.cfm_heyeyey", "cfm_heyeyey", -4, 3, new ItemStack(MrCrayfishFurnitureMod.itemTV), modernTechnology);
        dingDong = new Achievement("achievement.cfm_dingdong", "cfm_dingdong", -3, 4, new ItemStack(MrCrayfishFurnitureMod.itemDoorBell), modernTechnology);
        careful = new Achievement("achievement.cfm_careful", "cfm_careful", -2, 3, new ItemStack(MrCrayfishFurnitureMod.itemElectricFence), modernTechnology);
        gardening = new Achievement("achievement.cfm_gardening", "cfm_gardening", -2, -2, new ItemStack(MrCrayfishFurnitureMod.itemHedgeBasic), installMod);
        mailBox = new Achievement("achievement.cfm_mailbox", "cfm_mailbox", -3, -3, new ItemStack(MrCrayfishFurnitureMod.itemMailBox), gardening);
        sendMail = new Achievement("achievement.cfm_sendmail", "cfm_sendmail", -4, -4, new ItemStack(MrCrayfishFurnitureMod.itemEnvelope), mailBox);
        firstMail = new Achievement("achievement.cfm_firstmail", "cfm_firstmail", -4, -2, new ItemStack(MrCrayfishFurnitureMod.itemPackage), mailBox);
        tapped = new Achievement("achievement.cfm_tapped", "cfm_tapped", -1, -3, new ItemStack(MrCrayfishFurnitureMod.itemTap), gardening);
        donator = new Achievement("achievement.cfm_donator", "cfm_donator", -1, -4, new ItemStack(MrCrayfishFurnitureMod.itemDollar), gardening);
        bathroom = new Achievement("achievement.cfm_bathroom", "cfm_bathroom", 2, -2, new ItemStack(MrCrayfishFurnitureMod.itemBasin), installMod);
        whatDidYouEat = new Achievement("achievement.cfm_whatdidyoueat", "cfm_whatdidyoueat", 3, -3, new ItemStack(MrCrayfishFurnitureMod.itemToilet), bathroom);
        allClean = new Achievement("achievement.cfm_allclean", "cfm_allclean", 1, -3, new ItemStack(MrCrayfishFurnitureMod.itemShower), bathroom);
        page = new AchievementPage("MrCrayfish's Furniture Mod", new Achievement[]{installMod, mineKea, placeTree, unwrapPresent, privacy, applianceCity, cookItem, freezeItem, modernTechnology, buyItem, copyItem, houseParty, heyeyey, dingDong, careful, gardening, mailBox, sendMail, firstMail, tapped, donator, bathroom, whatDidYouEat, allClean});
    }

    public static void registerPage() {
        AchievementPage.registerAchievementPage((AchievementPage)page);
    }

    public static void triggerAchievement(EntityPlayer player, String name) {
        player.triggerAchievement((StatBase)achievements.get(name));
    }
}

