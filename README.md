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
Para preparar o deployment da aplicacao, use o comando `sam package`

```bash
sam package --output-template-file packaged.yaml --s3-bucket bucketlambdasam
```
Compile projeto

```bash
mvn clean package shade:shade
```
### CloudFormation
Para preparar o deployment da aplicacao pelo CloudFormation, use o comando `cloudformation packagee`

```bash
aws cloudformation package --template-file template.yaml --s3-bucket bucketlambdasam --output-template template-api-aws-lambda-export.yaml
```
Deployment da aplicacao via CloudFormation

```bash
aws cloudformation deploy --template-file template-api-aws-lambda-export.yaml --stack-name api-aws-lambda-stack --capabilities CAPABILITY_NAMED_IAM
```
Criando o CloudFormation do CodeBuild

```bash
aws cloudformation deploy --template-file code-build-template.yaml --stack-name code-build-stack --capabilities CAPABILITY_NAMED_IAM
```

Criando o CloudFormation do CodePipeline

```bash
aws cloudformation deploy --template-file code-pipeline-template.yaml --stack-name code-pipeline-stack --capabilities
 CAPABILITY_NAMED_IAM
```