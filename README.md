# readme

# 🌀 뇌질환 환자들의 재활 치료 프로젝트 내쉬다

> 개발 기간 : 2023.08.21 ~ 2023.10.06
> 개발 인원 : 6명
> 팀명 : 은공진응

## 📑 목차

1. 프로젝트 기획 배경
2. 주요기능 설명
3. 기술 스택
4. 아키텍처
5. ERD
6. 기능 엿보기
7. 팀원 소개 및 역할

## 🚩 프로젝트 기획 배경

> 후천적으로 실어증 혹은 말을 더듬는 후유증을 가진 분들을 위한 재활 프로젝트입니다.

후천적 실어증은 뇌질환으로 인한 휴유증으로 자주 발생하는 질병입니다.

언어재활은 조기 치료가 중요하며 초기 3개월에 가장 많이 회복되며 6개월 1년의 시간이 지날수록 회복 속도가 더뎌집니다.
하지만 현재 시행되고 있는 실어증 재활치료 현황은 사진이나 단어가 적힌 종이를 활용하여 환자에게 단어나 문장의 발음을 반복적으로 연습시킵니다.
이런 방식은 재활 치료사의 지속적인 지원이 필요하며, 시간적인 제약도 있습니다.
또한 각 환자의 재활 경과를 고려하여 자료를 조정할 수 있을 만큼 자료가 다양하지 않은 문제점이 있습니다.

저희는 이러한 문제점을 해결하고 실어증 환자들이 말을 쉼쉬는듯 편한하게 내쉴수 있도록 하고자 내쉬다를 기획하였습니다.

## 🔎 주요 기능 설명

### 1️⃣ 발음 연습을 위한 연습

- 단어, 단락, 단순절을 선택하여 난이도를 설정하여 발음 연습을 진행할 수 있습니다.
- 올바른 발음과 사용자의 발음을 문자로 비교하여 한눈에 확인할 수 있습니다.

### 2️⃣ 상황에 맞는 대화 연습

- chat gpt를 사용하여 실제 대화 상황처럼 대화를 할수 있습니다.
- 상황에 맞지 않는 답변은 저장이 되며 복습 가능합니다.
- 카페, 영화관, 경찰서 3가지 상황에서 연습 가능합니다.

### 3️⃣ 상황 인지 능력 향상을 위한 드라마플레이

- 한장의 사진을 보고 빈칸이 뚫린 문장을 채워 연습하는 게임입니다.
- 쉬움/중간/어려움 3단계로 사용자가 원하는 단계로 선택하여 게임을 진행할 수 있습니다.

### 4️⃣ 순간 인지 능력 향상을 위한 스피드게임

- 단어 하나와 4장의 사진을 본 후 단어에 맞는 사진을 빠르게 선택하는 게임입니다.
- 사용자는 순간인지능력 향상시킬수 있습니다.

### 5️⃣ 스트릭과 업적달성

- 스트릭 기능을 통해 최근 연습동향, 로그인 동향을 확인할 수 있습니다.
- 단어 10개 연습, 문장 10개 연습등의 업적을 달성하면 달성 날짜의 스트릭에 이모지가 뜹니다.

### 6️⃣ 주간 시험

- 드라마 플레이, 스피드게임, 연습 모두를 통합하여 주간 테스트를 진행할 수 있습니다.
- 주간 테스트의 결과를 통해 사용자들은 경과를 시각적으로 확인할 수 있습니다.

### 7️⃣ 통계

- 사용자가 많이 틀린 발음 순으로 통계를 나타냅니다.
- 주간시험을 다시 확인할 수 있으며 정답과, 자신의 발음을 다시 청취할 수 있습니다.
- 달성한 업적과 달성날짜를 한눈에 확인할 수 있습니다.
- 진행한 모든 게임에 대한 정보는 저장되며 확인 가능합니다.

## 🛠 기술스택

<table>
<tr>
 <td align="center">언어</td>
 <td>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/Java-orange?style=for-the-badge&logo=Java&logoColor=white"/>
	
	
 </td>
</tr>
<tr>
 <td align="center">프레임워크</td>
 <td>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=ffffff"/>
	<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=ffffff"/>  
</tr>
<tr>
 <td align="center">라이브러리</td>
 <td>
  
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/jwt-6DB33F?style=for-the-badge&logo=jwt&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/gpt-6DB33F?style=for-the-badge&logo=gpt&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/smtp-6DB33F?style=for-the-badge&logo=smtp&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/fastapi-009688?style=for-the-badge&logo=fastapi&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/pytorch-EE4C2C?style=for-the-badge&logo=pytorch&logoColor=white"/>

</tr>
<tr>
 <td align="center">패키지 매니저</td>
 <td>
    <img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white">
    <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

  </td>
</tr>
<tr>
 <td align="center">인프라</td>
 <td>
  <img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=ffffff"/>
    <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=ffffff"/>
        <img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=ffffff"/>
  
</tr>
<tr>
 <td align="center">포맷팅</td>
 <td>
  <img src="https://img.shields.io/badge/ESLint-4B32C3?style=for-the-badge&logo=ESLint&logoColor=ffffff"/> 
  <img src="https://img.shields.io/badge/Prettier-F7B93E?style=for-the-badge&logo=Prettier&logoColor=ffffff"/> 
  <img src="https://img.shields.io/badge/PostCSS-DD3A0A?style=for-the-badge&logo=PostCSS&logoColor=ffffff"/> 
  </td>
