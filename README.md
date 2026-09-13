# Strength Tracker DevOps

A Spring Boot strength-training tracker used as a hands-on playground for a full CI/CD pipeline: Maven build → Docker image → Jenkins → SonarQube analysis → Helm → ArgoCD (GitOps) on Kubernetes.

## Overview

- **Application**: `strength-tracker`, a Spring Boot 3.2 (Java 17) web app scaffolded with the `spring-boot-starter-web` and `spring-boot-starter-thymeleaf` dependencies, built with Maven.
- **Pipeline**: A `Jenkinsfile` builds the app with Maven inside a container and packages it into a Docker image.
- **Delivery**: A Helm chart (`charts/strength-tracker`) packages the Kubernetes manifests, and `argo-patch.yaml` supports bootstrapping ArgoCD for GitOps-style deployment.
- **Quality**: `sonar-project.properties` wires the project up to SonarQube/SonarCloud static analysis.
- **Local cluster**: `kind.exe` is included for spinning up a local Kubernetes cluster (kind) on Windows to test the chart end to end.

## Project Structure

```
.
├── src/main/java/com/example/strengthtracker/  # Spring Boot application source
├── charts/strength-tracker/                    # Helm chart for Kubernetes deployment
├── target/                                     # Maven build output
├── Dockerfile                                   # Container image definition
├── Jenkinsfile                                  # CI pipeline (Maven build + Docker build)
├── argo-patch.yaml                              # Patch used when bootstrapping ArgoCD
├── pom.xml                                      # Maven project descriptor
├── sonar-project.properties                     # SonarQube project configuration
├── kind.exe                                      # kind (Kubernetes-in-Docker) binary for local testing
├── Exercise.java                                 # Standalone console prototype (see note below)
└── StrengthTrackerApp.java                       # Standalone console prototype (see note below)
```

> **Note:** `Exercise.java` and `StrengthTrackerApp.java` at the repo root are an earlier console-based (Scanner-driven) prototype of the tracker and are separate from the Spring Boot app that Maven actually builds (`com.example.strengthtracker.StrengthTrackerApplication` under `src/main/java`). They're kept here for reference; the Docker/Jenkins/Helm pipeline targets the Spring Boot app.

## Prerequisites

- JDK 17
- Maven 3.9+
- Docker
- kubectl and Helm (for Kubernetes deployment)
- kind (for a local cluster) and/or access to a Kubernetes cluster
- ArgoCD (optional, for GitOps-based deployment)
- Jenkins (optional, to run the included pipeline)

## Running Locally

Build and run the Spring Boot app directly with Maven:

```bash
mvn clean package
java -jar target/strength-tracker-0.0.1-SNAPSHOT.jar
```

The app starts on `http://localhost:8080` by default.

## Running with Docker

```bash
# Build the jar first
mvn clean package -DskipTests

# Build the image
docker build -t strength-tracker:latest .

# Run the container
docker run -p 8080:8080 strength-tracker:latest
```

## CI/CD Pipeline (Jenkins)

The `Jenkinsfile` defines a two-stage pipeline:

1. **Build with Maven** – runs `mvn clean package -DskipTests` inside a `maven:3.9-eclipse-temurin-17` container against the Jenkins workspace.
2. **Build Docker Image** – builds the `strength-tracker:latest` image from the resulting jar.

To use it, point a Jenkins pipeline job at this repository (Jenkins agent needs Docker available on the host).

## Kubernetes Deployment (Helm + ArgoCD)

The Helm chart under `charts/strength-tracker` packages the Kubernetes resources for the app.

```bash
helm install strength-tracker ./charts/strength-tracker
```

For GitOps-style delivery, point an ArgoCD `Application` at this repo/chart path. `argo-patch.yaml` is used to patch ArgoCD's own configuration (e.g. the admin account) during cluster bootstrap — see the note in **Security** below before using it as-is.

To test the whole flow locally, spin up a cluster with `kind` and deploy the chart into it before wiring up ArgoCD.

## Code Quality (SonarQube)

`sonar-project.properties` configures analysis for project key `strength-tracker`. Run a scan with the SonarScanner CLI (or a Jenkins/CI step) pointed at your own SonarQube/SonarCloud instance:

```bash
sonar-scanner
```

## ⚠️ Security Note

This repository currently has **secrets committed in plaintext**:

- `sonar-project.properties` contains a live SonarQube/SonarCloud token (`sonar.login`).
- `argo-patch.yaml` contains a bcrypt password hash used to patch ArgoCD's admin credentials.

Before making this repository public (or sharing it further), you should **rotate/revoke that SonarQube token** and **regenerate the ArgoCD admin password**, then remove both values from git history and load them via environment variables, Jenkins credentials, or a Kubernetes/ArgoCD secret store instead of committing them to the repo.

## License

No license file is currently included. Add one (e.g. MIT, Apache 2.0) if you intend for others to reuse this code.
