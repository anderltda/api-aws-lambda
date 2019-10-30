Command:
=======================

## S3
Criando o bucket

```bash
aws s3 mb s3://bucketlambdasam --region us-east-1
```
Deletando o bucket

```bash
aws s3 rb s3://bucketlambdasam --force
```

### SAM
Criando o projeto

```bash
sam init --name project-name --runtime java8 --dependency-manager maven
```
Para preparar o deployment da aplicação, use o comando `sam package`

```bash
sam package --output-template-file packaged.yaml --s3-bucket bucketlambdasam
```
Compile projeto

```bash
mvn clean package shade:shade
```
Para preparar o deployment da aplicação pelo cloudformation, use o comando `cloudformation packagee`

```bash
aws cloudformation package --template-file template.yaml --s3-bucket bucketlambdasam --output-template template-api-test-lambda-export.yaml
```
Deployment da aplicação via cloudformation

```bash
aws cloudformation deploy --template-file template-api-test-lambda-export.yaml --stack-name nome-stack --capabilities CAPABILITY_IAM
```