# Getting Started

### Prerequisites
- Docker

### Built With

* [Java Spring](https://spring.io/)
* [ReactJS](https://reactjs.org/)
* [PostgreSql](https://www.postgresql.org/)
* [Apache Kafka](https://docs.spring.io/spring-boot/docs/2.7.11/reference/htmlsingle/#messaging.kafka)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/#streams-kafka-streams)

### Start all applications
- Run the `./start.sh` 
- Producer application reads sale files from folder `data/sale-files`
- Visit http://localhost:3000 to verify.
### Folder Structure
```bash
├── README.md
├── consumer
│   ├── README.md
│   ├── build
│   ├── build.gradle
│   ├── dockerfile
│   ├── gradle
│   ├── gradlew
│   ├── gradlew.bat
│   ├── settings.gradle
│   └── src
├── data
│   ├── archived-sale-files
│   └── sale-files
├── docker-compose.yml
├── frontend
│   ├── README.md
│   ├── dockerfile
│   ├── node_modules
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   ├── src
│   └── tsconfig.json
├── gradle
│   └── wrapper
├── gradlew
├── gradlew.bat
├── producer
│   ├── README.md
│   ├── build
│   ├── build.gradle
│   ├── dockerfile
│   ├── gradle
│   ├── gradlew
│   ├── gradlew.bat
│   ├── settings.gradle
│   └── src
├── settings.gradle
└── start.sh