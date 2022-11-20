# RUNNING
# docker run -v /var/run/dokcer.sock:/var/run/dokcer.sock -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 5000:5000 -d --restart always
FROM jenkins/jenkins:latest
USER root
RUN apt-get update && apt-get install -y apt-transport-https
RUN apt-get install ca-certificates curl gnupg lsb-release -y
RUN mkdir -p /etc/apt/keyrings
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg
RUN echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu focal stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null
RUN apt-get update
RUN apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin -y
RUN apt-cache madison docker-ce
RUN apt-get install docker-ce=5:20.10.17~3-0~ubuntu-focal docker-ce-cli=5:20.10.17~3-0~ubuntu-focal containerd.io docker-compose-plugin -y
RUN ssh-keygen -t rsa -P "" -f /root/.ssh/id_rsa
RUN apt-get install openjdk-11-jdk -y
RUN apt-get install maven -y