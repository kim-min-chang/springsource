// 영화 전체 리뷰 가져오기
const reviewList = document.querySelector(".review-list");
const reviewFrom = document.querySelector("#reviewForm");
// 날짜 포멧
const formatData = (str) => {
  const date = new Date(str);

  return (
    date.getFullYear() +
    "/" +
    (date.getMonth() + 1) +
    "/" +
    date.getDate() +
    " " +
    date.getHours() +
    ":" +
    date.getMinutes()
  );
};

const reviewLoaded = () => {
  fetch(`/reviews/${mno}/all`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // 도착한 data화면에 보여주기
      document.querySelector(".review-cnt").innerHTML = data.length;

      if (data.length > 0) {
        reviewList.classList.remove("hidden");
      }

      let tags = "";
      data.forEach((review) => {
        tags += `<div class="d-flex justify-content-between my-2 border-bottem py-2 review-row" data-rno="${review.reviewNo}">`;
        tags += `<div class="flex-grow-1 align-self-center">`;
        tags += `<div><span class="font-semibold">${review.text}</span></div>`;
        tags += `<div class="small text-muted"><span class="d-inline-block mr-3">${review.nickname}</span>`;
        tags += `평점 : <span class="grade">${review.grade}</span><div class="strrr"></div></div>`;
        tags += `<div class="text-muted"><span class="small">${formatData(
          review.regDate
        )}</span></div></div >`;
        tags += `<div class="d-flex flex-column align-self-center"><div class="mb-2">`;
        tags += `<button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
        tags += `<div class="mb-2"><button class="btn btn-outline-success btn-sm">수정</button></div></div></div> `;
      });
      // 리뷰 영역에 보여주기
      reviewList.innerHTML = tags;
    });
};
reviewLoaded();

// 리뷰 작성 및 수정
reviewFrom.addEventListener("submit", (e) => {
  e.preventDefault();

  const reviewNo = reviewFrom.reviewNo.value;
  const email = reviewFrom.email.value;
  const nickname = reviewFrom.nickname.value;
  const text = reviewFrom.text.value;

  const review = {
    reviewNo: reviewNo,
    text: text,
    grade: grade,
    mno: mno,
    mid: 49,
    email: "user49@naver.com",
    nickname: "nickname49",
  };
  if (!reviewNo) {
    // 신규작성
    fetch(`/reviews/${mno}`, {
      headers: {
        "content-type": "application/json",
      },
      method: "post",
      body: JSON.stringify(review),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);
        reviewFrom.nickname.value = "";
        reviewFrom.email.value = "";
        reviewFrom.text.value = "";
        reviewFrom.reviewNo.value = "";

        reviewFrom.querySelector(".starrr a:nth-child(" + grade + ")").click();
        alert(data + "번 리뷰가 등록되었습니다.");

        reviewLoaded();
      });
  } else {
    // 수정
    fetch(`/reviews/${mno}/${reviewNo}`, {
      headers: {
        "content-type": "application/json",
      },
      method: "put",
      body: JSON.stringify(review),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);
        reviewFrom.nickname.value = "";
        reviewFrom.email.value = "";
        reviewFrom.text.value = "";
        reviewFrom.reviewNo.value = "";

        reviewFrom.querySelector(".btn-outline-danger").innerHTML = "리뷰 등록";
        reviewFrom.querySelector(".starrr a:nth-child(" + grade + ")").click();
        alert(data + "번 리뷰가 수정되었습니다.");

        reviewLoaded();
      });
  }
});

// 리뷰 삭제 및 조회
reviewList.addEventListener("click", (e) => {
  const btn = e.target;
  // reviewNo 가져오기 (data-rno 값)
  const reviewNo = btn.closest(".review-row").dataset.rno;
  if (btn.classList.contains("btn-outline-danger")) {
    if (!confirm("리뷰를 삭제하시겠습니까?")) return;
    // fetch 작성
    fetch(`/reviews/${mno}/${reviewNo}`, {
      method: "delete",
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);
        alert(data + "번 리뷰가 삭제되었습니다.");
        reviewLoaded();
      });
  } else if (btn.classList.contains("btn-outline-success")) {
    fetch(`/reviews/${mno}/${reviewNo}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        reviewForm.reviewNo.value = `${data.reviewNo}`;
        reviewForm.email.value = `${data.email}`;
        reviewForm.nickname.value = `${data.nickname}`;
        reviewForm.text.value = `${data.text}`;

        reviewFrom
          .querySelector(".starrr a:nth-child(" + data.grade + ")")
          .click();
        reviewFrom.querySelector(".btn-outline-danger").innerHTML = "리뷰 수정";
      });
  }
});

// 이미지 모달 요소 가져오기
const imgModal = document.querySelector("#imgModal");

if (imgModal) {
  imgModal.addEventListener("show.bs.modal", (e) => {
    // 모달을 뜨게 만든 img 요소 가져오기
    const posterimg = e.relatedTarget;

    // data- 가져오기
    const file = posterimg.getAttribute("data-file");
    console.log(file);

    imgModal.querySelector(".modal-title").textContent = `${title}`;

    imgModal.querySelector(
      ".modal-body"
    ).innerHTML = `<img src="/upload/display?fileName=${file}&size=1" alt="" style="width:100%"/>`;
    e.stopImmediatePropagation();
  });
}
