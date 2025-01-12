// 페이지 로드 시 실행되는 코드



function wishlist_admi(){
  
  const wishBtn = document.getElementById('wishbtn').value;
  const data = {sish_state : wishBtn};
  
  
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
    }


    
  })
  .catch(error => { // 통신 자체를 실패
    console.error('추가 실패 : ', error);
  });
  
  
  
}