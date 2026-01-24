package post.domain.content;

public class CommentContent extends Content {

  private static final int MAX_LENGTH = 100;

  public CommentContent(String text) {
    super(text);
  }

  @Override
  void validateText(String text) {
    if (text == null || text.isBlank() || text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException();
    }
  }

}
