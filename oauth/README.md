使用Dockerfile构建镜像
```
# 构造指定版本
docker build -t oauthserver-api:v1.0 .
# 构造latest
docker build -t oauthserver-api .
```
运行容器
```
docker run -d -p 8181:8181 --name oauthserver-api00 oauthserver-api:latest
```