</tr>

<tr>
 <td align="center">협업툴</td>
 <td>
    <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/>
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Gitlab-FC6D26?style=for-the-badge&logo=Gitlab&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=Mattermost&logoColor=white"/> 
    <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white"/>
 </td>
</tr>
<tr>
 <td align="center">기타</td>
 <td>
    <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white"/>
    <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/> 
    <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"/>
    <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"/>
 </td>
</tr>
</table>

## 🧱 아키텍쳐
![그림1.png](./img/아키텍처.png)

## 📌 ERD
![erd.png](./img/erd.png)

## 👀 기능엿보기
### 메인화면
![인트로.gif](./img/인트로.gif)

### 🟣 로그인
![로그인.gif](./img/로그인.gif)

### 🟣 비밀번호 찾기
<img src="./img/비밀번호 찾기.gif" alt="10MB 이상의 파일입니다. img 폴더를 확인해 주세요:)">

### 🟣 회원가입
![회원가입.gif](./img/회원가입.gif)

### 🟣 회원정보 수정
![회원정보 수정](https://github.com/D105-SSAFY/NASHDA/assets/129479086/5474324c-c4f8-42c2-bbed-0264717f8220)

### 🟣 스피드게임
![스피드 게임](https://github.com/D105-SSAFY/NASHDA/assets/129479086/bee573e9-0e75-40c0-ae05-f2a1f9eb7c24)

### 🟣 드라마플레이
![드라마 플레이](https://github.com/D105-SSAFY/NASHDA/assets/129479086/4878cee2-de6d-41af-bd65-664e3331395e)

### 🟣 주간 테스트
![주간 테스트](https://github.com/D105-SSAFY/NASHDA/assets/129479086/ef45e2b0-4383-4644-bdcb-a02783f10cca)

### 🟣 발음 연습
![발음.gif](./img/발음.gif)

### 🟣 대화 시뮬레이션(카페)
![시뮬_카페.gif](./img/시뮬_카페.gif)

### 🟣 대화 시뮬레이션(경찰서)
![시뮬_경찰서.gif](./img/시뮬_경찰서.gif)

### 🟣 대화 시뮬레이션(영화관)
![시뮬_영화관.gif](./img/시뮬_영화관.gif)

### 🟣 스트릭
![스트릭 테마](https://github.com/D105-SSAFY/NASHDA/assets/129479086/c8a7c0cb-8bfb-4a7c-864a-8fce93e61225)

### 🟣 관리자에게 문의
![파일 다운로드_다변 확인_질문 등록](https://github.com/D105-SSAFY/NASHDA/assets/129479086/b161674a-7618-4c67-a953-9a1815df9a30)

### 🟣 업적
![업적.gif](./img/업적.gif)

### 🟣 게임 통계
![게임 통계](https://github.com/D105-SSAFY/NASHDA/assets/129479086/6e7c2ba9-d138-4e57-bae0-ec937d08cffa)

### 🟣 시뮬레이션 통계
![시뮬레이션_통계.gif](./img/시뮬레이션_통계.gif)

### 🟣 주간 테스트 통계
![주간 테스트_통계](https://github.com/D105-SSAFY/NASHDA/assets/129479086/eec5a23a-8862-49c6-b781-81b2aef1fae1)

### 🟣 사용자 발음 통계
![초성_중성_종성 통계](https://github.com/D105-SSAFY/NASHDA/assets/129479086/39c517b5-355d-47dc-8654-cbda292f4ab0)

## 🧚‍♀️팀원 소개 및 역할
| 이름   | 기능 및 역할 |
| ----|--------------------------------------------------------------------------- |
| 서은진 |  팀장, BackEnd </br> SMTP를 활용한 회원가입/비밀번호 초기화, Spring Security, JWT를 활용한 로그인, 회원 스트릭 생성, 주간 TEST 시작 및 결과 MongoDB저장 및 조회, 데이터베이스 설계, 업적달성 및 조회, 회원정보 조회/수정, 테스트 통계 제공, 게임 통계 제공, 기획 발표|
| 우주연 |  팀원, FrontEnd </br> 로그인, 회원 가입, 비밀번호 찾기 |
| 안세혁 | 디자인, 프론트엔드 { 기반 로직(보안 및 API, 공용 메소드 등), 사용자 기능,분석 및 통계 기능, css작성, QnA, 테마 }, 전반적인 UX/UI 디자인, 프로젝트 구상, 기획, 최종 발표|
| 서동현 | 팀원 (프론트엔드) </br> - Frontend : Redux persist storage 구현 및 session storage 연동, 메인 화면 디자인 구현, 발음 연습/대화 시뮬레이션/드라마 플레이/스피드 퀴즈/테스트 페이지 로직 처리 및 비동기 연결, 음성 처리 구현 </br> - Backend : STT 서버 구현 </br> - AI : 모델 비교 및 선정, 데이터 전처리 및 fine tuning  |
| 조경호 |  팀원, Backend</br> Infra (Docker, AWS, Jenkins), 발음 연습 로직 구현, 게임 및 연습 문제 불러오기 기능, 백엔드 서버 간 통신 기능, S3 서버 연동  |
| 이연지 |   팀원, BackEnd </br> 공지사항(파일 업로드 및 다운로드), 질문 게시판(파일 업로드 및 다운로드), 대화 시뮬레이션(OpenAI 연동, 시나리오 작성, 대화 유지-mongoDB 연동), 대화 시뮬레이션 통계   


