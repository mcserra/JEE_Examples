# Build
mvn clean package && docker build -t com.jee.examples/jeeexamples .

# RUN

docker rm -f jeeexamples || true && docker run -d -p 8080:8080 -p 4848:4848 --name jeeexamples com.jee.examples/jeeexamples 