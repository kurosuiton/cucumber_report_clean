package com.kurosuiton.ccmbrrpcleaner;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Artem_Velichkin
 */
public class JobFilter implements FilenameFilter {

    JobFilter() {
    }

    public boolean accept(File file, String fileName) {
        Pattern p = Pattern.compile("[\\d]*");
        Matcher m = p.matcher(fileName);
        return m.matches();
    }

}
