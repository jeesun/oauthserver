使用Dockerfile构建镜像
```
# 构造指定版本
docker build -t oauthserver-web:v1.0 .
# 构造latest
docker build -t oauthserver-web .
```
运行容器
```
docker run -d -p 8182:8182 --name oauthserver-web00 oauthserver-web:latest
```