#!/usr/bin/env bash

if ! [ -x /usr/local/bin/brew ];
then
    ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
fi

brew install caskroom/cask/brew-cask
brew cask install docker-compose docker-machine virtualbox
docker-machine create --driver virtualbox default
docker-machine stop default
./open-boot2docker-ports.sh
docker-machine start default
