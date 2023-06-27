FROM khipu/openjdk17-alpine
COPY ./target/*.jar Admin_Api.jar
ENTRYPOINT [ "java","-jar","Admin_Api.jar" ]