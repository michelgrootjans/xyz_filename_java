import java.time.LocalDate;

public interface Target {
    Integer getId();

    String getTitle();

    String getKind();

    String getCategoryPrefix();

    boolean isPersonal();

    Integer getAge();

    LocalDate getPublishedOn();

}
