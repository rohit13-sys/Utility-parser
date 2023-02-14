# 

<h1>Utility-parser<h1>

<h3>This is a utility to parse XML against XSD schema and after that the required data will be parsed and saved in DataBase<h3>

<h4>Following are the Rest-EndPoints which are exposed  to execute operations like uploading xml file and validating them and persisting them in DataBase<h4>

Change the port of applicatin according to your need in application.yml
server:
  port: 8082

There is facility known as liquibase which can do all annoying Database related configurations for you like it can create DB automatically.
You just have to give name of schema and some minor configurations

First you have to configure database properties in src/resources/application.yml
url: {url of your installed Database}/{Your Schema Name}?createDatabaseIfNotExist=true

 liquibase:
   changeLog: classpath:/db/changelog/db.changelog-master.xml
   default-schema: local_db

Make Sure to add these propertiers under Spring section in application.yml

Please refer some configurations for liquibase which are done in /resource folder regarding Database and creation of table(includes schema.sql,db.changelog-master.xml)

1) PostMapping :- 
/xml-parser/
   desc :- this api requires a multipart file in body which is nothing but your xml which needs to be verified and persistd in DataBase
   
   
2) GetMapping :- /xml-parser?newsPaperName=""
   desc :- this api is used for getting newspaper data.
           if you want to search newspaper according to newspapernames then use <b>/xml-parser?newsPaperName=abb</b> as an example and give newspapername as a query parameter
           if you want some paginations features then feel free to insert mopre query parameters like pageno and page size as shown below
             '/xml-parser?newsPaperName=abb&pageNo=0&pageSize=0'
           
       
       
If you want to run test cases please use below command as this is a maven project
- mvn clean test

If you want to make executable jar file then use command 
- mvn clean install

After executing above command jar file will be created with suffix :- "*-SNAPSHOT-0.0.1.jar"
Then open cmd/terminal and go to path project/target/
Execute java -jar {project-name}-SNAPSHOT-0.0.1.jar
And your application will running on the given port from application.yml
   
   
   


