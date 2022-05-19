# finance-demo
- 금융 관련 서비스들을 스프링을 활용해 만들어보는 프로젝트
- 클린 아키텍처 적용

## 요구사항
- 기본 서비스: 환전, 입출금, 결제
- 회원은 자신의 거래 내역을 복식부기로 확인
- 회원은 자신의 자산, 부채, 자본을 확인 가능

## 설계
### domain
- 회원, 계좌, 분개장, 계정별 원장

## Technical Solving
### DB
#### 회원의 user_id 를 기준으로 원하는 계좌(account)의 거래 내역(journal) 찾기 
1. accounts 테이블에서 FK 인 user_id 를 기준으로 journal 을 찾기 때문에 user_id 열에 인덱스를 생성
   1. where 절에서 = 사용 시 full table scan 에서 Non-Unique Key Lookup 으로 개선 
2. journals 테이블에서 FK 인 account_id 에도 인덱스를 생성 
   1. accounts 와 join 하여 계좌 내역을 찾을 시 두 테이블 모두 Non-Unique Key Lookup 실행

### Server