## 安装前请卸载已安装的官方插件避免冲突。

# 前言

因为阿里官方长时间未修复相关bug，所以只能fork一份，修复在使用过程中遇到的bug。

- fork节点为[6c59c8c3](https://github.com/alibaba/p3c/commit/6c59c8c36ecd8722c712d5685b8c3822c1c8b030)


插件已经上传到了jetbrains的插件市场  
欢迎安装[https://plugins.jetbrains.com/plugin/22381-alibaba-java-coding-guidelines-fix-some-bug-](https://plugins.jetbrains.com/plugin/22381-alibaba-java-coding-guidelines-fix-some-bug-)


# 插件版本号说明

> 迭代版本 + idea版本 + P3C_PMD版本

# 已修复的issues

- [issues-898](https://github.com/alibaba/p3c/issues/898)
- [issues-900](https://github.com/alibaba/p3c/issues/900)

# 捐赠(Donate)
你的馈赠将助力我更好的去贡献，谢谢！  
Your gift will help me to contribute better, thank you!

[PayPal](https://paypal.me/godfather1103?locale.x=zh_XC)

支付宝(Alipay)  
![支付宝](pic/hb-300.png)
![支付宝](pic/Alipay-300.png)

微信(WeChat)  
![微信支付](pic/WeChat-300.png)

# P3C

最新版本：黄山版（2022.2.3发布）

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## <font color="green">Preface</font>
> We are pleased to present Alibaba Java Coding Guidelines which consolidates the best programming practices over the years from Alibaba Group's technical teams. A vast number of Java programming teams impose demanding requirements on code quality across projects as we encourage reuse and better understanding of each other's programs. We have seen many programming problems in the past. For example, defective database table structures and index designs may cause software architecture flaws and performance risks. Another example is confusing code structures being difficult to maintain. Furthermore, vulnerable code without authentication is prone to hackers’ attacks. To address these kinds of problems, we developed this document for Java developers at Alibaba.
 
For more information please refer the *Alibaba Java Coding Guidelines*:
- 中文版: 直接下载上方的PDF文件（黄山版）
- English Version: *[Alibaba Java Coding Guidelines](https://alibaba.github.io/Alibaba-Java-Coding-Guidelines)*

## <font color="green">Introduction</font>
The project consists of 3 parts:  
- [PMD implementations](p3c-pmd)  
- [IntelliJ IDEA plugin](idea-plugin)  
- [Eclipse plugin](eclipse-plugin)   

## <font color="green">Rules</font>
<font color="blue">Forty-nine rules are realized based on PMD, please refer the P3C-PMD documentation for more detailed information. Four rules are implemented within IDE plugins (IDEA and Eclipse) as follows:</font>  

- ``[Mandatory]`` Using a deprecated class or method is prohibited.  
   Note: For example, decode(String source, String encode) should be used instead of the deprecated method decode(String encodeStr). Once an interface has been deprecated, the interface provider has the obligation to provide a new one. At the same time, client programmers have the obligation to check out what its new implementation is.
   
- ``[Mandatory]`` An overridden method from an interface or abstract class must be marked with @Override annotation.
   Counter example: For getObject() and get0bject(), the first one has a letter 'O', and the second one has a number '0'. To accurately determine whether the overriding is successful, an @Override annotation is necessary. Meanwhile, once the method signature in the abstract class is changed, the implementation class will report a compile-time error immediately.
   
- ``[Mandatory]`` A static field or method should be directly referred by its class name instead of its corresponding object name.

- ``[Mandatory]`` The usage of hashCode and equals should follow:
    1. Override hashCode if equals is overridden.
    2. These two methods must be overridden for Set since they are used to ensure that no duplicate object will be inserted in Set.
    3. These two methods must be overridden if self-defined object is used as the key of Map.
   Note: String can be used as the key of Map since these two methods have been rewritten.

