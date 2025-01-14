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







function rating_btn(){
  
  // 버튼 상태
  const ratingBtn = document.getElementById('mrating').value;
  
  if (ratingBtn > 10.0) {
    var ratingMsgDiv = document.getElementById('rating_msg');
    ratingMsgDiv.innerHTML = '<span style="color: red; font-weight: bold;">평점은 10을 넘길 수 없습니다.</span>';
    return false;
  }
  
  // url가져오기
  const url = window.location.href;
  const storeId = url.match(/\/read\/(\d+)$/)?.[1];
  
  const data = 
  {
    rating : ratingBtn,
    storeno : storeId
  };
  console.log(data);
  // ajax 기본 형식
  fetch('/rating/rating_work', {
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
    var ratingMsgDiv = document.getElementById('rating_msg');
    var storeTD = document.getElementById('store_rating');
    ratingMsgDiv.innerHTML = ''
    
    
    if(data.state === 1){
      // ratingMsgDiv에 메시지와 함께 3초로 시작하는 타이머를 표시
      let timeRemaining = 3;  // 시작 시간을 3초로 설정
      ratingMsgDiv.innerHTML = '<span style="color: green; font-weight: bold;">' + data.msg + ' (시간: ' + timeRemaining + '초)</span>';
      
      // storeTD에 item 내용 표시
      storeTD.innerHTML = data.item;
    
      // 1초마다 초를 줄여가며 표시
      var countdown = setInterval(function() {
        timeRemaining--;  // 남은 시간 1초씩 감소
        ratingMsgDiv.innerHTML = '<span style="color: green; font-weight: bold;">' + data.msg + ' (시간: ' + timeRemaining + '초)</span>';
        
        if (timeRemaining <= 0) {
          clearInterval(countdown);  // 시간이 0초가 되면 타이머 멈추기
          ratingMsgDiv.innerHTML = '';  // 0초가 되면 메시지 비우기
          ratingMsgDiv.innerHTML = '<span>※0점은 평점 삭제</span>';
          }
        }, 1000);
      
      
    } else {
      ratingMsgDiv.innerHTML = '<span style="color: red; font-weight: bold;">오류로 인해 평점을 남길 수 없습니다.</span>';
    }
    
  })
  .catch(error => { // 통신 자체를 실패
    console.error('추가 실패 : ', error);
  });
  
  
  
}