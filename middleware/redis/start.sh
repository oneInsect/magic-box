#!/bin/bash
export SERVER_CMD=/opt/redis/redis-6.0.8/src/redis-server
export CONF=/opt/redis/redis-6.0.8/redis.conf
export REDIS_CLI=/opt/redis/redis-6.0.8/src/redis-cli


function config(){
    sed -i 's/protected-mode yes/protected-mode no/' ${CONF}
    sed -i 's/bind 127.0.0.1/# bind 127.0.0.1/g' ${CONF}
}

function start_server(){
    ln -s ${REDIS_CLI} /usr/local/bin/redis-cli
    ${SERVER_CMD} ${CONF}
}


function main(){
    config
    start_server
}

main
