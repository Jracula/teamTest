const replyButtons = $('.btn-comment-reply');

replyButtons.on('click', function() {
  const commentItem = $(this).closest('.comment-item');
  const replyForm = commentItem.find('.comment-reply-form');
  replyForm.show();

  const cancelButton = replyForm.find('.btn-comment-reply-cancel');
  cancelButton.on('click', function() {
    replyForm.hide();
  });

  const submitButton = replyForm.find('.btn-comment-reply-submit');
  submitButton.on('click', function() {
    const textarea = replyForm.find('.comment-reply-textarea');
    const content = textarea.val();

    if (content) {
      const replyList = commentItem.find('.comment-reply-list');
      const newReply = `
        <li class="comment-reply-item">
          <div class="comment-avatar"></div>
          <div class="comment-content">${content}</div>
        </li>
      `;
      replyList.append(newReply);

      replyForm.hide();
      textarea.val('');
    }
  });
});

$(".reshow").on("click", function() {
	//몇번째 답글달기 버튼을 클릭한지
	const idx = $(".reshow").index(this);
	if ($(this).text() == "답글달기") {
		$(this).text("취소");

	} else {
		$(this).text("답글달기");
	}
	$(".inputRecommentBox").eq(idx).toggle();
	$(".inputRecommnetBox").eq(idx).find("textarea").focus();
});
function modifyComment(obj, recommNo, bookNo) {
	//숨겨놓은 textarea를 화면에 보여줌
	$(obj).parent().prev().show();
	//화면에 있던 댓글내용(p태그)를 숨김
	$(obj).parent().prev().prev().hide();
	//수정 -> 수정완료
	$(obj).text("수정완료");
	$(obj).attr("onclick", "modifyComplete(this," + recommNo + "," + bookNo + ")");
	//삭제 -> 수정취소
	$(obj).next().text("수정취소");
	$(obj).next().attr("onclick", "modifyCancel(this," + recommNo + "," + bookNo + ")");
	//답글달기버튼 삭제
	$(obj).next().next().hide();
}
function modifyCancel(obj, recommNo, bookNo) {
	$(obj).parent().prev().hide(); // textarea 숨김
	$(obj).parent().prev().prev().show();//기존댓글 다시 보여줌
	//수정완료 -> 수정
	$(obj).prev().text("수정");
	$(obj).prev().attr("onclick", "modifyComment(this," + recommNo + "," + bookNo + ")");
	//수정취소 -> 삭제
	$(obj).text("삭제");
	$(obj).attr("onclick", "deleteComment(this," + recommNo + "," + bookNo + ")");
	//답글달기 버튼 다시 버보여줌
	$(obj).next().show();
}
function modifyComplete(obj, recommNo, bookNo) {
	//form태그를 생성해서 전송, a(location.href)
	//댓글 내용, 댓글번호,공지사항번호
	//1.form 태그 생성
	const form = $("<form style='display:none;' action='/updateRecomm.do' method='post'></form>");
	//2. input 태그 2개 숨김
	const recommNoInput = $("<input type='hidden' name='recommNo'>");
	recommNoInput.val(recommNo);
	const bookNoInput = $("<input type='text' name='bookNo'>");
	bookNoInput.val(bookNo);
	//3.textarea
	const recommContent = $(obj).parent().prev().clone();
	//4.form태그에 input, textarea를 모두 포함
	form.append(recommNoInput).append(bookNoInput).append(recommContent);
	//5.생성된 form태그를 body태그에 추가
	$("body").append(form);
	//form태그를 전송
	form.submit();
}

function deleteCommnet(obj, recommNo, bookNo) {
	if (confirm("댓글을 삭제하시겠습니까?")) {
		location.href = "/deleteRecomm.do?recommNo="+ recommNo +"&bookNo="+ bookNo;
	}
}
			