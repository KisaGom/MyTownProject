import { apiInstance } from ".";

const api = apiInstance();

function listBoard(success, fail) {
  api.get(`/board`).then(success).catch(fail);
}

function listSidoBoard(sidoCode, success, fail) {
  api.get(`/board/sido/${sidoCode}`).then(success).catch(fail);
}

function listGugunBoard(gugunCode, success, fail) {
  api.get(`/board/gugun/${gugunCode}`).then(success).catch(fail);
}

function listDongBoard(dongCode, success, fail) {
  api.get(`/board/dong/${dongCode}`).then(success).catch(fail);
}

function detailBoard(id, success, fail) {
  api.get(`/board/detail/${id}`).then(success).catch(fail);
}

function registBoard(item, success, fail) {
  api.post(`/board`, JSON.stringify(item)).then(success).catch(fail);
}

function modifyBoard(item, success, fail) {
  api.put(`/board/${item.id}`, JSON.stringify(item)).then(success).catch(fail);
}

function deleteBoard(id, success, fail) {
  api.delete(`/board/${id}`).then(success).catch(fail);
}

function updateLike(id, success, fail) {
  api.put(`/board/like/${id}`).then(success).catch(fail);
}

export {
  listBoard,
  listSidoBoard,
  listGugunBoard,
  listDongBoard,
  detailBoard,
  registBoard,
  modifyBoard,
  deleteBoard,
  updateLike,
};
