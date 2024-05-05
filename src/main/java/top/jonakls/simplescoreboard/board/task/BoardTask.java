package top.jonakls.simplescoreboard.board.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.jonakls.simplescoreboard.board.Board;
import top.jonakls.simplescoreboard.board.BoardManager;

import java.util.Collection;
import java.util.List;

public class BoardTask implements Runnable {

  private final BoardManager boardManager;
  private boolean update;
  private Board board;

  public BoardTask(
    final BoardManager boardManager
  ) {
    this.boardManager = boardManager;
    this.update = true;
  }

  @Override
  public void run() {
    final Collection<? extends Player> players = Bukkit.getOnlinePlayers();

    if (players.isEmpty()) {
      return;
    }

    if (this.update) {
      this.board = Board.of(
        "<Red>Title",
        List.of(
          "<Green>Line 1",
          "<Green>Line 2",
          "<Green>Line 3",
          "<Green>Line 4",
          "<Green>Line 5",
          "<Green>Line 6",
          "<Green>Line 7",
          "<Green>Line 8",
          "<Green>Line 9",
          "<Green>Line 10"
        )
      );
      this.update = false;
    } else {
      this.board = Board.of(
        "<Red>Title",
        List.of(
          "<blue>Line 1",
          "<blue>Line 2",
          "<blue>Line 3",
          "<blue>Line 4",
          "<blue>Line 5",
          "<blue>Line 6",
          "<blue>Line 7",
          "<blue>Line 8",
          "<blue>Line 9",
          "<blue>Line 10"
        )
      );
      this.update = true;
    }

    players.forEach(player -> this.boardManager.update(player, this.board));
  }
}
