FROM java:openjdk-8-jdk
# Workaround for https://github.com/boot2docker/boot2docker/issues/357
# make sure the container can talk to the external resources
RUN echo "8.8.8.8" >  /etc/resolv.conf
RUN echo "8.8.4.4" >>  /etc/resolv.conf

RUN mkdir -p /opt/backend/
ADD target/*.jar /opt/backend/
ADD run.sh /opt/backend/
WORKDIR /opt/backend
EXPOSE 10001 47000
CMD ./run.sh
