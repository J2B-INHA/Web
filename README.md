# NFT-generator

<p align="center">
    <img src="https://user-images.githubusercontent.com/66549638/201521745-bce282a8-20a3-4c9d-9efd-93fd3ec52ac9.png">
</p>

인하대학교 J2B팀의 이미지 인식 기반 NFT generator 서비스입니다.

# 🔗 Link
https://j2b-inha.shop

# 📽️ 주요 화면
![홈페이지](https://user-images.githubusercontent.com/66549638/203804333-521e1c99-6bba-48fc-9588-0d0dd3048b9d.gif)
![로그인](https://user-images.githubusercontent.com/66549638/203804341-e86d8e46-bed8-49d6-a957-eddb9c3ce70e.gif)
![NFT 생성](https://user-images.githubusercontent.com/66549638/203804338-493d25fa-bddf-4758-a59c-5cdf8ee0d31d.gif)
![이미지 변환](https://user-images.githubusercontent.com/66549638/203804328-b531984c-31b6-4d13-8e73-b1848a4a6d2e.gif)
![상품 구매](https://user-images.githubusercontent.com/66549638/203804320-ce360b13-09b4-456e-9de4-115034fd7456.gif)
![NFT 민팅 장면](https://user-images.githubusercontent.com/66549638/204516801-bb417bb9-c853-4460-97d6-5379ebd02e0e.gif)



# 📜 주요 기능
1. 로그인 / 회원가입

2. 이미지 NFT 상품 생성
    
    - NFT 정보와 이미지를 업로드하면, 이미지를 원하는 필터를 이용해 변환하여 효과를 입힌 이미지로 변환됩니다.
    - NFT 정보와 이미지 정보를 가진 NFT 메타데이터 (JSON)을 생성합니다.

3. NFT 상품 조회

4. NFT 상품 구매

    - NFT 상품을 구매하면, 구매한 NFT 상품의 메타데이터를 다운로드 받을 수 있습니다.

5. NFT 민팅

    - NFT 상품을 구매하면, 해당 NFT 상품의 메타데이터를 포함한 NFT를 민팅 (발행)할 수 있습니다.
    - 📌 (참고사항) 사용자로부터 메타마스크 계정을 받아오는 방식이 아닌, 테스트용 메타마스크 계정으로 민팅하는 방식으로 이루어져 있습니다.



# 📌 실행 시 주의사항
AWS t2.small 인스턴스 <span style="color: yellow">(RAM 용량 2GB)</span>를 사용하였습니다.

따라서 NFT 상품을 생성할 때 (Python opencv 코드 실행) 용량이 너무 큰 이미지를 업로드하면 에러가 발생할 수 있습니다!

이는 인스턴스의 RAM 용량 이슈로, 이미지 업로드 시 에러가 발생할 경우 용량이 작은 사진을 업로드 해주세요!

# 🛠️ 기술 스택
![Tech Stack](https://user-images.githubusercontent.com/66549638/204513684-30c4fd4c-f933-440a-94c9-248addb37144.png)

# ⛓️ 프로젝트 전체 구성도
![프로젝트 흐름도](https://user-images.githubusercontent.com/66549638/204513680-251b05ae-7dfc-4afe-8be9-20374b3a6345.png)

# 🗺️ ERD
![J2B_NFT_INHA (4)](https://user-images.githubusercontent.com/66549638/203796825-4bf3c75a-c891-4318-bb9f-2261ef5f9e51.png)


# 🧑‍💻 프로젝트를 진행하면서 겪은 과정들

1. nginx Request Entity Too Large 이슈

    https://chominho96.github.io/aws/Nginx-413-Request-Entity-Too-Large-%EC%9D%B4%EC%8A%88/

2. Spring에서 Bash 명령어 실행

    https://chominho96.github.io/spring/Spring%EC%97%90%EC%84%9C-Bash-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%8B%A4%ED%96%89%ED%95%98%EA%B8%B0/

3. Linux에서 opencv 설치

    https://chominho96.github.io/aws/Linux%EC%97%90%EC%84%9C-opencv-%EC%84%A4%EC%B9%98/

4. Amazon Linux 내부 파일 접근 시 FileNotFoundException 이슈

    https://chominho96.github.io/aws/EC2-%EB%82%B4%EB%B6%80-%ED%8C%8C%EC%9D%BC-%EC%A0%91%EA%B7%BC-%EC%8B%9C-FileNotFoundException-%EC%9D%B4%EC%8A%88/

5. multipartFile.transferTo() 메서드 호출 후 InputStream consume 이슈

    https://chominho96.github.io/spring/multipartFile.transferTo()-%EB%A9%94%EC%84%9C%EB%93%9C-%ED%98%B8%EC%B6%9C-%ED%9B%84-InputStream-consume-%EC%9D%B4%EC%8A%88/

6. ProcessBuilder 관련 Deadlock 이슈

    https://chominho96.github.io/spring/ProcessBuilder-Deadlock-%EC%9D%B4%EC%8A%88/