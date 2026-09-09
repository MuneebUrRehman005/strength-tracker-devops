# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Java source code files into the container
COPY Exercise.java StrengthTrackerApp.java ./

# Compile the Java application
RUN javac Exercise.java StrengthTrackerApp.java

# Run the application when the container starts
CMD ["java", "StrengthTrackerApp"]