import edu.neumont.csc180.tyler.logan.HtmlToPdf;
import org.junit.jupiter.api.Test;

class HtmlToPdfTest {
    HtmlToPdf htmlToPdf = new HtmlToPdf();

    @Test
    void testWriteHtmlToPdf() throws Exception {
        htmlToPdf.writeHtmlToPdf();
    }
}