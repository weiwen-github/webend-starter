# 数据库密码加密解密

## 加密

- 在jar包位置生成密文 D:\maven-repository\org\jasypt\jasypt\1.9.2 路径下打开 cmd命令窗口
- 在cmd命令窗口中输入如下命令，生成密码密文：
  java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="真实密码" password=私钥 algorithm=PBEWithMD5AndDES
- 注意：其中input为你的明文密码，password为你的私钥，algorithm这个是一个规则（建议不要改）

## 解密

- 在jar包位置生成密文 D:\maven-repository\org\jasypt\jasypt\1.9.2 路径下打开 cmd命令窗口
- 在cmd命令窗口中输入如下命令，进行解密:
  java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="加密后的密码" password=私钥 algorithm=PBEWithMD5AndDES

  