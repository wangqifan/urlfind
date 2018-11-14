# urlfind
配置
----
首先需要下载chormedriver   http://npm.taobao.org/mirrors/chromedriver/
查看自己电脑chorme浏览器版本，然后去下对应的chormedriver版本  下载好 解压放到C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe

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
