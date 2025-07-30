package top.oxchang.mmbrute;


public class MMbrute {
    public static void main(String[] args) {
        // 解析参数
        CliHelp.parse(args);

        for (String pass : Conff.passwords) {
            for (String word : Conff.worlist) {
                switch (Conff.mode) {
                    case "md5":
                        if (Md5utils.verifymd5(pass.toLowerCase(), word)) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "nt":
                        if (Ntlmutils.verifyntlmv1(pass.toLowerCase(), word)) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "bcrypt":
                        if (Bcryutils.verifybcry(word, pass)) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "shadow":
                        if (Shadowutils.verifyshadow(word, pass)) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "jwt":
                        if (Jwtutils.validateToken(pass, word)) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "sha1":
                        if (Sha1Calculator.verifysha1(word, pass)) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "sha256":
                        if (Sha2Calculator.verifysha2(word, pass, "sha256")) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "sha512":
                        if (Sha2Calculator.verifysha2(word, pass, "sha512")) {
                            System.out.println(pass + ":" + word);
                            FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                        }
                        break;
                    case "auto":
                        // auto自动识别是那种加密方式
                        if (pass.matches("\\w{128}")) {
                            if (Sha2Calculator.verifysha2(word, pass, "sha512")) {
                                System.out.println(pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            }
                        } else if (pass.matches("\\w{64}")) {
                            if (Sha2Calculator.verifysha2(word, pass, "sha256")) {
                                System.out.println(pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            }
                        } else if (pass.matches("\\w{40}")) {
                            if (Sha1Calculator.verifysha1(word, pass)) {
                                System.out.println(pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            }
                        } else if (pass.matches("\\w{32}")) {
                            // 应该是md5或者ntlm
                            if (Md5utils.verifymd5(pass.toLowerCase(), word)) {
                                System.out.println("MD5 enc:" + pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            } else if (Ntlmutils.verifyntlmv1(pass.toLowerCase(), word)) {
                                System.out.println("NTLM enc:" + pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            }
                        } else if (pass.matches("^\\$2a\\$10\\$[./0-9A-Za-z]{53}")) {
                            if (Bcryutils.verifybcry(word, pass)) {
                                System.out.println("Bcrypt enc:" + pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            }
                        } else if (pass.matches("\\$1\\$[a-zA-Z0-9./]*\\$[a-zA-Z0-9./]{22}") || pass.matches("\\$5\\$[a-zA-Z0-9./]*\\$[a-zA-Z0-9./]{43}") || pass.matches("\\$6\\$[a-zA-Z0-9./]*\\$[a-zA-Z0-9./]{86}")) {
                            if (Shadowutils.verifyshadow(word, pass)) {
                                System.out.println("Shadow enc:" + pass + ":" + word);
                                FileAppender.appendToFile("Shadow enc:" + pass + ":" + word);
                            }
                        }
                }
            }
        }
    }
}
