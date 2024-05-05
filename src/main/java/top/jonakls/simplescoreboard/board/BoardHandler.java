package top.jonakls.simplescoreboard.board;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class BoardHandler {

  private final MiniMessage miniMessage;
  private Board board;
  private final Scoreboard scoreboard;
  private final Objective objective;

  public BoardHandler(Board board) {
    this.miniMessage = MiniMessage.miniMessage();
    this.board = board;

    final ScoreboardManager manager = Bukkit.getServer()
                                        .getScoreboardManager();
    this.scoreboard = manager.getNewScoreboard();
    this.objective = scoreboard.registerNewObjective(
      board.getId(),
      Criteria.DUMMY, miniMessage.deserialize(board.getTitle()));

    this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
  }

  public void update(final Player player) {
    this.scoreboard.getEntries().forEach(this.scoreboard::resetScores);
    this.objective.displayName(miniMessage.deserialize(board.getTitle()));

    for (
      int i = 0;
      i < board.getLines()
            .size();
      i++
    ) {
      final String line = board.getLines()
                            .get(i);
      final Score score = objective.getScore(line);
      score.customName(miniMessage.deserialize(line));
      score.setScore(board.getLines()
                       .size() - i);
    }
    player.setScoreboard(scoreboard);
  }

  public void remove(final Player player) {
    player.setScoreboard(Bukkit.getServer()
                           .getScoreboardManager()
                           .getMainScoreboard());
  }

  public void setBoard(final Board board) {
    this.board = board;
  }
}
