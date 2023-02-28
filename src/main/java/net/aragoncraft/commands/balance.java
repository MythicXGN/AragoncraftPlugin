package net.aragoncraft.commands;

import net.aragoncraft.Aragoncraft;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class balance implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player player){
            Economy economy = Aragoncraft.getEconomy();
            if (args.length == 0){
                player.sendMessage(ChatColor.GREEN + "Current Balance: " + ChatColor.GOLD  + economy.format(economy.getBalance(player)));
            }else if(args.length == 2 && args[0].equalsIgnoreCase("withdraw")){
                double withdraw_amount = Double.parseDouble(args[1]);
                EconomyResponse response = economy.withdrawPlayer(player, withdraw_amount);
                if (response.transactionSuccess()){
                    player.sendMessage("You have successfully removed: " + economy.format(response.amount));
                    player.sendMessage("Your new balance is: " + economy.format(response.balance));
                }else{
                    player.sendMessage("Failed to withdraw money from your balance.");
                    player.sendMessage(response.errorMessage);
                }
            }
        }
        return true;
    }
}
