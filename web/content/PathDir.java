import java.io.*;

public class PathDir {
    static File file = new File("D:/hugo/web/content/posts");
    static File fl = new File("D:/hugo/web/content/");
    public static void main(String[] args) {
        stringBuffer.append("#### 目录 \n\n\n");
        printFile(file, 0,"..");
    }

    static StringBuffer stringBuffer = new StringBuffer();

    public static void printFile(File file , int level,String path) {
        if (file.getName().equals("images")) {
            return;
        }
        //如果文件路径是个目录,就有子集
        for (int i = 0; i < level; i++) {
            stringBuffer.append("\t");
        }
        if (file.isDirectory()) {
            //"<details> %s </details>"
            stringBuffer.append("<ul>\n");
            stringBuffer.append("<details >\n");
            stringBuffer.append("<summary>"+file.getName()+"</summary>\n");
            //循环子文件
            File[] files = file.listFiles();
            for (File temp : files) {
                printFile(temp, level + 1,path+"/"+file.getName());
            }
            stringBuffer.append("</details>\n");
            stringBuffer.append("</ul>\n");
        } else {
            //ul
            stringBuffer.append("<ul>");
            stringBuffer.append("<a href="+path+"/"+file.getName().replace(".md","")+">"+file.getName()+"</a>\n");
            stringBuffer.append("</ul>\n");
        }
        saveFile(stringBuffer.toString());
    }
    private static void saveFile(String str) {
        try {
            // 准备文件666.txt其中的内容是空的
//            File f1 = new File("D:/666.md");
            File f1 = new File(fl.getPath()+"/dir.md");
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