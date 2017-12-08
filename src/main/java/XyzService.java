import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Random;

public class XyzService {
    public String xyzFn(Target target) {
        try {
            String string = String.format("%02d", target.getPublishedOn().getDayOfMonth());
            string += target.getCategoryPrefix();
            string += target.getKind().replace("_", "");

            if (target.getHasPersonal()) {
                string += MessageFormat.format("_{0}", target.getAge()) != null ? target.getAge() : 0;
            }

            string += "_" + target.getId();
            MessageDigest md = null;
            md = MessageDigest.getInstance("SHA1");
            byte[] result = md.digest(String.valueOf(new Random().nextInt()).getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }

            string += "_" + sb.toString().substring(0, 8);
            String truncatedTitle = target.getTitle().toLowerCase().replaceAll("[^A-Za-z]+", "");
            int length = truncatedTitle.length();
            int truncateTo = length > 9 ? 9 : length;
            string += "_" + truncatedTitle.substring(0, truncateTo+1);
            string += ".jpg";
            return string;
        } catch (Exception e) {
            return "";
        }
    }

}
