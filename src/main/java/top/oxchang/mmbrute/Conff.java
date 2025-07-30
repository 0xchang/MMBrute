package top.oxchang.mmbrute;

import java.util.ArrayList;
import java.util.List;

public class Conff {
    public static String mode="auto";
    public static String password_file = "";
    public static List<String> passwords = new ArrayList<>();
    public static List<String> worlist = new ArrayList<>();
    public static String wordlist_file = "mm.txt";

    // 计算密集型任务，线程数后续再考虑是否加入
    public static int threadnum = 6;
}
