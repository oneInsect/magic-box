#!/bin/bash

java -jar -Dspring.profiles.active=dev -Dspring.location.config=/opt/fileserver/ /opt/fileserver/service-0.0.1-SNAPSHOT.jar