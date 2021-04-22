# 基础镜像使用java
FROM mcr.microsoft.com/java/jdk:8-zulu-ubuntu
# 将jar包添加到容器中并更名为app.jar
ADD luren-wechat.jar app.jar 
# 运行jar包
RUN rm -f /etc/localtime
COPY localtime /etc/
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
