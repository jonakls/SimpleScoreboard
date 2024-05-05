package top.jonakls.simplescoreboard.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import top.jonakls.simplescoreboard.board.BoardManager;

public class BoardPlayerListener implements Listener {

  private final BoardManager boardManager;

  public BoardPlayerListener(final BoardManager boardManager) {
    this.boardManager = boardManager;
  }

  @EventHandler(ignoreCancelled = true)
  public void onRegisterBoard(final PlayerJoinEvent event) {
    this.boardManager.register(event.getPlayer());
  }

  @EventHandler(ignoreCancelled = true)
  public void onUnregisterBoard(final PlayerQuitEvent event) {
    this.boardManager.unregister(event.getPlayer());
  }
}
