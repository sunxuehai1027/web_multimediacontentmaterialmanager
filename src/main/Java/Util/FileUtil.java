package Util;

import java.io.File;
import java.util.HashMap;

public class FileUtil {

    /**
     * 获取路径下所有文件
     * @param filepath
     * @return
     */
    public static HashMap<Integer, String> getFiles(String filepath) {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {//判断是否为文件
                hashMap.put(1, fileList[i].getName());
                System.out.println("文件：" + fileList[i].getName());
            }
            if (fileList[i].isDirectory()) {//判断是否为文件夹
                hashMap.put(2, fileList[i].getName());
                System.out.println("文件夹：" + fileList[i].getName());
            }
        }
        return hashMap;
    }
}
