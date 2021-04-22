FROM mcr.microsoft.com/java/jdk:8-zulu-ubuntu

VOLUME /tmp
VOLUME /home/healthplatform/uploadPath 

ADD healthplatform-admin.jar app.jar 

RUN rm -f /etc/localtime
COPY localtime /etc/
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
