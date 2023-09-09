sudo docker run --name data_base_docker --network my_docker_network -p 5432:5432 -e POSTGRES_USER=db-username -e POSTGRES_PASSWORD=db-password -e POSTGRES_DB=docker-db  postgres:15.1

