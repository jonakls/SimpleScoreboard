package top.jonakls.simplescoreboard.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoardManager {

  private final Map<String, BoardHandler> boardMap = new ConcurrentHashMap<>();

  public void register(final Player player) {
    final Board board = Board.of(
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

    final BoardHandler handler = new BoardHandler(board);
    handler.update(player);

    this.boardMap.put(player.getUniqueId()
                        .toString(), handler);
  }

  public void unregister(final Player player) {
    final String id = player.getUniqueId()
                        .toString();
    final BoardHandler handler = this.boardMap.get(id);
    if (handler != null) {
      handler.remove(player);
      this.boardMap.remove(id);
    }
  }

  public void update(final Player player, final Board board) {
    final BoardHandler handler = this.boardMap.get(player.getUniqueId()
                                                     .toString());

    if (handler != null) {
      handler.setBoard(board);
      handler.update(player);
    }
  }

  public void clear() {
    Bukkit.getOnlinePlayers()
      .forEach(this::unregister);
    this.boardMap.clear();
  }
}
