package com.ptn.geesetool.utils;

import au.com.bytecode.opencsv.CSVWriter;
import net.coobird.thumbnailator.Thumbnails;

import java.io.*;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
    public static final String PATH = System.getProperty("user.dir");
    public static final String OUTPUT = "out";
    public static final String suffix = "/";
    public static final Pattern p = Pattern.compile("[0-9]+_[0-9]");

    public static void WriteToCSV(String[] strings, String fileName, boolean isAppend) throws IOException {

        File file = new File(PATH + suffix + OUTPUT + suffix + fileName);
        if (!file.exists()) {
            new File(PATH + suffix + OUTPUT).mkdirs();
            FileWriter outputfile = new FileWriter(file);
            outputfile.flush();
            outputfile.close();
        }
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(file, true));

            writer.writeNext(strings);
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }

    public static String[] handleTags(String text) {
        String rs1 = text.replaceAll(" T-Shirt", "").trim();
        String rs = text.replaceAll(" T-Shirt", "").trim().replaceAll(" ", ",");

        return new String[]{rs, rs1};
    }

    public static String[] regexTeepublic(String text) {
        String value = "";
        Matcher matcher = p.matcher(text);
        if (matcher.find()) {
            value = matcher.group();
        }
        var link = "https://res.cloudinary.com/teepublic/image/private/s--ttzvyqmM--/l_text:helvetica_16_bold:134de18e2ab48975a040d1a7b6fd7b0f19ccead7,o_0/c_fit,f_png,h_500,q_90,w_500/v1531256391/production/designs/" + value + ".png";

        return new String[]{value, link};
    }
    public static InputStream resizeImage(InputStream originalImage, int targetWidth, int targetHeight, String format) throws Exception {

        ByteArrayInputStream inputStream = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(originalImage)
                    .size(targetWidth, targetHeight)
                    .outputFormat(format)
                    .outputQuality(0.5)
                    .toOutputStream(outputStream);
            byte[] data = outputStream.toByteArray();
            inputStream = new ByteArrayInputStream(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }



    public static void removeDuplicate(String fileName) {
        String csvFile = PATH + suffix + OUTPUT + suffix + fileName;
        BufferedReader br = null;
        String line = "";
        HashSet<String> lines = new HashSet<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lines.add(line)) {
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
