package proxy;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DeserializeObject {

    public static Object deserialization(boolean isZip, String folder, String fullFileName) {

        if (isZip) {
            return deserializationFromZip(folder, fullFileName);
        } else {
            return deserializationFromFile(folder, fullFileName);
        }

    }

    private static Object deserializationFromFile(String folder, String fileName) {
        Object objFromFile = new Object();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(folder + fileName + ".bin"))) {
            objFromFile = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return objFromFile;

    }

    private static Object deserializationFromZip(String folder, String fileName) {
        Object objFromZip = new Object();
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(folder + fileName + ".zip"))) {
            ZipEntry entry = zipIn.getNextEntry();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = zipIn.read(buffer, 0, buffer.length)) > 0) {
                bos.write(buffer, 0, len);
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);

            objFromZip = in.readObject();
            zipIn.closeEntry();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return objFromZip;
    }
}

