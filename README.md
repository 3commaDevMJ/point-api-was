
포인트 api
==========

api 구성
-----
1. 회원별 포인트 합계 조회
2. 회원별 포인트 적립 / 사용내역 조회
3. 회원별 포인트 적립
4. 회원별 포인트 사용

API URI
----
포인트 합계조회 - ```GET /api/v1/owner/:owner/point/amount```   
포인트 내역조회 - ```GET /api/v1/owner/:owner/points```   
포인트 적립/사용 -```POST /api/v1/point```   
포인트 사용취소 - ```DELETE /api/v1/point/:eventId```   
(eventId는 적립/사용 후 return 받는 param)  


프로젝트 환경.
-------
1. java 11
2. gradle 7.4
3. spring-boot 2.5

리눅스 환경 설치 및 빌드
----
java 설치
```
yum install java-11-openjdk-devel.x86_64
```
git 설치
```
sudo yum install git
```
git 프로젝트 내려받기(clone)
```
ex ) git clone git@github.com:3commaDevMJ/point-api-was.git
```

이후 프로젝트 폴더로 이동 ( point-api-was)
```
./gradlew clean bootJar
```
명령어를 통해 /build/libs/ 폴더 안에 jar파일을 생성한다.   
이후 설치한 java -jar 위 폴더안에 *.jar를 통해 서버 실행 가능.   
(nohup 을 통해 뒷단에서 실행 가능. 보통은 스크립트를 통해 진행함.)   
``` java -jar ./build/libs/xxxx.jar```

 

다른 방법 : docker image로 빌드
------
[docker 주소] https://hub.docker.com/repository/docker/3commadevmj/point-api    
1.도커설치   
```sudo yum install docker```   
2.docker 서비스 실행   
```sudo service docker start```   
3.docker image 가져오기   
```docker pull 3commadevmj/point-api```   
4. docker images 로 이미지 확인.   
``` docker images ```   
5. docker run -it {저장소명:tag}   
``` docker run -t 3commadevmj/point-api:latest```   
