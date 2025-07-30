package top.oxchang.mmbrute;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CliHelp {
    public static void parse(String[] args) {
        // 1. 定义可接受的参数选项
        Options options = new Options();
        options.addOption("p", "password", true, "指定加密的密码");
        options.addOption("pf", "passfile", true, "指定加密密码的文件");
        options.addOption("w", "wordlist", true, "指定字典文件，mm.txt(default)");
        options.addOption("m", "mode", true, "指定爆破的模式\nmd5(eg: e10adc3949ba59abbe56e057f20f883e)\nnt(NTLM eg: 32ED87BDB5FDC5E9CBA88547376818D4)\nbcrypt(eg: $2a$10$EY9T1qMaL/edr.ZYE2q5N.uxXcvXO5Szt9AlZD43YN6VMsqr57Ani)\nshadow(eg: $1$2mjeQ.8y$2.18GfkwzArGtpi82l7n91,\n$5$lNZgy4wyTS7B98G6$SBaIXJXzZxqDoQr.Jg1Ut1s.sfYwjDc3ps4fOoQH6y8,\n$6$8xJ2BRJjphy/ndNu$.vOkZAv09O/Vp4E6mBp91W92ql8UnHDfinF8FXhBzzZrlOCktZCEfTfMjhKKnkaVw3AeQEZX1s7biSGfErvbj/)\njwt(eg: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY)\nsha1(eg: 7c4a8d09ca3762af61e59520943dc26494f8941b)\nsha256(eg: 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92)\nsha512(eg: ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413)\nauto(default)");
        options.addOption("t", "thread", true, "设置线程(default 6)");

        HelpFormatter formatter = new HelpFormatter();
        // 2. 创建解析器并处理参数
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("t")) {
                Conff.threadnum = Integer.parseInt(cmd.getOptionValue("t"));
            }
            if (cmd.hasOption("w")) {
                Conff.wordlist_file = cmd.getOptionValue("w");
            }
            if (cmd.hasOption("m")) {
                Conff.mode = cmd.getOptionValue("m");
            }
            if (cmd.hasOption("p")) {
                Conff.passwords.add(cmd.getOptionValue("p"));
            } else if (cmd.hasOption("pf")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(cmd.getOptionValue("pf")))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // 去除首尾空白符并过滤空行
                        String trimmedLine = line.trim();
                        if (!trimmedLine.isEmpty()) {
                            if (!Conff.passwords.contains(trimmedLine)) {
                                Conff.passwords.add(trimmedLine);
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("必须要使用p-p或-pf参数");
                formatter.printHelp("MMBrute ", options);
                System.exit(0);
            }

            // 添加wordlist
            try (BufferedReader reader = new BufferedReader(new FileReader(Conff.wordlist_file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 去除首尾空白符并过滤空行
                    String trimmedLine = line.trim();
                    if (!trimmedLine.isEmpty()) {
                        if (!Conff.worlist.contains(trimmedLine)) {
                            Conff.worlist.add(trimmedLine);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (ParseException e) {
            System.err.println("参数解析错误: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        parse(args);
    }
}
