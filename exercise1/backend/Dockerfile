#Need to use a base image
#FROM something

# Workaround for https://github.com/boot2docker/boot2docker/issues/357
# make sure the container can talk to the external resources
RUN echo "8.8.8.8" >  /etc/resolv.conf
RUN echo "8.8.4.4" >>  /etc/resolv.conf

#Commands to get stuff working
