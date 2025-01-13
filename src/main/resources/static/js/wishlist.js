// 페이지 로드 시 실행되는 코드



function wishlist_admi(){
  
  // 버튼 상태
  // const wishBtn = document.getElementById('wishbtn').value;
  
  // url가져오기
  const url = window.location.href;
  const storeId = url.match(/\/read\/(\d+)$/)?.[1];
  
  const data = 
  {store : storeId
  };
  
  // ajax 기본 형식
  fetch('/wishlist/wish_work', {
    method : 'POST',
    headers : {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
  .then(response => response.json())
  .then(data => {
    
    // 성공 or 실패 로직 2가지
    // 통신 성공 -> [백엔드 처리] -> [값 반환] -> 반환 값 수령 -> 성공
    // 통신 성공 -> [백엔드 처리] -> [값 반환] -> 반환 값 수령 -> 실패
    console.log(data);
    
    
    if (data.url) {
      alert("로그인 후 추천 가능합니다.");
      window.location.href = data.url;  // 해당 URL로 페이지 이동
    } else {
      // data.state가 0이면 버튼 텍스트 변경
      const wishBtn = document.getElementById("wishbtn");
      const wishImg = document.getElementById("wishlistImage");
  
      if (data.state === 0) {
        wishImg.src = "/wishlist/images/empstar.png";  // 이미지 변경
      } else if (data.state === 1) {
        wishImg.src = "/wishlist/images/star.png";  // 이미지 변경
      }
    }

    
  })
  .catch(error => { // 통신 자체를 실패
    console.error('추가 실패 : ', error);
  });
  
  
  
}