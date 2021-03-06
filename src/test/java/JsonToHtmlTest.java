import edu.neumont.csc180.tyler.logan.JsonToHtml;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class JsonToHtmlTest {
    JsonToHtml jsonToHtml = new JsonToHtml();
    @Test
    void localDateWithStringFormat() {
        //arrange
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String expected = "2020/04/12";
        //act
        LocalDateTime now = LocalDateTime.now();
        String actual = String.format("%s", dtf.format(now));
        //assert
        assertEquals(expected, actual);
    }

    @Test
    void testConvertJsonToHtml() {
        jsonToHtml.convertJsonToHtml();
    }
}