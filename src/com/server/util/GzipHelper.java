package com.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.kobjects.base64.Base64;

/*this class is to compress and decompress the data for http request or response.*/
public class GzipHelper 
{
	  /** 
		*compress the request data
		*/
    public static String compress(String str) throws IOException {

        byte[] blockcopy = ByteBuffer
                .allocate(4)
                .order(java.nio.ByteOrder.LITTLE_ENDIAN)
                .putInt(str.length())
                .array();
        ByteArrayOutputStream os = new ByteArrayOutputStream(str.length());
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(str.getBytes());
        gos.close();
        os.close();
        byte[] compressed = new byte[4 + os.toByteArray().length];
        System.arraycopy(blockcopy, 0, compressed, 0, 4);
        System.arraycopy(os.toByteArray(), 0, compressed, 4,
                os.toByteArray().length);
        return Base64.encode(compressed);

    }

    /** 
	 *decompress the response data
     */
    public static String decompress(String zipText) throws IOException {
        byte[] compressed = Base64.decode(zipText);
        if (compressed.length > 4)
        {
            GZIPInputStream gzipInputStream = new GZIPInputStream(
                    new ByteArrayInputStream(compressed, 4,
                            compressed.length - 4));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int value = 0; value != -1;) {
                value = gzipInputStream.read();
                if (value != -1) {
                    baos.write(value);
                }
            }
            gzipInputStream.close();
            baos.close();
            String sReturn = new String(baos.toByteArray(), "UTF-8");
            return sReturn;
        }
        else
        {
            return "";
        }
    }
}
