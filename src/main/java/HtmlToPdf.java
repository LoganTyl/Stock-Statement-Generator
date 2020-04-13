import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class HtmlToPdf {
    public void writeHtmlToPdf() throws Exception{
        try (OutputStream os = new FileOutputStream("/Users/me/Documents/pdf/out.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri("file:///Users/me/Documents/pdf/in.htm");
            builder.toStream(os);
            builder.run();
        }
    }
}
