## Launch activemq
docker pull rmohr/activemq:5.15.9
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq

## Launch consumer
mvn clean package && docker-compose up --build

activemq configurations are done in the postdeploy.txt and docker-compose

## Test with
curl -u admin:admin -d "body=message" http://localhost:8161/api/message/groups?type=queue
