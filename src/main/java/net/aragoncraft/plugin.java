package net.aragoncraft;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import net.aragoncraft.commands.balancecommand;
import net.aragoncraft.listener.events;

public class plugin extends JavaPlugin {
    private static plugin plugin;
    private static final Logger log = Logger.getLogger("Minecraft");

    private static Economy economy = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ){
            System.out.println("[ARGONCRAFT ECON]: Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("balance").setExecutor(new balancecommand());
        Bukkit.getPluginManager().registerEvents(new events(), this);
        plugin = this;
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
        Bukkit.getLogger().info("&6Disabling Aragoncraft");
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    public static Economy getEconomy() {
        return economy;
    }
}