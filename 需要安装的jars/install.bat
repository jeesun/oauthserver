call mvn install:install-file -Dfile=taobao-sdk-java-auto_1455552377940-20160607.jar -DgroupId=com.alibaba -DartifactId=dayu -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=taobao-sdk-java-auto_1455552377940-20160607-source.jar -DgroupId=com.alibaba -DartifactId=dayu-source -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=ojdbc14-10.2.0.3.0.jar -DgroupId=ojdbc14 -DartifactId=ojdbc14 -Dversion=10.2.0.3.0 -Dpackaging=jar
:: webjars官方还没来得及打包2.9.1的element-ui，所以自己打包了一个。
call mvn install:install-file -Dfile=element-ui-2.9.1.jar -DgroupId=org.webjars.npm -DartifactId=element-ui -Dversion=2.9.1 -Dpackaging=jar
call mvn install:install-file -Dfile=element-ui-2.10.1.jar -DgroupId=org.webjars.npm -DartifactId=element-ui -Dversion=2.10.1 -Dpackaging=jar
call mvn install:install-file -Dfile=element-ui-2.12.0.jar -DgroupId=org.webjars.npm -DartifactId=element-ui -Dversion=2.12.0 -Dpackaging=jar
