使用Dockerfile构建镜像
```
# 构造指定版本
docker build -t oauthserver-old-task:v1.0 .
# 构造latest
docker build -t oauthserver-old-task .
```
运行容器
```
docker run -d -p 8184:8184 --name oauthserver-old-task00 oauthserver-old-task:latest
```