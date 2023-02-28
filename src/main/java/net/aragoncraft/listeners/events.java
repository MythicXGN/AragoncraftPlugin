package net.aragoncraft.listener;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.platform.PlayerAdapter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;



public class events implements Listener {
    private LuckPerms luckPerms;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Welcome to AragonCraft, " + event.getPlayer().getName() + "!");
        hubscoreBoard(event.getPlayer());
    }

    public void hubscoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard hubboard = manager.getNewScoreboard();
        Objective obj = hubboard.registerNewObjective("hubScoreboard", "AragonCraft",
                ChatColor.translateAlternateColorCodes('&', ""));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(ChatColor.BLUE + "=-=-=-=-=-=-=-=-=-=");
        score.setScore(3);

        Score score1 = obj.getScore(ChatColor.DARK_AQUA + "Online Players: " + ChatColor.GOLD + Bukkit.getOnlinePlayers().size());
        score1.setScore(2);

        PlayerAdapter<Player> adapter = luckPerms.getPlayerAdapter(Player.class);
        CachedMetaData metaData = adapter.getMetaData(player);

        Score score2 = obj.getScore(ChatColor.DARK_AQUA + "Rank: " + "");

        player.setScoreboard(hubboard);
    }
}