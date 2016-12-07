# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
	config.vm.box="centos/7"
	config.vm.provision :shell, path: "bootstrap.sh"
    config.vm.provision "docker" do |d|
	d.pull_images "rabbitmq"
	d.run "rabbitmq"
	d.run "cassandra",
		args: "--name library-cassandra -d cassandra:2.2.8"
	end
    config.vm.synced_folder ".", "/vagrant", type: "virtualbox"
	config.vm.provider "virtualbox" do |vb|
		vb.gui = true
		vb.memory = 4096
		vb.cpus = 2
	end
	
end
