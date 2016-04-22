package cn.com.octodata.auth.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Xiang Wei Kong on 2015/12/16.
 */
@Component
public class HttpRequestUtil {

    private static final Log LOG = LogFactory.getLog(HttpRequestUtil.class);

    @Async
    public void sendPost(String url, String body) {
        try {
            URL realUrl = new URL(url);
            URLConnection urlConnection = realUrl.openConnection();
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("content-type", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            try (PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream())) {
                printWriter.print(body);
                printWriter.flush();
            }
            urlConnection.getInputStream().close();
        } catch (Exception e) {
            LOG.warn("=== httpRequestUtil.sendPost(...) Exception, url:[" + url + "],Exception messages: " +  e.getMessage() );
        }
    }
}
