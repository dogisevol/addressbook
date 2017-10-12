FORMAT: 1A
HOST:

# Application 

Addressbook is a simple application to maintain an address book

## Build
	mvn package

## Start server
	java -jar target/addressbook-0.0.1-SNAPSHOT.jar
	or
	mvn spring-boot:start
	
## Other
	H2 console is available on http://localhost:8080/h2-console/	
	JDBC URL: jdbc:h2:mem:communication

## API	
    TBD