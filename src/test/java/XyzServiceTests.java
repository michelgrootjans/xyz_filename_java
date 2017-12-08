import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;

import static org.mockito.Mockito.*;

public class XyzServiceTests {
    @Test
    public void name() {
        Target target = mock(Target.class);
        when(target.getPublishedOn()).thenReturn(LocalDate.of(2017, 12, 8));
        when(target.getCategoryPrefix()).thenReturn("abc");
        when(target.getKind()).thenReturn("unicorn");
        when(target.getHasPersonal()).thenReturn(false);
        when(target.getAge()).thenReturn(4);
        when(target.getId()).thenReturn(1337);
        when(target.getTitle()).thenReturn("magic & superglue");

        assertTrue(new XyzService().xyzFn(target).matches("08abcunicorn_1337_[0-9a-f]{8}_magicsuper.jpg"));
    }

}
