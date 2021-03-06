# 상품 주문 프로그램

- Java
- Gradle
- AssertJ, Mockito
- 로컬 환경 CMD에서 실행

## 요구사항

---
### 세부사항
- Product (상품)
  - 상품번호, 상품명, 판매가격, 재고수량
- Order (주문)
  - 상품번호, 구매수량
  - 유효한 상품번호
  - 유효한 구매수량
- OrderProduct (결제 시 사용하는 상품주문 클래스)
  - 주문내역(상품명, 구매수량), 주문금액, + 배송비, = 지불금액
- ProductData (상품데이터 singleton 클래스)
- CUIHandler (CUI 입출력 클래스)

### 추가내용 등
- 동일한 상품번호의 상품에 대한 주문은 중복으로 + - 조작하여 개수를 조절할 수 있습니다.
- 최종 주문수량은 음수일 수 없습니다.
- 상품번호는 음수일 수 없습니다.

## 특이사항
779049,"[리퍼브/키친마켓] Fabrik Pottery Cup, Saucer (단품)",10000,64  
> 위 데이터 csv 포맷에 맞지 않는 것 (제품명) 수정
---

#### 비지니스 모델
- 상품은 고유의 상품번호, 상품명, 판매가격, 재고수량 정보를 가진다.
- 상품번호, 주문수량 으로 상품을 주문할 수 있다.
- 주문은 상품번호, 주문수량 정보를 갖는다.
    - 결제 시 재고 확인을 한다.
        - 재고가 부족한데 결제를 시도하면 SoldOutException 이 발생한다.
- 주문 금액이 5만원 미만이면 배송료가 2,500원 추가된다.
#### UI
- 상품번호, 주문수량 을 반복적으로 입력해서 한 번에 여러개 상품을 같이 주문할 수 있다.
    - 한번 결제 완료되면, 다음 주문에선 이전 결제와 무관하게 주문 가능
- empty(space + ENTER) 입력으로 주문을 완료하고 결제한다.
- 주문이 완료되면 주문내역, 주문금액, 결제금액(배송비포함) 을 화면에 보여준다.
- 'q' 또는 'quit' 을 입력하면 프로그램이 종료.
#### 필수 테스트
- Test 에서는 반드시 multi thread(?) 요청으로 SoldOutException 이 정상 동작하는지 확인하는 단위테스트 작성
#### 데이터
- 상품의 데이터는 깃에 함께 올라간 csv 데이터 사용
    - 데이터를 불러오는 방식은 자유. (in-memory db, file, hard-coding 등)
    - 상품에 대한 상품번호, 상품명, 판매가격, 재고수량 데이터는 그대로 사용.

----
### 추가 고려사항
#### 요구사항에 일치하는 기능
- 요구사항에 맞게 동작
#### 유지보수와 확장 용이성
- OOP 에 대한 이해를 바탕으로 SOLID 원칙 적용
#### 제품의 아키텍쳐 설계
- 책임 분리
- 코드 간의 적절한 의존성 관리
- 적절한 수준의 추상화
#### 가독성
- 의미있는 이름 짓기
- 적절한 책임 단위로 쪼개어진 코드 파일
- 일관된 코드 형식
#### 효율성
- 시간 복잡도에 대한 이해
- 프로젝트에 적절한 외부 라이브러리 선택

---
