const actionForm = document.querySelector("#actionForm");
// remove 클릭시
// actionForm action 수정
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (!confirm("삭제하시겠습니까?")) {
    return;
  }

  actionForm.action = "/guestbook/remove";
  actionForm.submit();
});

// list 클릭시
document.querySelector(".btn-info").addEventListener("click", (e) => {
  // actionForm method 수정(get)
  actionForm.method = "get";
  // gno 삭제
  actionForm.querySelector("[name='gno']").remove();
  actionForm.submit();
});
