import { apiInstance } from ".";

const api = apiInstance();

function listComment(board_id, success, fail) {
  api.get(`/comment/${board_id}`).then(success).catch(fail);
}

function registComment(item, success, fail) {
  api.post(`/comment`, JSON.stringify(item)).then(success).catch(fail);
}

function modifyComment(item, success, fail) {
  api
    .put(`/comment/${item.id}`, JSON.stringify(item))
    .then(success)
    .catch(fail);
}

function deleteComment(id, success, fail) {
  api.delete(`/comment/${id}`).then(success).catch(fail);
}

export { listComment, registComment, modifyComment, deleteComment };
