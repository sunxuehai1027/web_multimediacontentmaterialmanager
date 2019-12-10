package Util;

public class TypeUtil {

    public static final String[] VIDEO_SUFFIX = new String[]{"MP4", "FLV", "AVI", "MPG", "MOV"};

    public static final String[] AUDIO_SUFFIX = new String[]{"MP3", "OGG", "WAV", "AIF", "AU", "RAM", "WMA", "AAC", "FLAC"};

    public static final String[] TEXT_SUFFIX = new String[]{"TXT", "LOG", "XLS", "XLSX", "DOC", "DOCX", "LIC"};

    public static final String[] PIC_SUFFIX = new String[]{"PIC", "JPG", "BMP", "GIF", "PNG", "TIF"};

    public static final String[] PRESS_SUFFIX = new String[]{"ZIP", "RAR", "Z", "GZ", "ARJ"};

    /**
     * 根据文件的后缀名获得文件的分类
     *
     * @param suffix
     * @return
     */
    public static int getFileTypeBySuffix(String suffix) {
        if (suffix == null) {
            return -1;
        }
        if (of(suffix, VIDEO_SUFFIX)) {
            return 3;
        } else if (of(suffix, PIC_SUFFIX)) {
            return 2;
        } else if (of(suffix, TEXT_SUFFIX)) {
            return 1;
        } else if (of(suffix, AUDIO_SUFFIX)) {
            return 4;
        } else if (of(suffix, PRESS_SUFFIX)) {
            return 5;
        }
        return 0;
    }

    public static boolean of(String value, String[] group) {
        for (String s : group) {
            if (s.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
