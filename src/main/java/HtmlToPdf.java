import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.*;

public class HtmlToPdf {
    public void writeHtmlToPdf() throws Exception{
        File[] files = new File("html/").listFiles();
        iterateThroughFiles(files);
    }

    private void iterateThroughFiles(File[] files) throws Exception {
        for(File file: files){
            if(file.getName().contains(".html")){
                convertFileToPdf(file);
            }
            else{
                throw new IllegalArgumentException("File must have a '.html' extension");
            }
        }
    }

    private void convertFileToPdf(File file) throws Exception{
        String fileWithoutDomain = file.getName().replace(".html", "");
        String fileAsHtm = file.getName().substring(0, file.getName().length()-1);
        try (OutputStream os = new FileOutputStream(String.format("pdf/%s.pdf", fileWithoutDomain))) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri(String.format("file:html/%s", file.getName()));
            builder.toStream(os);
            builder.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception");
        }
    }
}
