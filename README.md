# spring-cloud-contract-tests-producer
Spring cloud contract tests - producer app

This app is consumed by two other apps from repos - spring-cloud-contracts-consumer-approach1 and spring-cloud-contracts-consumer-approach2

Spring Cloud Contracts Consumer App - Approach 1 - 
When there are spring cloud contracts available in the external service to be consumed (written in this producer app) 
and they have been uploaded to ("assumed") artifactory (from the producer app), 
then those stubs can be can be used in the consumer to write implementation tests.  

Spring Cloud Contracts Consumer App - Approach 2 - 
When there are NO contracts available on the external service or NO stubs uploaded in the artifactory (assuming this producer app does not have contract tests) 
then the contracts and stubs can be written in the consumer app itself in a separate module which can then be used to write implementation tests.

