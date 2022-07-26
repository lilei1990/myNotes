import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public class PathDir {
    static File file = new File("D:/hugo/web/content/posts");
    static File fl = new File("D:/hugo/web/content/");

    public static void main(String[] args) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(System.currentTimeMillis());
        stringBuffer.append("---\n" +
                "author: \"lei\"\n" +
                "date: \""+time+"\"\n" +
                "---\n");
        stringBuffer.append("#### 目录 \n\n\n");
        printFile(file, 0, "..");
    }

    static StringBuffer stringBuffer = new StringBuffer();

    public static void printFile(File file, int level, String path) {
        if (file.getName().equals("imagers")) {
            return;
        }
        //如果文件路径是个目录,就有子集
        for (int i = 0; i < level; i++) {
            stringBuffer.append("\t");
        }
        if (file.isDirectory()) {
            //"<details> %s </details>"
            stringBuffer.append("<ul>\n");
            //层级太深就折叠
            if (level > 0) {
                stringBuffer.append("<details >\n");
            } else {
                stringBuffer.append("<details  open>\n");
            }

            stringBuffer.append("<summary>" + file.getName() + "</summary>\n");
            //循环子文件
            File[] files1 = file.listFiles((pathname) -> {
                return pathname.isDirectory();
            });
            File[] files2 = file.listFiles((pathname) -> {
                return !pathname.isDirectory();
            });

            for (File temp : files1) {
                printFile(temp, level + 1, path + "/" + file.getName());
            }
            for (File temp : files2) {
                printFile(temp, level + 1, path + "/" + file.getName());
            }
            stringBuffer.append("</details>\n");
            stringBuffer.append("</ul>\n");
        } else {
            //ul
            stringBuffer.append("<ul>");
            stringBuffer.append("<a href=" + path + "/" + file.getName().replace(".md", "") + ">" + file.getName() + "</a>\n");
            stringBuffer.append("</ul>\n");
        }
        saveFile(stringBuffer.toString());
    }

    private static void saveFile(String str) {
        try {
            // 准备文件666.txt其中的内容是空的
//            File f1 = new File("D:/666.md");
            File f1 = new File(fl.getPath() + "/dir.md");
            if (!f1.exists()) {
                f1.getParentFile().mkdirs();
            }
            // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
//            byte[] data = str.getBytes();
            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f1);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            // 把数据写入到输出流
            osw.write(str);
            // 关闭输出流
            osw.close();
//            println("输入完成")
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
