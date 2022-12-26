# playstore-project
게임 유통 플랫폼 Play Store 

## 기술 스택
> <img src="https://img.shields.io/badge/Kotlin-7952B3?style=for-the-badge&logo=kotlin&logoColor=white">  
> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">  
> <img src="https://img.shields.io/badge/Thymeleaf-339933?style=for-the-badge&logo=thymeleaf&logoColor=white">    
> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

## 개발 멤버
+ **BACKEND**
  + [KwanJung98 (82KJ)](https://github.com/82KJ/) - Scrum Master
  + [KIM NA WON (naw0n)](https://github.com/naw0n) - Team Leader
  + [Kibeom Park (Manhye)](https://github.com/Manhye)
+ **FRONTEND**
  + [Jan Woo Jin (JangWooJin1)](https://github.com/JangWooJin1)
  + [Kang Hyun Woo (rkdgusdn)](https://github.com/rkdgusdn)

## 주요 구현 사항
+ **Init Page** - 사용자 등록, 권한 확인
  + Login Page
  + SignUp Page<br><br>
+ **UserMain Page** - 전체 게임 리스트 확인
  + UserMy Page - 구매 게임 확인, 게임 환불, 게임머니 충전, 게임머니 현금화, 패스워드 수정
  + GameInfo Page - 게임 상세정보 확인 (가격, 제한 연령, 사양)
  + Basket Page - 장바구니 정보 확인
  + Payment Page - 게임 구매 진행<br><br>
+ **AdminMain Page** - 게임 등록, 수정, 삭제
  
## 실행 화면
### 1. Login Page
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209555650-98d9578c-4123-4278-8819-7759b107e9b3.png" width="80%" height="80%"/></p>

**L1** : 아이디 입력&nbsp;&nbsp;&nbsp;**L2** : 패드워드 입력&nbsp;&nbsp;&nbsp;**L3** : 회원가입 페이지 이동&nbsp;&nbsp;&nbsp;**L4** : 로그인 버튼  

### 2. SignUp Page
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209556047-6b804fd8-40dc-41dc-90f9-ca130abb89f1.png" width="75%" height="75%"/></p>

**L1** : 아이디 입력&nbsp;&nbsp;&nbsp;**L2** : 생년월일 입력&nbsp;&nbsp;&nbsp;**L3** : 패드워드 입력&nbsp;&nbsp;&nbsp;**L4** : 패드워드 재입력&nbsp;&nbsp;&nbsp;**L5** : 회원가입 버튼&nbsp;&nbsp;&nbsp;**L6** : 로그인 페이지 이동 링크

### 3. UserMain Page
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209556286-c4b92725-4adb-4680-b84a-ef5c3489689d.png" width="75%" height="75%"/></p>

**L1** : 게임 검색&nbsp;&nbsp;&nbsp;**L2** : 마이페이지 버튼&nbsp;&nbsp;&nbsp;**L3** : 게임 머니&nbsp;&nbsp;&nbsp;**L4** : 장바구니 버튼&nbsp;&nbsp;&nbsp;**L5** : 로그아웃 버튼&nbsp;&nbsp;&nbsp;**L6** : 게임 상세 정보 

### 4. UserMy Page
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209556820-a8240a44-e099-4735-ba43-f3f4b39fd370.png" width="75%" height="75%"/></p>

**L1** : 메인페이지 이동&nbsp;&nbsp;&nbsp;**L2** : 영수증 확인&nbsp;&nbsp;&nbsp;**L3** : 패스워드 수정&nbsp;&nbsp;&nbsp;**L4** : 게임 머니 충전&nbsp;&nbsp;&nbsp;**L5** : 게임 머니 현금화&nbsp;&nbsp;&nbsp;**L6** : 게임 환불&nbsp;&nbsp;&nbsp;**L7** : 게임 플레이

### 5. GameInfo Page
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209556999-c3e2a3d2-2051-4ea1-bf3b-3f19f1f0cc15.png" width="75%" height="75%"/></p>

**L1** : 메인 이미지&nbsp;&nbsp;&nbsp;**L2** : 게임 이름&nbsp;&nbsp;&nbsp;**L3** : 서브 이미지&nbsp;&nbsp;&nbsp;**L4** : 게임 가격&nbsp;&nbsp;&nbsp;**L5** : 연령 제한&nbsp;&nbsp;&nbsp;**L6** : 장바구니 추가&nbsp;&nbsp;&nbsp;**L7** : 상세 정보&nbsp;&nbsp;&nbsp;**L8** : 메인 페이지 이동

### 6. AdminMain Page
#### - 게임 추가
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209557532-ee3c7aba-be64-457c-9b87-81ef185a6801.png" width="75%" height="75%"/></p>

**L1** : 메인 이미지 추가&nbsp;&nbsp;&nbsp;
**L2** : 게임 이름 입력&nbsp;&nbsp;&nbsp;
**L3** : 서브 이미지 추가&nbsp;&nbsp;&nbsp;
**L4** : 게임 가격 입력&nbsp;&nbsp;&nbsp;
**L5** : 게임 연령 입력&nbsp;&nbsp;&nbsp;
**L6** : 게임 상세 정보 입력&nbsp;&nbsp;&nbsp;  
**L7** : 관리자 메인페이지로 이동&nbsp;&nbsp;&nbsp;
**L8** : 게임 저장&nbsp;&nbsp;&nbsp;

#### - 게임 수정, 삭제
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209557722-90178176-fd20-4375-82e0-d3c27134ec90.png" width="75%" height="75%"/></p>

**L1** : 메인 이미지 수정&nbsp;&nbsp;&nbsp;
**L2** : 게임 이름 수정&nbsp;&nbsp;&nbsp;
**L3** : 서브 이미지 수정&nbsp;&nbsp;&nbsp;
**L4** : 게임 가격 수정&nbsp;&nbsp;&nbsp;
**L5** : 게임 연령 수정&nbsp;&nbsp;&nbsp;
**L6** : 게임 상세 정보 수정&nbsp;&nbsp;&nbsp;  
**L7** : 관리자 메인페이지로 이동&nbsp;&nbsp;&nbsp;
**L8** : 게임 삭제&nbsp;&nbsp;&nbsp;
**L9** : 수정 사항 저장&nbsp;&nbsp;&nbsp;

### 7. etc...
#### - 게임 구매  
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209557875-e62cf622-cadd-48aa-92af-6b80eddb22f1.png" width="40%" height="40%"/>&nbsp;<img src="https://user-images.githubusercontent.com/45115733/209557883-76bd98ac-3f60-441e-ab8f-dfc5fd8598e6.png" width="40%" height="40%"/></p>

#### - 게임 머니 충전, 장바구니
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209557890-0b98931c-6dda-42c4-9e42-3b15d46a108d.png" width="40%" height="40%"/>&nbsp;<img src="https://user-images.githubusercontent.com/45115733/209557226-f7e39ebc-d6ab-4663-b4ea-b61745a8f656.png" width="40%" height="40%"/></p>

#### - 게임 머니 현금화, 패스워드 수정
<p align="center"><img src="https://user-images.githubusercontent.com/45115733/209557899-8e8e5d29-7eeb-4d45-9766-3f8ae8231d62.png" width="40%" height="40%"/>&nbsp;<img src="https://user-images.githubusercontent.com/45115733/209558770-e0069e6a-308a-4739-85d7-598c4dd0c58c.png" width="40%" height="40%"/></p>
