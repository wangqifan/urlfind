# urlfind
配置
----
首先需要下载IEdriver  在IE中导入证书 
https://github.com/wangqifan/urlfind/blob/master/ca-certificate-rsa.cer

依赖
----
参见pom文件

~~~
 <dependencies>

         <!-- https://mvnrepository.com/artifact/net.lightbody.bmp/browsermob-core-->
         <dependency>
             <groupId>net.lightbody.bmp</groupId>
             <artifactId>browsermob-core</artifactId>
             <version>2.1.5</version>
             <exclusions>
                 <exclusion>
                     <artifactId>guava</artifactId>
                     <groupId>com.google.guava</groupId>
                 </exclusion>
             </exclusions>
         </dependency>
         <dependency>
             <groupId>net.lightbody.bmp</groupId>
             <artifactId>browsermob-legacy</artifactId>
             <version>2.1.5</version>
             <exclusions>
                 <exclusion>
                     <artifactId>guava</artifactId>
                     <groupId>com.google.guava</groupId>
                 </exclusion>
             </exclusions>
         </dependency>
         <dependency>
             <groupId>org.seleniumhq.selenium</groupId>
             <artifactId>selenium-java</artifactId>
             <version>3.3.0</version>
         </dependency>

     </dependencies>
~~~
