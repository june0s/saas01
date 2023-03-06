import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UnitTest {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String uuid = UUID.randomUUID().toString();
        System.out.println(formatted + " " + uuid);

    }
}
