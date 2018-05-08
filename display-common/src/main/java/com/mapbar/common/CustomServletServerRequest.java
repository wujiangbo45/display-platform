package com.navinfo.opentsp.qingqi.common.util;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 重写getBody和getBodyFromServletRequestParameters
 * @author: wujiangbo
 * @Create: 2017/09/21 下午4:15
 */
public class CustomServletServerRequest extends ServletServerHttpRequest{
    /**
     * Construct a new instance of the ServletServerHttpRequest based on the given {@link HttpServletRequest}.
     *
     * @param servletRequest the servlet request
     */

    private HttpServletRequest servletRequest;

    private InputStream inputStream;

    @Override
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public CustomServletServerRequest(HttpServletRequest servletRequest) {
        super(servletRequest);
        this.servletRequest = servletRequest;
    }

    @Override
    public InputStream getBody() throws IOException {
        if (isForm(super.getServletRequest())) {
            return getBodyFromServletRequestParameters(super.getServletRequest());
        }
        else if (checkInputStreamIsNull(this.servletRequest.getInputStream())){
            return inputDefaultStream();
        }else{
            return this.inputStream;
        }
    }

    private boolean checkInputStreamIsNull(InputStream inputStream) throws IOException {
        boolean flag = false;
        if (inputStream == null) {
            flag = true;
        }
        else if (inputStream.markSupported()) {
            inputStream.mark(1);
            flag = (inputStream.read() == -1);
        }
        else {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
            int b = pushbackInputStream.read();
            if (b == -1) {
                flag = true;
            }
            this.inputStream = pushbackInputStream;
            pushbackInputStream.unread(b);

        }
        return flag;
    }


    private InputStream inputDefaultStream() throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        Writer writer = new OutputStreamWriter(bos, FORM_CHARSET);
        writer.write("{}");
        writer.flush();
        return new ByteArrayInputStream(bos.toByteArray());
    }

    private static boolean isForm(HttpServletRequest request) {
        String contentType = request.getContentType();
        return HttpMethod.GET.matches(request.getMethod()) || contentType != null && contentType.contains(FORM_CONTENT_TYPE);
    }

    private static InputStream getBodyFromServletRequestParameters(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        Writer writer = new OutputStreamWriter(bos, FORM_CHARSET);

        Map<String, String[]> form = request.getParameterMap();
        if (form.isEmpty()){
            writer.write("{}");
        }
        for (Iterator<String> nameIterator = form.keySet().iterator(); nameIterator.hasNext();) {
            String name = nameIterator.next();
            List<String> values = Arrays.asList(form.get(name));
            for (Iterator<String> valueIterator = values.iterator(); valueIterator.hasNext();) {
                String value = valueIterator.next();
                writer.write(URLEncoder.encode(name, FORM_CHARSET));
                if (value != null) {
                    writer.write('=');
                    writer.write(URLEncoder.encode(value, FORM_CHARSET));
                    if (valueIterator.hasNext()) {
                        writer.write('&');
                    }
                }
            }
            if (nameIterator.hasNext()) {
                writer.append('&');
            }
        }
        writer.flush();

        return new ByteArrayInputStream(bos.toByteArray());
    }
}
