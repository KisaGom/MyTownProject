import { apiInstance } from ".";

const api = apiInstance();

function listBoard(dongCode, success, fail) {
  api.get(`/board/${dongCode}`).then(success).catch(fail);
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

export { listBoard, registBoard, modifyBoard, deleteBoard };
