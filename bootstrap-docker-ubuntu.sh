sudo apt-get update
sudo apt-get install docker-engine
sudo docker run hello-world

#THIS IS SO UGLY!!!
curl -L https://github.com/docker/compose/releases/download/VERSION_NUM/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
docker-compose --version
