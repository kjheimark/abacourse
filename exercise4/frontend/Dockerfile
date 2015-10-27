FROM python:2
# Workaround for https://github.com/boot2docker/boot2docker/issues/357
# make sure the container can talk to the external resources
RUN echo "8.8.8.8" >  /etc/resolv.conf
RUN echo "8.8.4.4" >>  /etc/resolv.conf

RUN mkdir -p /opt/frontend/
ADD *.py /opt/frontend/
ADD run.sh /opt/frontend/
WORKDIR /opt/frontend
RUN pip2 install requests --upgrade
EXPOSE 9000
CMD ./run.sh
