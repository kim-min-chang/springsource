document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  if (e.keyCode == 13) {
    // 검색어 입력 확인
    // 없으면 메세지 후 리턴
    const keyword = e.target.value;
    if (!keyword) {
      alert("영화명을 입력하세요");
      return;
    }

    // 있으면 keyword 가져온 후
    // searchform 찾아서 keyword 입력값 변경
    const searchform = document.querySelector("#searchForm");
    searchform.querySelector("[name=keyword]").value = keyword;

    // form submit;
    searchform.submit();
  }
});
