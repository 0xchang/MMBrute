package top.oxchang.mmbrute;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender {
    /**
     * 将字符串追加写入到指定文件
     * @param content 要写入的内容
     * @param filePath 文件路径（默认为output.txt）
     */
    public static void appendToFile(String content, String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            filePath = "output.txt";
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine(); // 自动添加换行
        } catch (IOException e) {
            System.err.println("写入文件失败: " + e.getMessage());
        }
    }

    // 使用默认output.txt路径的重载方法
    public static void appendToFile(String content) {
        appendToFile(content, "output.txt");
    }
}
