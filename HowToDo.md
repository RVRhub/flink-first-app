This is command that could be used to get list of IP addresses of your docker Colima network
```bash
colima list
```

### Create a JAR file
```bash
 ./gradlew shadowJar         
````

How to open port 9999 in netcat to send data to socket in Flink
```bash
apt-get update && apt-get install -y netcat
nc -lk 9999
```

Generate data.csv using datafaker-gen. Go to the _datafaker-gen_ folder and run:
```bash
path/my/datafaker_gen -f csv -n 2 -sink textfile
```

### How to run KafkaStream 
Use this fork: https://github.com/RVRhub/kafka-stack-docker-compose


### Run Flink job from savepoint

```bash
/opt/flink/bin/flink savepoint f43a5004f9f053c5d1e30312a14ac351 /opt/flink/savepoints
/opt/flink/bin/flink cancel -s /opt/flink/savepoints e7954328ec61c4385ea66da3db2cac96

/opt/flink/bin/flink run -s file:/opt/flink/savepoints/savepoint-72762c-0d8190a23ba0 -n -c dev.rvr.StreamingWithSnapshot /opt/flink/usrlib/artifact/flink-app-0.0.1.jar 
```

### What to do next

- [x] Configure cron job to run Flink job
- [ ] How to work with savepoints in Flink
- [x] To understand batch and stream processing in Flink
- [x] Try to write a simple Flink program based on batch processing
- [x] To understand how to use Flink with Kafka
- [x] To understand how to use Flink with ElasticSearch
- [ ] Write description of the project

### Questions

 1. What is the difference between batch and stream processing in Flink?


### How to create JAR for certain job

1. Change Main-Class in build.gradle.kts

```kotlin
    manifest {
        attributes["Main-Class"] = "dev.rvr.StreamingWithSnapshot"
    }
```
    
2. Uncomment the lines with needed job in _./batch/script.sh_
   
3. Run the command to create JAR file

```bash
./gradlew clean shadowJar
```