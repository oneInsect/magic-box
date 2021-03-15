#!/bin/bash

INSTALL_HOME=/opt/data/mysql/install/
MYSQL_HOME=/opt/data/mysql/
MYSQL_DATA=${MYSQL_HOME}data/
mkdir -p ${MYSQL_DATA} ${MYSQL_DATA}

cd ${INSTALL_HOME}
tar axvf mysql-8.0.21-1.el8.x86_64.rpm-bundle.tar
rpm -ivh mysql-community-common-8.0.21-1.el8.x86_64.rpm
rpm -ivh mysql-community-libs-8.0.21-1.el8.x86_64.rpm
rpm -ivh mysql-community-client-8.0.21-1.el8.x86_64.rpm
rpm -ivh mysql-community-server-8.0.21-1.el8.x86_64.rpm

now=`date`
sed -i "s#datadir=/var/lib/mysql#datadir=${MYSQL_DATA}#g" /etc/my.cnf
sed -i "s#log-error=/var/log/mysqld.log#log-error=${MYSQL_DATA}mysqld.log#g" /etc/my.cnf

mysqld --initialize
chown mysql:mysql /var/lib/mysql ${MYSQL_DATA} -R
systemctl start mysqld.service
oldpwd=`cat ${MYSQL_DATA}mysqld.log | grep password | awk -F ": " '{print $2}'`
mysqladmin -uroot -p"${oldpwd}" password 'Changeme_123'
mysql -u root -p"Changeme_123" mysql -e "update user set host = '%' where user = 'root';flush privileges;"
echo "${now} complete initialize." >> ${MYSQL_HOME}initialized
