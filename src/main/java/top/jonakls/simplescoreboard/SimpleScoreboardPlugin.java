package top.jonakls.simplescoreboard;

import org.bukkit.plugin.java.JavaPlugin;
import top.jonakls.simplescoreboard.board.BoardManager;
import top.jonakls.simplescoreboard.board.task.BoardTask;
import top.jonakls.simplescoreboard.listener.BoardPlayerListener;

public class SimpleScoreboardPlugin extends JavaPlugin {

  private final BoardManager boardManager = new BoardManager();

  @Override
  public void onEnable() {
    this.getServer()
      .getPluginManager()
      .registerEvents(
        new BoardPlayerListener(this.boardManager), this
      );

    this.getServer()
      .getScheduler()
      .scheduleSyncRepeatingTask(
        this, new BoardTask(this.boardManager), 0L, 20L
      );
  }

  @Override
  public void onDisable() {
    this.boardManager.clear();
  }
}
