package proxy;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SerializeObject {

    public static void serialization(boolean isZip, Object obj, String folder, String fullFileName) {

        if (isZip) {
            serializationToZip(obj, folder, fullFileName);
        } else {
            serializationToFile(obj, folder, fullFileName);
        }
    }

    private static void serializationToFile(Object obj, String folder, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(folder + fileName + ".bin"))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void serializationToZip(Object obj, String folder, String fileName) {

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(folder + fileName + ".zip"))) {
            zipOut.putNextEntry(new ZipEntry(fileName + ".bin"));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);

            byte[] buffer = bos.toByteArray();
            zipOut.write(buffer);
            zipOut.closeEntry();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}