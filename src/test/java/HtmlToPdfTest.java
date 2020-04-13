import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HtmlToPdfTest {
    HtmlToPdf htmlToPdf = new HtmlToPdf();

    @Test
    void testWriteHtmlToPdf() throws Exception {
        htmlToPdf.writeHtmlToPdf();
    }
}