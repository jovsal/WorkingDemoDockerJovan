# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### After installation of vagrant ###
 vagrant plugin install vagrant-vbguest

### Initial start of vagrant box ###
* Windows Key + X ---> command prompt (admin)
* cd {PROJECT PATH}
* vagrant box add centos/7

### All other ###
* Windows Key + X ---> command prompt (admin)
* cd {PROJECT PATH}
* vagrant up (start)
* vagrant suspend (hibernate)
* vagrant halt (shut down)
* vagrant reload (if you have changes in vagrantfile)
* vagrant provision (if have changes in bootstrap.sh)
* vagrant destroy (if you want to delete the current version of the virtual machine)

### To boot in full-screen ###
 from terminal in guest: reboot