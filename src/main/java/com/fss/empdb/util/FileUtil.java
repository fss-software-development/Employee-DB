package com.fss.empdb.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

	private FileUtil(){}

    public static File saveFile(final String UPLOADED_FOLDER,MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        java.nio.file.Path path = Paths.get(UPLOADED_FOLDER + "temp_" + file.getOriginalFilename() );
        Files.write(path, bytes);

        return path.toFile();
    }
    
}
