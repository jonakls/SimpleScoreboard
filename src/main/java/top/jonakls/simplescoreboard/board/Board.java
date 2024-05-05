package top.jonakls.simplescoreboard.board;

import java.util.List;
import java.util.UUID;

public class Board {

  private final String id;
  private String title;
  private List<String> lines;

  public Board(
    final String id,
    final String title,
    final List<String> lines
  ) {
    this.id = id;
    this.title = title;
    this.lines = lines;
  }

  public Board() {
    this.id = UUID.randomUUID()
                .toString();
  }

  public static Board newBoard() {
    return new Board();
  }

  public static Board of(
    final String title,
    final List<String> lines
  ) {
    final String id = UUID.randomUUID()
                        .toString();
    return new Board(id, title, lines);
  }

  public String getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public List<String> getLines() {
    return this.lines;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public void setLines(final List<String> lines) {
    this.lines = lines;
  }

  public void addLine(final String line) {
    this.lines.add(line);
  }
}
