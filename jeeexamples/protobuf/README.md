

### Generate .java from .proto

####1. brew install protobuf
####2. From the root
    protoc --java_out=src/main/java/ src/main/java/com/miguel/protobuf/Ping.proto
