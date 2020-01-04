package pt.com.santos.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pt.com.santos.util.io.ByteBufferOutputStream;

public final class WebRequest {

    public static Document getHtmlDocument(URL url)
            throws IOException, SAXException {
        return getHtmlDocument(url.openConnection());
    }

    public static Document getHtmlDocument(URLConnection connection)
            throws IOException, SAXException {
        return getHtmlDocument(getReader(connection));
    }

    public static Reader getReader(URLConnection connection)
            throws IOException {
        try {
            connection.addRequestProperty("Accept-Encoding", "gzip,deflate");
            connection.addRequestProperty("Accept-Charset", "UTF-8,ISO-8859-1");
        } catch (IllegalStateException e) {
            // too bad, can't request gzipped document anymore
        }

        Charset charset = getCharset(connection.getContentType());
        String encoding = connection.getContentEncoding();

        InputStream inputStream = connection.getInputStream();

        if ("gzip".equalsIgnoreCase(encoding)) {
            inputStream = new GZIPInputStream(inputStream);
        } else if ("deflate".equalsIgnoreCase(encoding)) {
            inputStream = new InflaterInputStream(inputStream,
                    new Inflater(true));
        }

        return new InputStreamReader(inputStream, charset);
    }

    public static Document getHtmlDocument(Reader reader)
            throws SAXException, IOException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://xml.org/sax/features/namespaces", false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(new InputSource(reader));
            return dom;
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Document getDocument(URL url)
            throws IOException, SAXException {
        return getDocument(url.openConnection());
    }

    public static Document getDocumentD(URL url)
            throws IOException, SAXException {
        return getDocumentD(url.openConnection());
    }

    public static Document getDocument(URLConnection connection)
            throws IOException, SAXException {
        return getDocument(new InputSource(getReader(connection)));
    }

    public static Document getDocumentD(URLConnection connection)
            throws IOException, SAXException {
        return getDocument(new InputSource(connection.getInputStream()));
    }

    public static Document getDocument(InputSource source)
            throws IOException, SAXException {
        try {
            return DocumentBuilderFactory.newInstance().
                    newDocumentBuilder().parse(source);
        } catch (ParserConfigurationException e) {
            // will never happen
            throw new RuntimeException(e);
        }
    }

    public static ByteBuffer fetch(URL resource) throws IOException {
        return fetch(resource, null);
    }

    public static ByteBuffer fetch(URL url,
            Map<String, String> requestParameters) throws IOException {
        URLConnection connection = url.openConnection();

        if (requestParameters != null) {
            for (Entry<String, String> parameter :
                requestParameters.entrySet()) {
                connection.addRequestProperty(parameter.getKey(),
                        parameter.getValue());
            }
        }

        int contentLength = connection.getContentLength();

        InputStream in = connection.getInputStream();
        ByteBufferOutputStream buffer = 
                new ByteBufferOutputStream(contentLength >= 0
                ? contentLength : 32 * 1024);

        try {
            // read all
            buffer.transferFully(in);
        } catch (IOException e) {
            // if the content length is not known in advance an
            // IOException (Premature EOF)
            // is always thrown after all the data has been read
            if (contentLength >= 0) {
                throw e;
            }
        } finally {
            in.close();
        }

        return buffer.getByteBuffer();
    }

    public static ByteBuffer post(HttpURLConnection connection,
            Map<String, String> parameters) throws IOException {
        byte[] postData = encodeParameters(parameters).getBytes("UTF-8");

        // add content type and content length headers
        connection.addRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.addRequestProperty("Content-Length",
                String.valueOf(postData.length));

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // write post data
        OutputStream out = connection.getOutputStream();
        out.write(postData);
        out.close();

        // read response
        int contentLength = connection.getContentLength();

        InputStream in = connection.getInputStream();
        ByteBufferOutputStream buffer = 
                new ByteBufferOutputStream(contentLength >= 0
                ? contentLength : 32 * 1024);

        try {
            // read all
            buffer.transferFully(in);
        } catch (IOException e) {
            // if the content length is not known in advance
            // an IOException (Premature EOF)
            // is always thrown after all the data has been read
            if (contentLength >= 0) {
                throw e;
            }
        } finally {
            in.close();
        }

        return buffer.getByteBuffer();
    }

    private static Charset getCharset(String contentType) {
        if (contentType != null) {
            // e.g. Content-Type: text/html; charset=iso-8859-1
            Matcher matcher = Pattern.compile("charset=(\\p{Graph}+)")
                    .matcher(contentType);

            if (matcher.find()) {
                try {
                    return Charset.forName(matcher.group(1));
                } catch (IllegalArgumentException e) {
                    Logger.getLogger(WebRequest.class.getName())
                            .log(Level.WARNING, e.getMessage());
                }
            }

            // use http default encoding only for text/html
            if (contentType.equals("text/html")) {
                return Charset.forName("ISO-8859-1");
            }
        }

        // use UTF-8 if we don't know any better
        return Charset.forName("UTF-8");
    }

    public static String encodeParameters(Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, String> entry : parameters.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }

            sb.append(entry.getKey());
            sb.append("=");

            try {
                sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // will never happen
                throw new RuntimeException(e);
            }
        }

        return sb.toString();
    }

    /**
     * Dummy constructor to prevent instantiation.
     */
    private WebRequest() {
        throw new UnsupportedOperationException();
    }
}
