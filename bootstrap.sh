#!/bin/bash
yum update
yum -y install epel-release 
yum -y groupinstall "X Window system"
yum -y groupinstall "MATE Desktop"
systemctl isolate graphical.target
systemctl set-default graphical.target
yum -y install wget
yum -y install curl
mkdir /opt/java && cd /opt/java
wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u111-b14/jdk-8u111-linux-x64.rpm"
yum install /opt/java/jdk-8u111-linux-x64.rpm -y
mkdir /opt/maven && cd /opt/maven
wget http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
tar xvf apache-maven-3.3.9-bin.tar.gz
ln -s /opt/maven/apache-maven-3.3.9/bin/mvn /usr/bin/mvn
export PATH=/opt/maven/apache-maven-3.3.9/bin:$PATH
export M2_HOME=/opt/maven
export M2=$M2_HOME/bin 
export PATH=$M2:$PATH
yum -y install git
mkdir /opt/idea && cd /opt/idea
wget http://download.jetbrains.com/idea/ideaIU-2016.3.tar.gz
tar xvf ideaIU-2016.3.tar.gz
mv idea-IU-163.7743.44 /usr/lib
ln -sf /usr/lib/idea-IU-163.7743.44 /usr/lib/idea
ln -sf /usr/lib/idea/bin/idea.sh /usr/bin/idea
sysctl -p
curl -L "https://github.com/docker/compose/releases/download/1.9.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
