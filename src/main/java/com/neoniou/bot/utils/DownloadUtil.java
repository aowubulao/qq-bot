package com.neoniou.bot.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Neo.Zzj
 * @date 2020/12/9
 */
public class DownloadUtil {

    public static String download(String path, String downloadUrl) {
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/87.0.4280.67");

            String fileName = getFileName(connection);

            FileOutputStream fos = new FileOutputStream(path + fileName);
            InputStream is = connection.getInputStream();

            int byteRead;
            byte[] buffer = new byte[1024];
            while ((byteRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteRead);
            }

            is.close();
            fos.close();

            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getFileName(HttpURLConnection connection) {
        String fileName = connection.toString();
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        return fileName;
    }
}
