public class Program {
    public static void main(String[] args) throws Exception {
        JsonToHtml jsonToHtml = new JsonToHtml();
        HtmlToPdf htmlToPdf = new HtmlToPdf();

        jsonToHtml.convertJsonToHtml();
        htmlToPdf.writeHtmlToPdf();
    }
}
