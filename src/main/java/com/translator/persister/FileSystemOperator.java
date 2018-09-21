package com.translator.persister;

import java.io.InputStream;
import java.util.Map;

public interface FileSystemOperator {
    String readText(String filePath);

    void save(Map<String, InputStream> files);
}
