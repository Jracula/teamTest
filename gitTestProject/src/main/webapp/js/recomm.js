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