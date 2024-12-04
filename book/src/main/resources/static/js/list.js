const togle = document.querySelector(".select");
const text = togle.querySelector(".text");

text.addEventListener("click", (e) => {
  const builder = new BooleanBuilder();
  const qBook = QBook.book;

  if (dto.categoryName == text.value) {
    // 카테고리 검색
    builder.and(qBook.category.category_id(1));
  }
});
