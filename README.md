
## 개요
> 콘서트 좌석 예매 프로그램

- 콘서트 선택 -> 날짜 / 시간 선택 -> 좌석 선택 -> 결제 
- PieChart, LineChart 를 이용한 시각화
- 회원 게시판

인터파크 티켓 예매 사이트를 참고했습니다. 고객이 로그인 후, 콘서트를 고르고 원하는 날짜, 시간을 선택한 뒤 결제하는 홈페이지입니다.

- 사이트 이용자 : 손님
- 사이트 관리자 : 매니저


## java fx 와 Scene Builder 를 이용한 콘서트 예매 페이지 

![image](https://user-images.githubusercontent.com/30681841/141705694-886c9143-a800-4a68-93ce-c2a1f87f31a4.png)
![image](https://user-images.githubusercontent.com/30681841/142988546-a3bd0452-ff36-423a-975c-f071026cdf75.png)
![image](https://user-images.githubusercontent.com/30681841/141705299-a66fb3fc-658d-4a37-ad13-1aa8625f722d.png)

## 초기 화면 설계

![image](https://user-images.githubusercontent.com/30681841/141442630-8fa1cea8-3ab4-4faa-8fc8-6169c827c129.png)

## 프로그램 작동 예시 

![](https://images.velog.io/images/boricat/post/0865d5fe-4d2b-4ac2-a756-5ed17cb4bdd8/image.png)

![](https://images.velog.io/images/boricat/post/e4050e13-5bbe-4ef9-84a3-8d3309ab0b92/image.png)

![](https://images.velog.io/images/boricat/post/fb54559f-65a7-45a7-a704-0f259ff1831e/image.png)

## 개발 시간

> ~11/22

날짜 | 계획
----- | -----
11.8 ~ 11.9 | 초기 DB 논의 및 큰 그림, 역할 분담, git branch 전략 공부
11.10~11.12 | DB 완성 및 FXML 기초 작업, Controller 설계 
11.13-11.14(주말) | 회원, 예약, 매니저 각자 나눠진 역할에 할당된 fxml 완성 (하는데까지)
11.15 | Controller, Dao 클래스 만들기-1 (DB 연동 테스트 및 메소드 설계)
11.16 | Controller, Dao 클래스 만들기-2 (DB 연동 테스트 및 메소드 설계)
11.17 | Controller, Dao 클래스 만들기-3 (DB 연동 테스트 및 메소드 설계)
11.18 | 팀원 각각 만든 부분 합치는 작업(오류수정 및 개선)-1
11.19 | 팀원 각각 만든 부분 합치는 작업(오류수정 및 개선)-2
11.20(토요일) | 팀원 각각 만든 부분 합치는 작업(오류수정 및 개선)-3
11.21(일요일) | 최종 디버깅
11.22 | 발표

## 개발 환경

> Eclipse EE, Mysql, Mysql WorkBench, Scene Builder, Onenote, JDK 11

## 역할 분담

> 동진 : 홈페이지 가입, 메인 페이지

> 지형 : 매니저 페이지, 메인 페이지

> 정진 : 메인 페이지 


## 시연 영상

[![Video Label](https://user-images.githubusercontent.com/30681841/142995565-834954e7-a0c8-4d20-a8c8-e7ef4cd98129.png)](https://youtu.be/8OECmZlYDRU)




## 프로젝트 소감 

> 1. 어려움을 겪었던 부분     

[1] 초기 설계의 부실함 --> 엉성한 DB 설계 --> 프로젝트 진행 도중에도 잦은 수정 --> 컨트롤러간 연결이 부실해짐 --> 오타와 오류 수정에 많은 시간 소모 --> 실제 페이지와 비교해서 기능 구현을 많이 하는데 시간이 부족했음 

> 2. 느낀 점 및 배운 점

1. 무작정 코드를 작성하기에 앞서 큰 틀을 세우고 각 클래스 사이의 연관관계를 명확하게 명시해야한다. 
2. DB 작성에 어려움을 겪었을 때, 예상되는 시나리오를 하나 정해서 각각의 요소에 어느 범주에 들어가야하는지 파악했다. 
3. github 가 어떤 식으로 돌아가는지 공부할 수 있었다. main - dev - feature 이렇게 3개 큰 브랜치로 나눠서 하며 각자 작업한 뒤 push & pull 을 통해서 브랜치를 합치는 과정에서 오류가 발생하지 않도록 github 가 제공하는 기능을 최대한 활용했다.



## 추가(개선)할 부분

1. 콘서트 갯수를 늘려서 실제 콘서트 예약 페이지와 유사한 정도를 높인다. 
2. 멀티스레드를 사용해서 동시에 여러가지 입력이 가능하도록 개선한다. 
3. css 를 활용해서 좀 더 깔끔하고 직관적으로 꾸민다. 




## 실제 예약 사이트와 비교 

![](https://images.velog.io/images/boricat/post/2fe30273-231d-4725-924d-9f63f2079ce4/image.png)

![](https://images.velog.io/images/boricat/post/b3923a81-4d45-47e4-b89b-0f0e08d73865/image.png)

![](https://images.velog.io/images/boricat/post/24498848-6d70-4c37-a26e-117abc467800/image.png)

![](https://images.velog.io/images/boricat/post/6764cda2-28eb-443e-bca7-ce1ad0a3dd70/image.png)

![](https://images.velog.io/images/boricat/post/d8db0f93-d151-40f6-931f-5282f6097e2b/image.png)

![](https://images.velog.io/images/boricat/post/2e9757b8-c83a-453c-85b4-755b47e57c2c/image.png)

![](https://images.velog.io/images/boricat/post/a9ce26aa-b98b-4b09-a994-a48607a259d4/image.png)

![](https://images.velog.io/images/boricat/post/a0f48511-3c96-4839-95a9-0245fce1ec55/image.png)

![](https://images.velog.io/images/boricat/post/4624c26a-8b1f-49e7-abf7-46e9439516e9/image.png)

![](https://images.velog.io/images/boricat/post/02f89ee4-08d1-4a73-aff8-0a42bbe1d201/image.png)

![](https://images.velog.io/images/boricat/post/b1ab639f-9e69-42b2-88ee-776bd804d605/tmp1.png)

![](https://images.velog.io/images/boricat/post/993350f3-b18b-4aef-b66c-129b8d1931b1/image.png)

![](https://images.velog.io/images/boricat/post/8924a6ee-72b7-4d12-9860-07b27ae5e884/image.png)




