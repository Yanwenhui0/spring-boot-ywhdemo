#! /bin/sh

cd ..
mvn clean package

cd docker
echo ">>>>> copy spring-docker-0.0.1-SNAPSHOT.jar to workspace"
cp ../target/spring-docker-0.0.1-SNAPSHOT.jar ./

echo ">>>>> docker build image..."
docker build -t yanwenhui/spring-docker-service:0.1 .
docker rm -f spring-docker-service
docker rmi -f $(docker images -q -f dangling=true)

echo ">>>>> docker run image..."
docker run -d --name spring-docker-service -p 8080:8080 yanwenhui/spring-docker-service:0.1