**Apache Repositories Data Collector**

This project is a Java application that uses the GitHub API to find the top 5 repositories of the Apache organization based on the stargazer count. The application retrieves the 100 most recently updated repositories, selects the top 5 based on the number of stargazers, and then fetches the top 10 contributors for each of these repositories. The collected data is then saved to an H2 database.

**Features**
Retrieves the top 5 repositories from the Apache organization based on stargazer count.
Fetches the top 10 contributors for each of the top 5 repositories.
Saves repository and contributor data to an H2 database.
Offers an API endpoint to trigger the data collection task.

**Configuration**
application.properties
You need to configure the GitHub API token in the src/main/resources/application.properties file. To do this, add the following line:

`github.api.token=your_github_api_token_here`

Note: It is crucial to provide a valid GitHub API token in the application.properties file to avoid hitting the API rate limits, which could result in a "maximum API call limit exceeded" error. The GitHub API has a rate limit of 60 requests per hour for unauthenticated requests, and 5000 requests per hour for authenticated requests.

**Logging**
Database operations and logs are configured to be written to the console. This helps in monitoring the application's execution and tracking any issues that may arise during runtime.

**H2 Database**
The application uses an H2 in-memory database to store the retrieved data. To access the H2 database console:

**Run the application.**
Access the H2 console in your browser by navigating to: http://localhost:8080/h2-console.
Use the following credentials to log in:

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password
You can execute SQL queries to view or manage the data stored in the database.

**Building the Project**
To compile and package the project as a JAR file, run the following Maven command:

`mvn clean install`

This will generate a JAR file in the target directory.

**Running the Application**
Triggering the Data Collection Task
Once the application is running, you can manually trigger the data collection task by sending a GET request to the /run-task endpoint. This can be done using the following curl command:

`curl http://localhost:8080/run-task`

This will initiate the process of retrieving the top repositories and contributors from GitHub and storing the data in the H2 database.