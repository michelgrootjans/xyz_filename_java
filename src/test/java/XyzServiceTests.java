import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

public class XyzServiceTests {
    @Test
    public void name() {
        Target target = mock(Target.class);
        when(target.getPublishedOn()).thenReturn(LocalDate.of(2017, 12, 14));
        when(target.getCategoryPrefix()).thenReturn("abc");
        when(target.getKind()).thenReturn("unicorn");
        when(target.getHasPersonal()).thenReturn(false);
        when(target.getAge()).thenReturn(4);
        when(target.getId()).thenReturn(1337);
        when(target.getTitle()).thenReturn("magic & superglue");

        String expected = "14abcunicorn_1337_3f4894ca_magicsuper.jpg";

        String result = blah(target);
        assertThat(result, is(expected));
        assertTrue(result.split("_")[2].matches("[0-9a-f]{8}"));
//        assertThat(blah(target), matches("[]"));

    }

    private String blah(Target target) {
        String filename = String.valueOf(target.getPublishedOn().getDayOfMonth());
        filename += target.getCategoryPrefix();
        filename += target.getKind().replace("_", "");

        if (target.getHasPersonal()) {
            filename += MessageFormat.format("_{0}", target.getAge()) != null ? target.getAge() : 0;
        }

        filename += "_" + target.getId();
        filename += "_" + sha1().substring(0, 8);
        String truncatedTitle = target.getTitle().toLowerCase().replaceAll("[^A-Za-z]+", "");
        int length = truncatedTitle.length();
        int truncateTo = length > 9 ? 9 : length;
        filename += "_" + truncatedTitle.substring(0, truncateTo);
        filename += ".jpg";
        return filename;
    }

    private String sha1() {
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
        } catch (Exception e) {
        }
        String input = String.valueOf(new Random().nextInt());
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();    }
}
