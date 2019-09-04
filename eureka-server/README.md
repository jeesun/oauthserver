使用Dockerfile构建镜像
```
# 构造指定版本
docker build -t oauthserver-eureka-server:v1.0 .
# 构造latest
docker build -t oauthserver-eureka-server .
```

运行容器
```
docker run -d -p 8761:8761 --name oauthserver-eureka-server00 oauthserver-eureka-server:latest
```