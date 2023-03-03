package net.aragoncraft;

import java.util.logging.Logger;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import net.aragoncraft.commands.balance;
import net.aragoncraft.listeners.events;

public class Aragoncraft extends JavaPlugin {
    private static final Logger log = Logger.getLogger("ARGONCRAFT");

    private static Economy economy = null;
    public static LuckPerms luckpermsAPI;

    @Override
    public void onEnable() {
        if (!setupEconomy() ){
            log.info("[ECON]: Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        luckpermsAPI = LuckPermsProvider.get();

        getCommand("balance").setExecutor(new balance());
        Bukkit.getPluginManager().registerEvents(new events(), this);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if(economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }

    @Override
    public void onDisable() {
        log.info("&6Disabling Aragoncraft");
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    public static Economy getEconomy() {
        return economy;
    }
}