FROM tomcat:9.0.64-jre11
ADD target/AcademicRegistrationSystem-1.0.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]