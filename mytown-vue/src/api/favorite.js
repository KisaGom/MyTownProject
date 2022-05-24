import { apiInstance } from "./index.js";

const api = apiInstance();

function list(userid, success, fail) {
  api.get(`/favorite/${userid}`).then(success).catch(fail);
}

function insert(user, success, fail) {
  api.post(`/favorite`, user).then(success).catch(fail);
}

function deleteFav(user, success, fail) {
  api
    .delete(`/favorite/${user.userid}/dongCode/${user.dongCode}`)
    .then(success)
    .catch(fail);
}

export { list, insert, deleteFav };
