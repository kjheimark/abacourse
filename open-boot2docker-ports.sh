#!/usr/bin/env bash

VM=$(docker-machine ls -q)

if [ -z "${VM}" ]; then
  VM="boot2docker-vm"
fi

for i in 10001 9000; do
  VBoxManage modifyvm "${VM}" --natpf1 "tcp-port$i,tcp,,$i,,$i";
  VBoxManage modifyvm "${VM}" --natpf1 "udp-port$i,udp,,$i,,$i";
done

for i in {47000..47050}; do
  VBoxManage modifyvm "${VM}" --natpf1 "tcp-port$i,tcp,,$i,,$i";
  VBoxManage modifyvm "${VM}" --natpf1 "udp-port$i,udp,,$i,,$i";
done


# delete ports
#for i in 10100 10106; do
#  VBoxManage modifyvm "${VM}" --natpf1 delete "tcp-port$i";
#  VBoxManage modifyvm "${VM}" --natpf1 delete "udp-port$i";
#done
