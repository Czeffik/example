#Requirements:
- java
- gradle (Possibly)

#Run tests:

If you don't have installed gradle, use `gradlew` instead of `gradle` 
- for run all tests with default baseUrl="https://api.kontakt.io/" and apiKey="KQvFHVLZxhYzMJfweBhQoZHDaAgBmVnP":
    - gradle test
- for run all tests with other params:
    - gradle test -DbaseUrl="other.url"
    - gradle test -DapiKey="otherapikey"
    - gradle test -DapiKey="otherapikey" -DbaseUrl="other.url"
- for run all tests which are SmokeTest use:
    - gradle smoke
    - gradle smoke -DbaseUrl="other.url"
    - gradle smoke -DapiKey="otherapikey"
    - gradle smoke -DapiKey="otherapikey" -DbaseUrl="other.url"
- for run all tests which are marked as Regression use:
    - gradle regression
    - gradle regression -DbaseUrl="other.url"
    - gradle regression -DapiKey="otherapikey"
    - gradle regression -DapiKey="otherapikey" -DbaseUrl="other.url"