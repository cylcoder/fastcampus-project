package user.domain;

public class Info {

  private final String name;
  private final String profileImageUrl;

  public Info(String name, String profileImageUrl) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.profileImageUrl = profileImageUrl;
  }

}
