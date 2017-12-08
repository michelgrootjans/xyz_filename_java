import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.LocalDate;

public interface Target {
    LocalDate getPublishedOn();

    String getCategoryPrefix();

    String getKind();

    boolean getHasPersonal();

    Integer getId();

    String getTitle();

    Integer getAge();
}
