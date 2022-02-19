package by.stasfedorenko.command;

public final class PagePath {
    public static final String ERROR_404_PAGE = "${pageContext.request.contextPath}/404.jsp";
    public static final String ERROR_PAGE = "${pageContext.request.contextPath}/error.jsp";
    public static final String INDEX = "${pageContext.request.contextPath}/index.jsp";

    public static final String GO_TO_CREATE_PDF = "run?command=create_pdf";
    public static final String GO_TO_START_BOT = "run?command=start_bot";

    private PagePath() {
    }
}
