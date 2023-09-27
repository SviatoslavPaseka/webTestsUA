# Use an official Java runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the current directory contents into the container at /app
COPY . /app

# Define environment variables
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $PATH:/usr/local/apache-maven/bin

# Make port 9090 available to the world outside this container
EXPOSE 9090