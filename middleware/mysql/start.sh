#!/bin/bash

now=`date`

if [[ ! -f /opt/mysql/initialized ]]
then
    mysqld --initialize
    chown mysql:mysql /var/lib/mysql -R
    systemctl start mysqld.service
    oldpwd=`cat /var/log/mysqld.log | grep password | awk -F ": " '{print $2}'`
    mysqladmin -uroot -p"${oldpwd}" password 'Changeme_123'
    echo "${now} complete initialize." >> /opt/mysql/initialized
else
    systemctl start mysqld.service
    echo "${now} restart mysql container." >> /opt/mysql/initialized
fi

while true
do
    sleep 1000
done