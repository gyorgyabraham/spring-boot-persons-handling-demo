# spring-boot-persons-handling-demo

This is a sample application that demonstrates a spring-boot based rest api, backed by an MS SQL server.
Requirements:
- java 17
- maven
- microsoft sql server

How to run:
- make sure to have MS SQL server running as per configured in application.properties. 
 Recommendation: `docker run -d --name sqlserver --restart always --platform=linux/amd64 --cap-add SYS_PTRACE --env MSSQL_MAX_MEMORYLIMIT_MB=2048 --env TZ=Europe/Budapest --env 'ACCEPT_EULA=1' --env 'MSSQL_SA_PASSWORD=Admin@123' -p 1433:1433 cjgaspard/mssql-server:2022-ubuntu-20.04`
- make sure to run the attached DDL+DML SQL scripts (dbscripts folder)
- start the Application class
- use the attached postman collection (postman_collection.json) to test the APIs