#! /bin/sh

cd ..
mvn clean package

cd docker
echo ">>>>> copy spring-docker-0.0.1-SNAPSHOT.jar to workspace"
cp ../target/spring-docker-0.0.1-SNAPSHOT.jar ./

echo ">>>>> docker-compose build image and run..."
docker-compose up --build -d