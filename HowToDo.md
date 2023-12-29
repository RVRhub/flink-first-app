```bash


```

```bash
./gradlew shadowJar
 flink run -c dev.rvr.WordCount /tmp/flink-web-a4268ba9-cad0-4c9a-b067-368f32941738/flink-web-upload/8113a8f0-97cf-478d-a817-77663345ae10_flink-app-0.0.1.jar

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

### What to do next

- [x] Configure cron job to run Flink job
- [ ] How to work with savepoints in Flink
- [ ] To understand batch and stream processing in Flink
- [ ] Try to write a simple Flink program based on batch processing
- [ ] To understand how to use Flink with Kafka
- [ ] To understand how to use Flink with ElasticSearch
- [ ] Fix a bug

### Questions

 1. What is the difference between batch and stream processing in Flink?
2. 